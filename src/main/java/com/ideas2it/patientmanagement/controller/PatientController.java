package com.ideas2it.patientmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.patientmanagement.dto.PatientDto;
import com.ideas2it.patientmanagement.service.PatientService;


import jakarta.validation.Valid;

/**
 * <p>
 * PatientController will get the patient detail Create
 * patient details, list all patients, Update and Delete patients
 * </p>
 *
 * @author sakthivel
 * 
 * @version 1.0
 * 
 * @since 28/12/2022
 */
@RestController
@RequestMapping("api/v1/patients")
public class PatientController {
	private final PatientService patientService;

	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}

	/**
	 * <p>
	 * Used to create patient details
	 * </p>
	 *
	 * @param patientDto contains patient dto details
	 * @return String
	 */
	@PostMapping
	public ResponseEntity<String> createResource(@RequestBody @Valid PatientDto patientDto) {

		return new ResponseEntity<String>(patientService.createPatient(patientDto), HttpStatus.CREATED);
	}

	/**
	 * <p>
	 * Used to get patient details
	 * </p>
	 *
	 * @param patientId contains patient id detail
	 * @return String
	 */
	@GetMapping("/{patientId}")
	public ResponseEntity<String> getResource(@PathVariable Long patientId) {

		return new ResponseEntity<String>(patientService.getPatient(patientId), HttpStatus.OK);
	}

	/**
	 * <p>
	 * Used to update patient details
	 * </p>
	 *
	 * @param patientDto contains patient dto details
	 * @return String
	 */
	@PutMapping("{patientId}")
	public ResponseEntity<String> updatePatient(@RequestBody PatientDto patientDto, @PathVariable Long patientId) {
		return new ResponseEntity<String>(patientService.updatePatient(patientDto, patientId), HttpStatus.OK);
	}

	/**
	 * <p>
	 * Used to remove patient details
	 * </p>
	 *
	 * @param patientId contains patient id detail
	 * @return String
	 */
	@DeleteMapping("{patientId}")
	public ResponseEntity<String> deleteResource(@PathVariable Long patientId) {
		return new ResponseEntity<String>(patientService.deletePatient(patientId), HttpStatus.OK);
	}
}