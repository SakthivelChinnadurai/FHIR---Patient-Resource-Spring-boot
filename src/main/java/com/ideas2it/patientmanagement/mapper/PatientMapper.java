package com.ideas2it.patientmanagement.mapper;

import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import org.hl7.fhir.r4.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Component;

import com.ideas2it.patientmanagement.dto.PatientDto;

/**
 *
 * @classname PatientMapper
 *
 * Mapper class convert the modeldto datas to model datas and
 * inversely model datas to modeldto datas
 *
 * @author : Sakthivel C
 *
 * @version : 1
 * @since 28/12/2022
 */
@Component
public class PatientMapper {

   /**
    * Coverts patientDto details to patient details
    *
    * @param patientDto contains patient dto Details
    * 
    * @return {@link Patient}
    */ 
	public Patient convertToFhir(PatientDto patientDto) {
		
	    Patient patient = new Patient();
		patient.addIdentifier().setSystem("urn:mrns").setValue(UUID.randomUUID().toString());
		patient.addTelecom().setSystem(ContactPointSystem.PHONE).setValue(patientDto.getMobileNumber());
		patient.addTelecom().setSystem(ContactPointSystem.EMAIL).setValue(patientDto.getEmail());
		patient.addName().setFamily(patientDto.getSureName()).addGiven(patientDto.getFirstName());
		patient.setActive(true);
		patient.setBirthDate(Date.from(patientDto.getDateOfBirth().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		switch (patientDto.getGender()) {
			case "male" -> patient.setGender(AdministrativeGender.MALE);
			case "female" -> patient.setGender(AdministrativeGender.FEMALE);
			case "others" -> patient.setGender(AdministrativeGender.OTHER);
		}
		return patient;
	}
}