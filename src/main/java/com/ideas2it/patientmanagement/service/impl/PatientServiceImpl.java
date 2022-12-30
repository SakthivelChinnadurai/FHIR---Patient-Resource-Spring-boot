/*
 * Copyright 2022, ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.patientmanagement.service.impl;

import ca.uhn.fhir.rest.gclient.ICreateTyped;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.patientmanagement.dto.PatientDto;
import com.ideas2it.patientmanagement.exception.DataNotFoundException;
import com.ideas2it.patientmanagement.mapper.PatientMapper;
import com.ideas2it.patientmanagement.service.PatientService;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;

/**
 * <p>
 * PatientServiceImpl used for crud operations and business logics for Patient
 * module
 * </p>
 *
 * @author sakthivel
 * @version 1.0
 * @since 28/12/2022
 */
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;
    private final FhirContext context;

    @Autowired
    public PatientServiceImpl(PatientMapper patientMapper, FhirContext context) {
        this.patientMapper = patientMapper;
        this.context = context;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createPatient(PatientDto patientDto) {
        var client = context.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        var patient = patientMapper.convertToFhir(patientDto);
        MethodOutcome outcome = client.create().resource(patient).execute();
        return outcome.getId().getIdPart();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPatient(Long patientId) {
        Patient patient = null;
        var client = context.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        try {
            patient = client.read().resource(Patient.class).withId(patientId).execute();
        } catch (ResourceNotFoundException exception) {
            throw new DataNotFoundException("Resource not found");
        }
        return context.newJsonParser().encodeResourceToString(patient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String updatePatient(PatientDto patientDto, Long patientId) {

        Patient patient;
        MethodOutcome outcome;
        var client = context.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        try {
            client.read().resource(Patient.class).withId(patientId).execute();
            patient = patientMapper.convertToFhir(patientDto);
            patient.setId(String.valueOf(patientId));
            outcome = client.update().resource(patient).execute();
        } catch (ResourceNotFoundException exception) {
            throw new DataNotFoundException("Resource not found");
        }
        return context.newJsonParser().encodeResourceToString(outcome.getResource());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deletePatient(Long patientId) {

        var client = context.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        var outcome = client.delete().resourceById(new IdType("Patient", patientId))
                  .encodedJson().execute();
        return context.newJsonParser().encodeResourceToString(outcome.getOperationOutcome());
    }
}