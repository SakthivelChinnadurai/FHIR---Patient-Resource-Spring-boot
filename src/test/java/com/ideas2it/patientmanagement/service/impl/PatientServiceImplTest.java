package com.ideas2it.patientmanagement.service.impl;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import com.ideas2it.patientmanagement.dto.PatientDto;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PatientServiceImplTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private IGenericClient client;
    private Patient patient;
    private PatientDto patientDto;

    @BeforeEach
    void SetUp() {
        patient = new Patient();
        patientDto = new PatientDto();
        patientDto.setSureName("krishnan");
        patientDto.setFirstName("sakthi");
        patientDto.setEmail("sakthi@gmail.com");
        patientDto.setMobileNumber("9876543219");
        patient.addTelecom().setSystem(ContactPoint.ContactPointSystem.PHONE).setValue(patientDto.getMobileNumber());
        patient.addTelecom().setSystem(ContactPoint.ContactPointSystem.EMAIL).setValue(patientDto.getEmail());
        patient.addName().setFamily(patientDto.getSureName()).addGiven(patientDto.getFirstName());
    }

    @Test
    void createPatientTest() {

        when(client.create().resource(patient).execute()).thenReturn(new MethodOutcome().setCreated(true));
        var outcome = client.create().resource(patient).execute();
        assertTrue(outcome.getCreated());
    }

    @Test
    void getPatientTest() {

        when(client.read().resource(Patient.class).withId(12345L).execute()).thenReturn(patient);
        var patient = client.read().resource(Patient.class).withId(12345L).execute();
        assertEquals("sakthi", patient.getNameFirstRep().getGivenAsSingleString());
    }

    @Test
    void getPatientTest_1() {

        when(client.read().resource(Patient.class).withId(9999999L).execute())
                .thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,
                () -> client.read().resource(Patient.class).withId(9999999L).execute());
    }

    @Test
    void updatePatientTest() {

        long patientId = 12345;
        patient.addName().setFamily("tamil");
        patient.setId(new IdType(patientId));
        when(client.read().resource(Patient.class).withId(patientId).execute()).thenReturn(patient);
        when(client.update().resource(patient).execute()).thenReturn(new MethodOutcome().setResource(patient));
        var patient = client.read().resource(Patient.class).withId(patientId).execute();
        var outcome = client.update().resource(patient).execute();
        assertEquals(patientId, outcome.getResource().getIdElement().getIdPartAsLong());
    }

    @Test
    void updatePatientTest_1() {

        long patientId = 9999999;
        patient.addName().setFamily("tamil");
        patient.setId(new IdType(patientId));
        when(client.read().resource(Patient.class).withId(patientId).execute())
                .thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class,
                () -> client.read().resource(Patient.class).withId(patientId).execute());
    }

    @Test
    void deletePatientTest() {
        long patientId = 12345;
        MethodOutcome outcome = new MethodOutcome();
        outcome.setOperationOutcome(new OperationOutcome()
                .setIssue(List.of(new OperationOutcome.OperationOutcomeIssueComponent()
                .setDiagnostics("deleted"))));
        outcome.setStatusCode(200);
        when(client.delete().resourceById(new IdType("Patient", patientId))
                .execute()).thenReturn(outcome);
        outcome = client.delete().resourceById(new IdType("Patient", patientId))
                .execute();
        assertEquals(200, outcome.getResponseStatusCode());
    }
}