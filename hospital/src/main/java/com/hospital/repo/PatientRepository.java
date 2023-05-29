package com.hospital.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.hospital.domain.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
	/**
	 * Lookup a patient by the id.
	 *
	 * @param id of the patient.
	 * @return Patient if found, null otherwise.
	 */
	Optional<Patient> findById(@Param("id") Integer id);

	/**
	 * Lookup a patient by the firstName.
	 *
	 * @param firstName of the patient.
	 * @return Patients if found, null otherwise.
	 */
	List<Patient> findByFirstName(@Param("firstName") String firstName);

	/**
	 * Lookup all patients.
	 *
	 * @return Patients if found, null otherwise.
	 */
	List<Patient> findAll();

	// Not exposed by Spring Data REST
	@Override
	@RestResource(exported = false)
	<S extends Patient> S save(S s);

	// Not exposed by Spring Data REST
	@Override
	@RestResource(exported = false)
	<S extends Patient> Iterable<S> saveAll(Iterable<S> iterable);

	// Not exposed by Spring Data REST
	@Override
	@RestResource(exported = false)
	void deleteById(Integer integer);

	// Not exposed by Spring Data REST
	@Override
	@RestResource(exported = false)
	void delete(Patient guest);

	// Not exposed by Spring Data REST
	@Override
	@RestResource(exported = false)
	void deleteAll(Iterable<? extends Patient> iterable);

	// Not exposed by Spring Data REST
	@Override
	@RestResource(exported = false)
	void deleteAll();
}
