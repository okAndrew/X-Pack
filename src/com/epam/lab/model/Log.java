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

}
