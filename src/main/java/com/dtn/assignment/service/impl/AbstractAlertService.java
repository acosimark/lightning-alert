package com.dtn.assignment.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.dtn.assignment.models.Assets;
import com.dtn.assignment.service.IAlertService;

public abstract class AbstractAlertService implements IAlertService {

	private int zoomLevel = 12;

	private List<Assets> assets = new ArrayList<>();

	/**
	 * @return the zoomLevel
	 */
	public int getZoomLevel() {
		return zoomLevel;
	}

	/**
	 * @param zoomLevel the zoomLevel to set
	 */
	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	/**
	 * @return the assets
	 */
	public List<Assets> getAssets() {
		return assets;
	}

	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<Assets> assets) {
		this.assets = assets;
	}

}
