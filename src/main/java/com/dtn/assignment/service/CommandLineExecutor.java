package com.dtn.assignment.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineExecutor implements CommandLineRunner {

	@Autowired
	private ILightningStrikeService service;

	@Override
	public void run(String... args) {
		execute();
	}

	void execute() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			if (input.trim().isEmpty())
				continue;

			if (input.equalsIgnoreCase("exit"))
				break;

			service.process(input);
		}

		scanner.close();
	}

}
