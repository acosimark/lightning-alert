package com.dtn.assignment.models;

import java.io.Serializable;

import com.google.gson.Gson;

public class Assets implements Serializable {

	private static final long serialVersionUID = -3457766566767591129L;

	private String assetName;

	private String quadKey;

	private String assetOwner;

	/**
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/**
	 * @return the quadKey
	 */
	public String getQuadKey() {
		return quadKey;
	}

	/**
	 * @param quadKey the quadKey to set
	 */
	public void setQuadKey(String quadKey) {
		this.quadKey = quadKey;
	}

	/**
	 * @return the assetOwner
	 */
	public String getAssetOwner() {
		return assetOwner;
	}

	/**
	 * @param assetOwner the assetOwner to set
	 */
	public void setAssetOwner(String assetOwner) {
		this.assetOwner = assetOwner;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
