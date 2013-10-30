package com.epam.lab.controller.web.servlets.file;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.model.UserFile;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	private static final long DEFAULT_EXPIRE_TIME = 604800000L;
	private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp, false);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response, true);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		processRequest(req, resp, true);
	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response, boolean content)
			throws ServletException, IOException {
		UserFileServiceImpl service = new UserFileServiceImpl();
		File file;
		String fileName;

		if (request.getParameter("fileid") == null) {
			String[] filesIds = request.getParameterValues("files");
			String[] foldersIds = request.getParameterValues("folders");
			file = service.getArchive(filesIds, foldersIds);
			fileName = file.getName();
		} else {
			long fileId = Long.valueOf(request.getParameter("fileid"));
			UserFile f = service.get(fileId);
			String filePath = f.getPath() + File.separator + f.getName();
			file = new File(filePath);
			fileName = f.getNameIncome();
		}

		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		long length = file.length();
		long lastModified = file.lastModified();
		String eTag = fileName + "_" + length + "_" + lastModified;
		long expires = System.currentTimeMillis() + DEFAULT_EXPIRE_TIME;

		// String ifNoneMatch = request.getHeader("If-None-Match");
		// long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		// if ((ifNoneMatch != null && matches(ifNoneMatch, eTag)) ||
		// (ifNoneMatch == null && ifModifiedSince != -1
		// && ifModifiedSince + 1000 > lastModified)) {
		// response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		// response.setHeader("ETag", eTag);
		// response.setDateHeader("Expires", expires);
		// return;
		// }

		String ifMatch = request.getHeader("If-Match");
		long ifUnmodifiedSince = request.getDateHeader("If-Unmodified-Since");
		if (ifMatch != null && !matches(ifMatch, eTag)
				&& ifUnmodifiedSince != -1
				&& ifUnmodifiedSince + 1000 <= lastModified) {
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
			return;
		}

		Range full = new Range(0, length - 1, length);
		List<Range> ranges = new ArrayList<Range>();
		String range = request.getHeader("Range");
		if (range != null) {

			if (!range.matches("^bytes=\\d*-\\d*(,\\d*-\\d*)*$")) {
				response.setHeader("Content-Range", "bytes */" + length);
				response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
				return;
			}

			String ifRange = request.getHeader("If-Range");
			if (ifRange != null && !ifRange.equals(eTag)) {
				try {
					long ifRangeTime = request.getDateHeader("If-Range");
					if (ifRangeTime != -1 && ifRangeTime + 1000 < lastModified) {
						ranges.add(full);
					}
				} catch (IllegalArgumentException ignore) {
					ranges.add(full);
				}
			}

			if (ranges.isEmpty()) {
				for (String part : range.substring(6).split(",")) {
					long start = sublong(part, 0, part.indexOf("-"));
					long end = sublong(part, part.indexOf("-") + 1,
							part.length());
					if (start == -1) {
						start = length - end;
						end = length - 1;
					} else if (end == -1 || end > length - 1) {
						end = length - 1;
					}
					if (start > end) {
						response.setHeader("Content-Range", "bytes */" + length);
						response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
						return;
					}
					ranges.add(new Range(start, end, length));
				}
			}
		}

		String contentType = getServletContext().getMimeType(fileName);
		if (contentType == null) {
			contentType = "application/octet-stream; charset=UTF-8";
		} else {
			contentType += "; charset=UTF-8";
		}

		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(fileName, "UTF-8"));
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("ETag", eTag);
		response.setDateHeader("Last-Modified", lastModified);
		response.setDateHeader("Expires", expires);

		// ////////////////////////////////////////////////////////////////////////////////////
		// Send requested file (part(s)) to client
		// ------------------------------------------------

		// Prepare streams.
		RandomAccessFile input = null;
		OutputStream output = null;

		try {
			// Open streams.
			input = new RandomAccessFile(file, "r");
			output = response.getOutputStream();

			if (ranges.isEmpty() || ranges.get(0) == full) {

				// Return full file.
				Range r = full;
				response.setContentType(contentType);
				response.setHeader("Content-Range", "bytes " + r.start + "-"
						+ r.end + "/" + r.total);

				if (content) {
					response.setHeader("Content-Length",
							String.valueOf(r.length));

					copy(input, output, r.start, r.length);
				}

			} else if (ranges.size() == 1) {

				// Return single part of file.
				Range r = ranges.get(0);
				response.setContentType(contentType);
				response.setHeader("Content-Range", "bytes " + r.start + "-"
						+ r.end + "/" + r.total);
				response.setHeader("Content-Length", String.valueOf(r.length));
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

				if (content) {
					// Copy single part range.
					copy(input, output, r.start, r.length);
				}

			} else {

				// Return multiple parts of file.
				response.setContentType("multipart/byteranges; boundary="
						+ MULTIPART_BOUNDARY);
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

				if (content) {
					// Cast back to ServletOutputStream to get the easy println
					// methods.
					ServletOutputStream sos = (ServletOutputStream) output;

					// Copy multi part range.
					for (Range r : ranges) {
						// Add multipart boundary and header fields for every
						// range.
						sos.println();
						sos.println("--" + MULTIPART_BOUNDARY);
						sos.println("Content-Type: " + contentType);
						sos.println("Content-Range: bytes " + r.start + "-"
								+ r.end + "/" + r.total);

						// Copy single part range of multi part range.
						copy(input, output, r.start, r.length);
					}

					// End with multipart boundary.
					sos.println();
					sos.println("--" + MULTIPART_BOUNDARY + "--");
				}
			}
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}

		// ////////////////////////////////////////////////////////////////////////////////////

//		response.setContentType(contentType);
//		response.setCharacterEncoding("UTF-8");
//		response.setContentLength((int) file.length());
//		ServletOutputStream os = response.getOutputStream();
//
//		String userId = request.getSession(false).getAttribute("userid")
//				.toString();
//		downloadService.download(file, os, userId);
	}

	private static boolean matches(String matchHeader, String toMatch) {
		String[] matchValues = matchHeader.split("\\s*,\\s*");
		Arrays.sort(matchValues);
		return Arrays.binarySearch(matchValues, toMatch) > -1
				|| Arrays.binarySearch(matchValues, "*") > -1;
	}

	private static long sublong(String value, int beginIndex, int endIndex) {
		String substring = value.substring(beginIndex, endIndex);
		return (substring.length() > 0) ? Long.parseLong(substring) : -1;
	}

	private static void copy(RandomAccessFile input, OutputStream output,
			long start, long length) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int read;

		if (input.length() == length) {
			// Write full range.
			while ((read = input.read(buffer)) > 0) {
				output.write(buffer, 0, read);
			}
		} else {
			// Write partial range.
			input.seek(start);
			long toRead = length;

			while ((read = input.read(buffer)) > 0) {
				if ((toRead -= read) > 0) {
					output.write(buffer, 0, read);
				} else {
					output.write(buffer, 0, (int) toRead + read);
					break;
				}
			}
		}
	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException ignore) {
				// Ignore IOException. If you want to handle this anyway, it
				// might be useful to know
				// that this will generally only be thrown when the client
				// aborted the request.
			}
		}
	}

	protected class Range {
		long start;
		long end;
		long length;
		long total;

		public Range(long start, long end, long total) {
			this.start = start;
			this.end = end;
			this.length = end - start + 1;
			this.total = total;
		}
	}
}
