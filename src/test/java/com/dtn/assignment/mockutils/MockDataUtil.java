package com.dtn.assignment.mockutils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.dtn.assignment.models.Assets;
import com.dtn.assignment.models.LightningEvent;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class MockDataUtil {

	public static final Assets getMockAsset() throws IOException {
		StringBuilder json = new StringBuilder();
		File file = ResourceUtils.getFile("classpath:asset-023112310233.json");
		Files.lines(Paths.get(file.toURI()), StandardCharsets.UTF_8).forEach(line -> json.append(line));
		return new Gson().fromJson(json.toString(), Assets.class);
	}

	public static final LightningEvent getMockLightningEvent() throws IOException {
		return new Gson().fromJson(getMockLightningEventStringInput(), LightningEvent.class);
	}

	public static final String getMockLightningEventStringInput() throws IOException {
		StringBuilder json = new StringBuilder();
		File file = ResourceUtils.getFile("classpath:lightning-event-023112310233.json");
		Files.lines(Paths.get(file.toURI()), StandardCharsets.UTF_8).forEach(line -> json.append(line));
		return json.toString();
	}

	@SuppressWarnings("serial")
	public static final List<Assets> getMockListOfAssets() throws IOException {
		StringBuilder json = new StringBuilder();
		File file = ResourceUtils.getFile("classpath:assets.json");
		Files.lines(Paths.get(file.toURI()), StandardCharsets.UTF_8).forEach(line -> json.append(line));
		return new Gson().fromJson(json.toString(), new TypeToken<List<Assets>>() {
		}.getType());
	}

}
