package com.dtn.assignment.models;

import com.google.gson.Gson;

public class LightningEvent {

	private int flashType;

	private long strikeTime;

	private double latitude;

	private double longitude;

	private int peakAmps;

	private String reserved;

	private int icHeight;

	private long receivedTime;

	private int numberOfSensors;

	private int multiplicity;

	/**
	 * @return the flashType
	 */
	public int getFlashType() {
		return flashType;
	}

	/**
	 * @param flashType the flashType to set
	 */
	public void setFlashType(int flashType) {
		this.flashType = flashType;
	}

	/**
	 * @return the strikeTime
	 */
	public long getStrikeTime() {
		return strikeTime;
	}

	/**
	 * @param strikeTime the strikeTime to set
	 */
	public void setStrikeTime(long strikeTime) {
		this.strikeTime = strikeTime;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the peakAmps
	 */
	public int getPeakAmps() {
		return peakAmps;
	}

	/**
	 * @param peakAmps the peakAmps to set
	 */
	public void setPeakAmps(int peakAmps) {
		this.peakAmps = peakAmps;
	}

	/**
	 * @return the reserved
	 */
	public String getReserved() {
		return reserved;
	}

	/**
	 * @param reserved the reserved to set
	 */
	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	/**
	 * @return the icHeight
	 */
	public int getIcHeight() {
		return icHeight;
	}

	/**
	 * @param icHeight the icHeight to set
	 */
	public void setIcHeight(int icHeight) {
		this.icHeight = icHeight;
	}

	/**
	 * @return the receivedTime
	 */
	public long getReceivedTime() {
		return receivedTime;
	}

	/**
	 * @param receivedTime the receivedTime to set
	 */
	public void setReceivedTime(long receivedTime) {
		this.receivedTime = receivedTime;
	}

	/**
	 * @return the numberOfSensors
	 */
	public int getNumberOfSensors() {
		return numberOfSensors;
	}

	/**
	 * @param numberOfSensors the numberOfSensors to set
	 */
	public void setNumberOfSensors(int numberOfSensors) {
		this.numberOfSensors = numberOfSensors;
	}

	/**
	 * @return the multiplicity
	 */
	public int getMultiplicity() {
		return multiplicity;
	}

	/**
	 * @param multiplicity the multiplicity to set
	 */
	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
