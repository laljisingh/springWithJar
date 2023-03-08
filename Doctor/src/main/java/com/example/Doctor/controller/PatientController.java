package com.example.Doctor.controller;

import com.example.Doctor.dao.DoctorRepository;
import com.example.Doctor.dao.PatientRepository;
import com.example.Doctor.model.Doctor;
import com.example.Doctor.model.Patient;
import com.example.Doctor.service.PatientService;
import io.micrometer.common.lang.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PatientService service;

    @PostMapping(value = "/patient")
    public String savePatient(@RequestBody String patientRequest) {

        JSONObject json = new JSONObject(patientRequest);
        Patient patient = setPatient(json);
        service.savePatient(patient);

        return "Saved patient";

    }

    private Patient setPatient(JSONObject json) {

        Patient patient = new Patient();

        patient.setPatientId(json.getInt("patientId"));
        patient.setPatientName(json.getString("patientName"));
        patient.setAge(json.getInt("age"));
        patient.setPhoneNumber(json.getString("phoneNumber"));
        patient.setDiseaseType(json.getString("diseaseType"));
        patient.setGender(json.getString("gender"));

        String currDate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        patient.setAdmitDate(currDate);

        String doctorId = json.getString("doctorId");
        Doctor doctor = doctorRepository.findById(Integer.valueOf(doctorId)).get();
        patient.setDoctorId(doctor);

        return patient;


    }


    @GetMapping(value = "/patient")
    public List<Patient> getPatients(@Nullable @RequestParam String doctorId,
                                     @Nullable @RequestParam String patientId) {
        List<Patient> patientL = new ArrayList<>();

        //both null- all patients
        //doctorId null- get by patient Id
        //patientId null- get all patients been treated by doctorI
        //ToDO: get all patients, Get a single patient by patient id, Get all patient who are been treated by doctorId
            if(null != doctorId && null == patientId){

                for (Patient patient : patientRepository.findAll()) {
                    if((patient.getDoctorId()).equals(doctorId)){
                        patientL.add(patient);
                    }
                }

            }else if(null != patientId  && null == doctorId ){

                for (Patient patient : patientRepository.findAll()) {
                    if((patient.getPatientId().toString()).equals(patientId)){
                        patientL.add(patient);
                    }
                }

            }else{
                patientRepository.findAll().forEach(patient -> patientL.add(patient));
            }

        return patientL;
    }

    @GetMapping(value = "/patient-list")
    public ResponseEntity getPatient(@Nullable @RequestParam String doctorId,
                                      @Nullable @RequestParam String patientId) {

        JSONArray patientDetails = service.getPatients();

        return new ResponseEntity<>(patientDetails.toString(), HttpStatus.OK);

        //both null- all patients
        //doctorId null- get by patient Id
        //patientId null- get all patients been treated by doctorId

        //ToDO: get all patients, Get a single patient by patient id, Get all patient who are been treated by doctorId


    }

}
