package com.epam.lab.controller.services.user;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.epam.lab.controller.exceptions.RequestedRangeNotSatisfiableException;
import com.epam.lab.controller.services.file.UserFileServiceImpl;
import com.epam.lab.controller.services.statistics.traffichistory.TrafficHistoryServiceImpl;
import com.epam.lab.controller.utils.TimeStampManager;
import com.epam.lab.model.Range;
import com.epam.lab.model.TrafficHistory;
import com.epam.lab.model.UserFile;

public class DownloadService {
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	private static final long DEFAULT_EXPIRE_TIME = 604800000L;

	private UserFileServiceImpl userFileService = new UserFileServiceImpl();
	private long expires = System.currentTimeMillis() + DEFAULT_EXPIRE_TIME;
	private List<Range> ranges = new ArrayList<Range>();
	private File file;
	private String fileName;
	private long length;
	private long lastModified;
	private String eTag;
	private Range full;

	private String ifNoneMatch;
	private long ifModifiedSince;
	private String ifMatch;
	private long ifUnmodifiedSince;
	private String range;
	private String ifRange;
	private String ifRangeTime;

	private Long userId;

	public DownloadService(Long userId, String ifNoneMatch,
			long ifModifiedSince, String ifMatch, long ifUnmodifiedSince,
			String range, String ifRange, String ifRangeTime, UserFile userFile) {
		file = new File(userFile.getPath() + File.separator + userFile.getName());
		fileName = userFile.getNameIncome();
		init(userId, ifNoneMatch, ifModifiedSince, ifMatch, ifUnmodifiedSince,
				range, ifRange, ifRangeTime);
	}

	public DownloadService(Long  userId, String ifNoneMatch,
			long ifModifiedSince, String ifMatch, long ifUnmodifiedSince,
			String range, String ifRange, String ifRangeTime, String[] filesIds,
			String[] foldersIds) {
		file = userFileService.getArchive(filesIds, foldersIds);
		fileName = file.getName();
		init(userId, ifNoneMatch, ifModifiedSince, ifMatch, ifUnmodifiedSince,
				range, ifRange, ifRangeTime);
	}

	public File getFile() {
		return file;
	}

	public String getFileName() {
		return fileName;
	}

	public long getLength() {
		return length;
	}

	public long getLastModified() {
		return lastModified;
	}

	public String geteTag() {
		return eTag;
	}

	public long getExpires() {
		return expires;
	}

	public Range getFull() {
		return full;
	}

	private void init(Long userId, String ifNoneMatch, long ifModifiedSince,
			String ifMatch, long ifUnmodifiedSince, String range,
			String ifRange, String ifRangeTime) {
		this.userId = userId;
		this.ifNoneMatch = ifNoneMatch;
		this.ifModifiedSince = ifModifiedSince;
		this.ifMatch = ifMatch;
		this.ifUnmodifiedSince = ifUnmodifiedSince;
		this.range = range;
		this.ifRange = ifRange;
		this.ifRangeTime = ifRangeTime;
		length = file.length();
		lastModified = file.lastModified();
		eTag = fileName + "_" + length + "_" + lastModified;
		full = new Range(0, length - 1, length);
	}

	public boolean ifNoneMatchProcess() {
		return (ifNoneMatch != null && DownloadService.matches(ifNoneMatch,
				eTag))
				|| (ifNoneMatch == null && ifModifiedSince != -1 && ifModifiedSince + 1000 > lastModified);
	}

	public boolean ifMatchProcess() {
		return ifMatch != null && !DownloadService.matches(ifMatch, eTag)
				&& ifUnmodifiedSince != -1
				&& ifUnmodifiedSince + 1000 <= lastModified;
	}

	public List<Range> getRangeList()
			throws RequestedRangeNotSatisfiableException {
		if (range != null) {
			if (!range.matches("^bytes=\\d*-\\d*(,\\d*-\\d*)*$")) {
				throw new RequestedRangeNotSatisfiableException();
			}
			if (ifRange != null && !ifRange.equals(eTag)) {
				try {
					long ifRangeTime = Long.parseLong(this.ifRangeTime); 
					if (ifRangeTime != -1 && ifRangeTime + 1000 < lastModified) {
						ranges.add(full);
					}
				} catch (IllegalArgumentException ignore) {
					ranges.add(full);
				}
			}
			if (ranges.isEmpty()) {
				for (String part : range.substring(6).split(",")) {
					long start = DownloadService.sublong(part, 0,
							part.indexOf("-"));
					long end = DownloadService.sublong(part,
							part.indexOf("-") + 1, part.length());
					if (start == -1) {
						start = length - end;
						end = length - 1;
					} else if (end == -1 || end > length - 1) {
						end = length - 1;
					}
					if (start > end) {
						throw new RequestedRangeNotSatisfiableException();
					}
					ranges.add(new Range(start, end, length));
				}
			}
		}
		return ranges;
	}

	public void copy(RandomAccessFile input, OutputStream output, long start,
			long length) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int read;
		int size = 0;
		if (input.length() == length) {
			while ((read = input.read(buffer)) > 0) {
				output.write(buffer, 0, read);
				size += read;
			}
		} else {
			input.seek(start);
			long toRead = length;
			while ((read = input.read(buffer)) > 0) {
				if ((toRead -= read) > 0) {
					output.write(buffer, 0, read);
					size += read;
				} else {
					output.write(buffer, 0, (int) toRead + read);
					size += (int) toRead + read;
					break;
				}
			}
		}
		createTrafficHistory(size);
	}

	public void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException ignore) {
			}
		}
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

	// public void download(File file, OutputStream os, String userId)
	// throws IOException {
	// InputStream fis = new FileInputStream(file);
	// byte[] bufferData = new byte[1024];
	// int read = 0;
	// int size = 0;
	// while ((read = fis.read(bufferData)) != -1) {
	// os.write(bufferData, 0, read);
	// size += read;
	// }
	// createTrafficHistory(size);
	// os.flush();
	// os.close();
	// fis.close();
	// }

	private void createTrafficHistory(int read) {
		TrafficHistory traffic = new TrafficHistory();
		Timestamp date = TimeStampManager.getFormatCurrentTimeStamp();
		traffic.setUserId(userId).setDate(date).setSize(read);
		TrafficHistoryServiceImpl traficService = new TrafficHistoryServiceImpl();
		traficService.insert(traffic);
	}

}