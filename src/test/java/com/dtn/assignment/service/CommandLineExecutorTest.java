package com.dtn.assignment.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommandLineExecutorTest {

	@InjectMocks
	CommandLineExecutor runner;

	@Mock
	ILightningStrikeService service;

	private InputStream sysInBackup;

	@BeforeEach
	void setup() {
		sysInBackup = System.in;
	}

	@AfterEach
	void tearDown() {
		System.setIn(sysInBackup);
	}

	@Test
	void testShouldNotProcessEmptyInput() {
		ByteArrayInputStream in = new ByteArrayInputStream(" \nexit".getBytes());
		System.setIn(in);

		runner.execute();

		verify(service, times(0)).process(Mockito.anyString());
	}

	@Test
	void testShouldProcessNonEmptyInput() {
		String input = "{\"flashType\":1,\"strikeTime\":1386285909025,\"latitude\":33.5524951,\"longitude\":-94.5822016,\"peakAmps\":15815,\"reserved\":\"000\",\"icHeight\":8940,\"receivedTime\":1386285919187,\"numberOfSensors\":17,\"multiplicity\":1}\nexit";
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		runner.execute();

		verify(service, times(1)).process(Mockito.anyString());
	}

}
