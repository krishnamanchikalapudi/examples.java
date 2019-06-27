package com.example.model;

import java.util.Date;

public class Status {

	private Boolean live = true;
	private Date liveAsOf = new Date();

	public Status() {
	}

	public Boolean getLive() {
		return live;
	}

	public void setLive(Boolean live) {
		this.live = live;
	}

	public Date getLiveAsOf() {
		return liveAsOf;
	}

	public void setLiveAsOf(Date liveAsOf) {
		this.liveAsOf = liveAsOf;
	}

}
