package com.dtn.assignment.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.dtn.assignment.models.Assets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@Component
public class AssetsResource {

	private static final Logger log = LoggerFactory.getLogger(AssetsResource.class);

	@Value("classpath:assets.json")
	private Resource resourceFile;

	private List<Assets> assets;

	public List<Assets> getAssets() {
		if (assets == null) {
			loadAssets();
		}
		return assets;
	}

	@SuppressWarnings("serial")
	private void loadAssets() {
		if (!resourceFile.exists())
			assets = new ArrayList<>();
		else {
			try {
				String resourceContent = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))
						.lines().collect(Collectors.joining());

				assets = new Gson().fromJson(resourceContent, new TypeToken<List<Assets>>() {
				}.getType());
			}
			catch (Exception e) {
				log.error("Exception occured while reading assets");
				assets = new ArrayList<>();
			}
		}
	}

}
