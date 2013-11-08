package com.epam.lab.model;

import java.sql.Timestamp;

import com.epam.lab.controller.annotations.TableColumn;

public class Log {

	@TableColumn("id")
	private long id;

	@TableColumn("date_time")
	private Timestamp datetime;

	@TableColumn("logger")
	private String logger;

	@TableColumn("lvl")
	private String level;

	@TableColumn("msg")
	private String message;

	public Log() {
	}

	public Log(long id, Timestamp datetime, String logger, String level,
			String message) {
		this.id = id;
		this.datetime = datetime;
		this.logger = logger;
		this.level = level;
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getLogger() {
		return logger;
	}

	public void setLogger(String logger) {
		this.logger = logger;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder log = new StringBuilder();
		log.append("Log[id: ").append(id);
		log.append(", datetime: ").append(datetime);
		log.append(", logger: ").append(logger);
		log.append(", level: ").append(level);
		log.append(", message: ").append(message);
		log.append("]");
		return log.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((logger == null) ? 0 : logger.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Log other = (Log) obj;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (id != other.id)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (logger == null) {
			if (other.logger != null)
				return false;
		} else if (!logger.equals(other.logger))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

}
