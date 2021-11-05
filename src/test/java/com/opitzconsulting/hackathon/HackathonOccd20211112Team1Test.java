package com.opitzconsulting.hackathon;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class HackathonOccd20211112Team1Test {
	
	@Inject
	EmbeddedApplication<?> application;
	
	@Test
	void testItWorks() {
		Assertions.assertTrue(application.isRunning());
	}
	
}
