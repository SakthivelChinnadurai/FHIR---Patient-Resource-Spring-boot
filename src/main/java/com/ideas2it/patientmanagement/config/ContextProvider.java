package com.ideas2it.patientmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ca.uhn.fhir.context.FhirContext;

/**
 * <p>
 * ContextProvider will provide fhircontext
 * </p>
 *
 * @author sakthivel
 *
 * @version 1.0
 *
 * @since 28/12/2022
 */
@Configuration
public class ContextProvider {

	@Bean
	public FhirContext ContectProvider() {
		return FhirContext.forR4();
	}
}
