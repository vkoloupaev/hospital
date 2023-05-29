package com.hospital.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hospital.domain.Patient;
import com.hospital.repo.PatientRepository;
import com.hospital.web.PatientDto;

@Service
public class PatientService {
	
	@Autowired
	private Environment env;

	private PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	/**
	 * Create a new Guest Object and persist it to the Database.
	 *
	 * @param patientDto
	 * @throws ParseException
	 */
	public Patient createPatient(PatientDto patientDto) throws DateTimeParseException {
		return patientRepository.save(new Patient(patientDto.getFirstName(), patientDto.getLastName(), LocalDate.parse(
				patientDto.getBirthday(), DateTimeFormatter.ofPattern(env.getProperty("spring.jackson.date-format")))));
	}
}
