package com.epam.lab.model;

import com.epam.lab.controller.annotations.TableColumn;

public class Counter {
	
	@TableColumn("countUsers")
	private Long countUsers;

	@TableColumn("countLogs")
	private Long countLogs;

	@TableColumn("countTariffs")
	private Long countTariffs;
	
	public Counter() {
	}
	
	public Counter(Long countUsers, Long countLogs, Long countTariffs) {
		this.countUsers = countUsers;
		this.countLogs = countLogs;
		this.countTariffs = countTariffs;
	}

	public Long getCountUsers() {
		return countUsers;
	}

	public void setCountUsers(Long countUsers) {
		this.countUsers = countUsers;
	}

	public Long getCountLogs() {
		return countLogs;
	}

	public void setCountLogs(Long countLogs) {
		this.countLogs = countLogs;
	}

	public Long getCountTariffs() {
		return countTariffs;
	}

	public void setCountTariffs(Long countTariffs) {
		this.countTariffs = countTariffs;
	}

}