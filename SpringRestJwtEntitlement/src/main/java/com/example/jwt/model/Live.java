package com.example.jwt.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Live {
	public Live() {
	}

	private String status;
	private Date currentSystemTime;

	public String getStatus() {
		if (status == null)
			status = "UP";
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCurrentSystemTime() {
		if (currentSystemTime == null)
			currentSystemTime = new Date();
		return currentSystemTime;
	}

	public void setCurrentSystemTime(Date currentSystemTime) {
		this.currentSystemTime = currentSystemTime;
	}

}
