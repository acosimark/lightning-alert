package com.dtn.assignment.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.geotools.tile.impl.bing.BingTileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dtn.assignment.models.Assets;
import com.dtn.assignment.models.LightningEvent;
import com.dtn.assignment.service.AssetsResource;
import com.dtn.assignment.service.IAlertService;

@Service
public class CloudToGroundAlertService implements IAlertService {

	private static final Logger log = LoggerFactory.getLogger(CloudToGroundAlertService.class);

	@Value("${app.zoomlevel:12}")
	private int zoomLevel;

	@Autowired
	private AssetsResource assets;

	private Set<String> strikedAssets = new HashSet<>();

	@Override
	public void alert(LightningEvent lightning) {
		String quadKey = BingTileUtil.lonLatToQuadKey(lightning.getLongitude(), lightning.getLatitude(), zoomLevel);
		if (!strikedAssets.contains(quadKey)) {
			Optional<Assets> asset = assets.getAssets().stream().filter(a -> a.getQuadKey().equals(quadKey))
					.findFirst();

			if (asset.isPresent()) {
				strikedAssets.add(quadKey);
				log.info("lightning alert for {}:{}", asset.get().getAssetOwner(), asset.get().getAssetName());
			}
		}
	}

}
