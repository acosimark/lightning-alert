package com.dtn.assignment.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dtn.assignment.mockutils.MockDataUtil;
import com.dtn.assignment.models.LightningEvent;

@SpringBootTest(classes = LightningEventService.class)
public class LightningEventServiceTest {

	@Autowired
	LightningEventService service;

	@MockBean
	CloudToGroundAlertService alertService;

	@Test
	void testWhenInputIsValidLightningObject() throws IOException {
		String lightning = MockDataUtil.getMockLightningEventStringInput();
		service.process(lightning);

		verify(alertService, times(1)).alert(Mockito.any(LightningEvent.class));
	}

	@Test
	void testWhenInputIsNotValidLightningObject() throws IOException {
		String lightning = "{\"assetName\":\"Fahey Brooks\",\"quadKey\":\"023112310233\",\"assetOwner\":\"86315\"}";
		service.process(lightning);

		verify(alertService, times(0)).alert(Mockito.any(LightningEvent.class));
	}

	@Test
	void testWhenInputIsMalformedJson() throws IOException {
		// missing {
		String lightning = "\"flashType\":1,\"strikeTime\":1386285909025,"
				+ "\"latitude\":33.5524951,\"longitude\":-94.5822016,"
				+ "\"peakAmps\":15815,\"reserved\":\"000\",\"icHeight\":8940,"
				+ "\"receivedTime\":1386285919187,\"numberOfSensors\":17,\"multiplicity\":1}";
		service.process(lightning);

		verify(alertService, times(0)).alert(Mockito.any(LightningEvent.class));
	}

}
