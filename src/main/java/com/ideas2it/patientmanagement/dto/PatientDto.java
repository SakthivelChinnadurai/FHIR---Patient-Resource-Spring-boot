package com.ideas2it.patientmanagement.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

@Getter
@Setter
public class PatientDto {

	private String patientId;
	@NotNull
	private String sureName;
	@NotNull
	private String firstName;
	@NotNull
	private String mobileNumber;
	@NotNull
	@Email
	private String email;
	@NotNull
	@Pattern(regexp = "male|female|others", message = "Enter valid format EX: male, female, others")
	private String gender;
	@NotNull
	@Past
	private LocalDate dateOfBirth;
}
