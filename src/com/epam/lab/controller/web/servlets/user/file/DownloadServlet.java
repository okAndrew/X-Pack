package com.epam.lab.controller.web.servlets.user.file;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.lab.controller.exceptions.RequestedRangeNotSatisfiableException;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.user.DownloadService;
import com.epam.lab.model.Range;
import com.epam.lab.model.UserFile;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";
	private DownloadService service;

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

		boolean haveAccess = preprocessing(request);

		if (!haveAccess || !service.getFile().exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		if (service.ifNoneMatchProcess()) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			response.setHeader("ETag", service.geteTag());
			response.setDateHeader("Expires", service.getExpires());
			return;
		}

		if (service.ifMatchProcess()) {
			response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
			return;
		}

		List<Range> ranges;
		try {
			ranges = service.getRangeList();
		} catch (RequestedRangeNotSatisfiableException e) {
			response.setHeader("Content-Range",
					"bytes */" + service.getLength());
			response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			return;
		}

		String contentType = getServletContext().getMimeType(
				service.getFileName());
		if (contentType == null) {
			contentType = "application/octet-stream; charset=UTF-8";
		} else {
			contentType += "; charset=UTF-8";
		}

		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setHeader("Content-Disposition", "attachment; filename="
				+ URLEncoder.encode(service.getFileName(), "UTF-8"));
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("ETag", service.geteTag());
		response.setDateHeader("Last-Modified", service.getLastModified());
		response.setDateHeader("Expires", service.getExpires());

		RandomAccessFile input = null;
		OutputStream output = null;

		try {
			input = new RandomAccessFile(service.getFile(), "r");
			output = response.getOutputStream();

			if (ranges.isEmpty() || ranges.get(0) == service.getFull()) {

				Range r = service.getFull();
				response.setContentType(contentType);
				response.setHeader("Content-Range", "bytes " + r.getStart()
						+ "-" + r.getEnd() + "/" + r.getTotal());

				if (content) {
					response.setHeader("Content-Length",
							String.valueOf(r.getLength()));

					service.copy(input, output, r.getStart(), r.getLength());
				}

			} else if (ranges.size() == 1) {

				Range r = ranges.get(0);
				response.setContentType(contentType);
				response.setHeader("Content-Range", "bytes " + r.getStart()
						+ "-" + r.getEnd() + "/" + r.getTotal());
				response.setHeader("Content-Length",
						String.valueOf(r.getLength()));
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

				if (content) {
					service.copy(input, output, r.getStart(), r.getLength());
				}

			} else {

				response.setContentType("multipart/byteranges; boundary="
						+ MULTIPART_BOUNDARY);
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

				if (content) {
					ServletOutputStream sos = (ServletOutputStream) output;

					for (Range r : ranges) {
						sos.println();
						sos.println("--" + MULTIPART_BOUNDARY);
						sos.println("Content-Type: " + contentType);
						sos.println("Content-Range: bytes " + r.getStart()
								+ "-" + r.getEnd() + "/" + r.getTotal());
						service.copy(input, output, r.getStart(), r.getLength());
					}

					sos.println();
					sos.println("--" + MULTIPART_BOUNDARY + "--");
				}
			}
		} finally {
			service.close(output);
			service.close(input);
		}
	}

	private boolean preprocessing(HttpServletRequest request) {
		boolean result = false;
		String ifNoneMatch = request.getHeader("If-None-Match");
		Long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		String ifMatch = request.getHeader("If-Match");
		Long ifUnmodifiedSince = request.getDateHeader("If-Unmodified-Since");
		String range = request.getHeader("Range");
		String ifRange = request.getHeader("If-Range");
		String ifRangeTime;
		try {
			Long ifRangeTimeLong = request.getDateHeader("If-Range");
			ifRangeTime = ifRangeTimeLong.toString();
		} catch (IllegalArgumentException e) {
			ifRangeTime = "";
		}

		HttpSession session = request.getSession(false);
		Long userId = null;
		if (session != null) {
			Object attrUserId = session.getAttribute("userid");
			if (attrUserId != null)
				userId = (Long) attrUserId;
		}
		String fileName = request.getParameter("file");
		if (fileName == null && userId != null) {
			String[] filesIds = request.getParameterValues("files");
			String[] foldersIds = request.getParameterValues("folders");
			if (filesIds != null || foldersIds != null) {
				service = new DownloadService(userId, ifNoneMatch,
						ifModifiedSince, ifMatch, ifUnmodifiedSince, range,
						ifRange, ifRangeTime, filesIds, foldersIds);
				result = true;
			}
		} else {
			UserFile userFile = new UserFileServiceImpl().getByName(fileName);
			if (userFile != null) {
				if (userFile.getIsPublic()
						|| (userId != null && userId == userFile.getIdUser())) {
					service = new DownloadService(userId, ifNoneMatch,
							ifModifiedSince, ifMatch, ifUnmodifiedSince, range,
							ifRange, ifRangeTime, userFile);
					result = true;
				}
			}
		}
		return result;
	}
}