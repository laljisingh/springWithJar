package com.example.Doctor.model;

import jakarta.persistence.*;

@Entity
@Table(name="patient_table")
public class Patient {
    @Id
    @Column(name="patient_Id")
    private Integer patientId;
    @Column(name="patient_Name")
    private String patientName;
    @Column(name = "gender")
    private String gender;
    @Column(name="patient_age")
    private Integer age;
    @Column(name="patient_phone_number")
    private String phoneNumber;
    @Column(name="patient_disease")
    private String diseaseType;
    @Column(name = "admit_date")
    private String admitDate;

    @JoinColumn(name="doctor_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctorId;

    public Patient() {

    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(String admitDate) {
        this.admitDate = admitDate;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

}
