package com.dtn.assignment.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtn.assignment.constants.FlashType;
import com.dtn.assignment.models.Assets;
import com.dtn.assignment.models.LightningEvent;
import com.dtn.assignment.service.ILightningStrikeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

@Service
public class LightningEventService implements ILightningStrikeService {

	private static final Logger log = LoggerFactory.getLogger(LightningEventService.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private AbstractAlertService alertService;

	@Override
	public void process(String ligthningPath, String assetsPath, int zoomLevel) {
		try {
			List<LightningEvent> events = getLightningEventsFromFile(ligthningPath);
			List<Assets> assets = getAssetsFromFile(assetsPath);

			alertService.setAssets(assets);
			alertService.setZoomLevel(zoomLevel);

			for (LightningEvent event : events)
				if (event.getFlashType() == FlashType.CLOUD_TO_GROUND.getValue())
					alertService.alert(event);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("serial")
	private List<Assets> getAssetsFromFile(String assetsPath) throws IOException {
		File file = new File(assetsPath);
		if (file.exists()) {
			List<Assets> assets = null;
			StringBuilder json = new StringBuilder();
			Files.lines(Paths.get(file.toURI()), StandardCharsets.UTF_8).forEach(e -> json.append(e));

			assets = new Gson().fromJson(json.toString(), new TypeToken<List<Assets>>() {
			}.getType());
			return assets;
		}
		else {
			throw new IOException("Assets File does not exist: " + assetsPath);
		}
	}

	private List<LightningEvent> getLightningEventsFromFile(String ligthningPath) throws IOException {
		File file = new File(ligthningPath);
		if (file.exists()) {
			List<LightningEvent> events = new ArrayList<>();
			Files.lines(Paths.get(file.toURI()), StandardCharsets.UTF_8).forEach(event -> {
				try {
					events.add(mapper.readValue(event, LightningEvent.class));
				}
				catch (JsonProcessingException e) {
					e.printStackTrace();
					log.error("Exception occured!", e);
				}
			});
			return events;
		}
		else {
			throw new IOException("LightningEvents File does not exist: " + ligthningPath);
		}
	}

}
