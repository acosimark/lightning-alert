package com.dtn.assignment.constants;

public enum FlashType {

	CLOUD_TO_GROUND(0), CLOUD_TO_CLOUD(1), HEARTBEAT(9);

	private final int value;

	FlashType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
