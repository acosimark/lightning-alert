package com.dtn.assignment.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.geotools.tile.impl.bing.BingTileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dtn.assignment.models.Assets;
import com.dtn.assignment.models.LightningEvent;

@Service
public class CloudToGroundAlertService extends AbstractAlertService {

	private static final Logger log = LoggerFactory.getLogger(CloudToGroundAlertService.class);

	private Set<String> strikedAssets = new HashSet<>();

	@Override
	public void alert(LightningEvent event) {
		String quadKey = BingTileUtil.lonLatToQuadKey(event.getLongitude(), event.getLatitude(), this.getZoomLevel());
		if (!strikedAssets.contains(quadKey)) {
			Optional<Assets> asset = this.getAssets().stream().filter(a -> a.getQuadKey().equals(quadKey)).findFirst();

			if (asset.isPresent()) {
				strikedAssets.add(quadKey);
				log.info("lightning alert for {}:{}", asset.get().getAssetOwner(), asset.get().getAssetName());
			}
		}
	}

}
