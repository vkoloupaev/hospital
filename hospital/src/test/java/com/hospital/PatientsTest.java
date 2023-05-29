package com.hospital;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import com.hospital.web.PatientDto;

public class PatientsTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void testPatientApi() throws Exception {
		setUp();
		createPatient(new PatientDto("John", "Gram", "1998-05-21"), "/patients/create");
		Integer patienId = createPatient(new PatientDto("Chris", "Brown", "1994-03-22"), "/patients/create");
		findAll("/patients");
		findById("/patients/", patienId);
		findPatientByFirstName("/patients/find/", "John");
	}

	private Integer createPatient(PatientDto patient, String uri) throws Exception {
		String inputJson = super.mapToJson(patient);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		Integer patientId = Integer.parseInt(content);
		assertTrue(patientId > 0);
		return patientId;
	}

	private String getResponse(String uri) throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		return mvcResult.getResponse().getContentAsString();
	}

	private PatientData[] getPatients(String uri) throws Exception {
		PatientData[] patientlist = super.mapFromJson(getResponse(uri), PatientData[].class);
		return patientlist;
	}

	private void findAll(String uri) throws Exception {
		PatientData[] patientList = getPatients(uri);
		assertTrue(patientList.length > 0);
	}

	private void findById(String uri, Integer id) throws Exception {
		@SuppressWarnings("deprecation")
		JSONObject patient = (JSONObject) (new JSONParser()).parse(getResponse(uri + id));
		assertTrue(patient.getAsNumber("id") == id);
	}

	private void findPatientByFirstName(String uri, String firstName) throws Exception {
		PatientData[] patients = getPatients(uri + firstName);
		List<PatientData> patientList = Arrays.asList(patients);
		assertTrue(patientList.stream().filter(t -> t.firstName.equalsIgnoreCase("John")).count() > 0);
		assertTrue(patientList.stream().filter(t -> !t.firstName.equalsIgnoreCase("John")).count() == 0);
	}
}