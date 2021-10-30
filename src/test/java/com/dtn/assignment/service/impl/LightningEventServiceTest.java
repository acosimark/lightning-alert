package com.dtn.assignment.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import com.dtn.assignment.models.LightningEvent;

@SpringBootTest(classes = LightningEventService.class)
public class LightningEventServiceTest {

	@Autowired
	LightningEventService service;

	@MockBean
	CloudToGroundAlertService alertService;

	private int defaultZoomLevel;

	@Test
	void testWhenInputsAreValid() throws IOException {
		String ligthningPath = new ClassPathResource("lightning-events.json").getFile().getAbsolutePath();
		String assetsPath = new ClassPathResource("assets.json").getFile().getAbsolutePath();

		service.process(ligthningPath, assetsPath, defaultZoomLevel);

		verify(alertService, times(1)).setAssets(Mockito.anyList());
		verify(alertService, times(1)).setZoomLevel(defaultZoomLevel);
		verify(alertService, times(3)).alert(Mockito.any(LightningEvent.class));
	}

	@Test
	void testWhenFilePathInputsDoesNotExist() throws IOException {
		String nonExistingFile = "nonExistingFile.json";

		assertThrows(RuntimeException.class, () -> service.process(nonExistingFile, nonExistingFile, defaultZoomLevel));
	}

	@Test
	void testWhenLightningEventsContainsMalformedEvent() throws IOException {
		// contains 3 events w/ 1 malformed lightning event
		String ligthningPath = new ClassPathResource("lightning-events-with-malformed-json.json").getFile()
				.getAbsolutePath();
		String assetsPath = new ClassPathResource("assets.json").getFile().getAbsolutePath();

		service.process(ligthningPath, assetsPath, defaultZoomLevel);

		verify(alertService, times(1)).setAssets(Mockito.anyList());
		verify(alertService, times(1)).setZoomLevel(defaultZoomLevel);
		verify(alertService, times(2)).alert(Mockito.any(LightningEvent.class));
	}

	@Test
	void testWhenAssetsIsMalformed() throws IOException {
		String ligthningPath = new ClassPathResource("lightning-events.json").getFile().getAbsolutePath();
		// contains a single non-array asset
		String assetsPath = new ClassPathResource("asset-023112310233.json").getFile().getAbsolutePath();

		assertThrows(RuntimeException.class, () -> service.process(ligthningPath, assetsPath, defaultZoomLevel));
	}

}
