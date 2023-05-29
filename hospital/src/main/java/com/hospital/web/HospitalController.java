package com.hospital.web;

import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.domain.Patient;
import com.hospital.repo.PatientRepository;
import com.hospital.service.PatientService;

@RestController
public class HospitalController {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private PatientService patientService;

	/**
	 * Create a Patient.
	 *
	 * @param patientDto
	 * @throws ParseException
	 */
	@PostMapping("/patients/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Integer postPatient(@RequestBody PatientDto patientDto) throws DateTimeParseException {
		return patientService.createPatient(patientDto).getId();
	}
	
	/**
	 * Find all Patients.
	 *
	 */
	@GetMapping(path = "/patients", produces = "application/json")
	public List<Patient> findAll() {
		return patientRepository.findAll();
	}
	
	/**
	 * Find a Patient by id.
	 *
	 * @param id
	 */
	@GetMapping(path = "/patients/{id}", produces = "application/json")
	public Optional<Patient> findPatientById(@PathVariable(value = "id") Integer id) {
		return patientRepository.findById(id);
	}

	/**
	 * Find a Patient by First Name.
	 *
	 * @param firstName
	 */
	@GetMapping(path = "/patients/find/{firstName}", produces = "application/json")
	public List<Patient> findPatientByFirstName(@PathVariable(value = "firstName") String firstName) {
		return patientRepository.findByFirstName(firstName);
	}
}
