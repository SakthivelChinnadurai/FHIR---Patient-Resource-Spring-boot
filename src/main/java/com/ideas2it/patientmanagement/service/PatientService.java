/*
 * Copyright 2022, ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ideas2it.patientmanagement.service;

import com.ideas2it.patientmanagement.dto.PatientDto;

/**
 * <p>
 * This {@Code PatientService} interface used for crud operations and business
 * logics
 * </p>
 *
 * @author sakthivel
 * 
 * @version 1.0
 * 
 * @since 28/12/2022
 */
public interface PatientService {

	/**
	 * <p>
	 * Used to create patient details
	 * </p>
	 * 
	 * @param patientDto contains patient dto details
	 * 
	 * @return {@link PatientDto}
	 */
	public String createPatient(PatientDto patientDto);
	
	/**
	 * <p>
	 * Used to gets patient details
	 * </p>
	 * 
	 * @param patientId contains patient id detail
	 * 
	 * @return String
	 */
	public String getPatient(Long patientId);

	/**
	 * <p>
	 * Used to update patient details
	 * </p>
	 *
	 * @param patientDto contains patient dto details
	 *
	 * @return String
	 */
	public String updatePatient(PatientDto patientDto, Long patientId);

	/**
	 * <p>
	 * Used to remove patient details
	 * </p>
	 *
	 * @param patientId contains patient id detail
	 *
	 * @return String
	 */
	public String deletePatient(Long patientId);
}
