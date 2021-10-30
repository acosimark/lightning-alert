package com.dtn.assignment.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import com.dtn.assignment.service.ILightningStrikeService;

@ShellComponent
public class LightningAlertShellCommand {

	@Autowired
	private ILightningStrikeService service;

	@ShellMethod("Reads lightning events data as a stream from standard input\n"
			+ "\t\t(one lightning strike per line as a JSON object, and matches \n"
			+ "\t\tthat data against a source of assets (also in JSON format) to \n" + "\t\tproduce an alert.")
	public void alert(@ShellOption(help = "Lightning Events file path") String lightning,
			@ShellOption(help = "Assets file path") String asset, @ShellOption(defaultValue = "12",
					help = "Level of detail, from 1 (lowest detail) to 23 (highest detail).") int zoomLevel) {
		service.process(lightning, asset, zoomLevel);
	}

}
