package com.dtn.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.util.ReflectionTestUtils;

import com.dtn.assignment.mockutils.MockDataUtil;
import com.dtn.assignment.models.Assets;

@SpringBootTest(classes = AssetsResource.class)
public class AssetsResourceTest {

	@Autowired
	AssetsResource assetsResource;

	private Resource resourceFile;

	@BeforeEach
	void setUp() {
		resourceFile = (Resource) ReflectionTestUtils.getField(assetsResource, "resourceFile");
		ReflectionTestUtils.setField(assetsResource, "assets", null);
	}

	@AfterEach
	void tearDown() {
		ReflectionTestUtils.setField(assetsResource, "resourceFile", resourceFile);
	}

	@Test
	void testWhenAssetResourceExists() throws IOException {
		List<Assets> mockAssets = MockDataUtil.getMockListOfAssets();

		List<Assets> assets = assetsResource.getAssets();
		assertEquals(mockAssets.size(), assets.size());
	}

	@Test
	void testWhenAssetResourceDoesNotExist() throws IOException {
		Resource mockResource = new ClassPathResource("non-existing-file.json");
		ReflectionTestUtils.setField(assetsResource, "resourceFile", mockResource);

		assertEquals(0, assetsResource.getAssets().size());
	}

	@Test
	void testWhenAssetResourceContainsInvalidValue() throws IOException {
		Resource mockResource = new ClassPathResource("asset-023112310233.json");
		mockResource.exists();
		ReflectionTestUtils.setField(assetsResource, "resourceFile", mockResource);

		assertEquals(0, assetsResource.getAssets().size());
	}

}
