package com.dtn.assignment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtn.assignment.constants.FlashType;
import com.dtn.assignment.models.LightningEvent;
import com.dtn.assignment.service.IAlertService;
import com.dtn.assignment.service.ILightningStrikeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LightningEventService implements ILightningStrikeService {

	private static final Logger log = LoggerFactory.getLogger(LightningEventService.class);

	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private IAlertService alertService;

	@Override
	public void process(String input) {
		try {
			LightningEvent lightning = mapper.readValue(input, LightningEvent.class);

			// alert only when lightning hits the ground
			if (lightning.getFlashType() == FlashType.CLOUD_TO_GROUND.getValue()) {
				alertService.alert(lightning);
			}
		}
		catch (JsonProcessingException e) {
			log.debug("Exception: ", e);
			log.error("Invalid JSON input!");
		}

	}

}
