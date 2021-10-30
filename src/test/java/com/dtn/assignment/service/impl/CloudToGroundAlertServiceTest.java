package com.dtn.assignment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import com.dtn.assignment.mockutils.MockDataUtil;
import com.dtn.assignment.models.Assets;
import com.dtn.assignment.models.LightningEvent;
import com.dtn.assignment.service.AssetsResource;

@SpringBootTest(classes = { CloudToGroundAlertService.class })
public class CloudToGroundAlertServiceTest {

	@Autowired
	CloudToGroundAlertService service;

	@MockBean
	AssetsResource resource;

	@AfterEach
	void teardown() {
		ReflectionTestUtils.setField(service, "strikedAssets", new HashSet<String>());
	}

	@Test
	@SuppressWarnings("unchecked")
	void testWhenLightningStrikesAKnownAsset() throws IOException {
		Assets asset = MockDataUtil.getMockAsset();
		LightningEvent lightning = MockDataUtil.getMockLightningEvent();

		when(resource.getAssets()).thenReturn(Lists.newArrayList(asset));

		service.alert(lightning);

		Set<String> strikedAssets = (Set<String>) ReflectionTestUtils.getField(service, "strikedAssets");
		assertEquals(1, strikedAssets.size());
		assertTrue(strikedAssets.contains(asset.getQuadKey()));
	}

	@Test
	@SuppressWarnings("unchecked")
	void testWhenLightningStrikesAKnownAssetTwice() throws IOException {
		Assets asset = MockDataUtil.getMockAsset();
		LightningEvent lightning = MockDataUtil.getMockLightningEvent();

		when(resource.getAssets()).thenReturn(Lists.newArrayList(asset));

		// 1st lightning strike
		service.alert(lightning);

		Set<String> strikedAssets = (Set<String>) ReflectionTestUtils.getField(service, "strikedAssets");
		assertEquals(1, strikedAssets.size());
		assertTrue(strikedAssets.contains(asset.getQuadKey()));

		// 2nd lightning strike
		service.alert(lightning);

		strikedAssets = (Set<String>) ReflectionTestUtils.getField(service, "strikedAssets");
		assertEquals(1, strikedAssets.size());
		assertTrue(strikedAssets.contains(asset.getQuadKey()));
	}

	@Test
	@SuppressWarnings("unchecked")
	void testWhenLightningStrikesAnUnknownAsset() throws IOException {
		LightningEvent lightning = MockDataUtil.getMockLightningEvent();

		when(resource.getAssets()).thenReturn(Lists.emptyList());

		service.alert(lightning);

		Set<String> strikedAssets = (Set<String>) ReflectionTestUtils.getField(service, "strikedAssets");
		assertEquals(0, strikedAssets.size());
	}

}
