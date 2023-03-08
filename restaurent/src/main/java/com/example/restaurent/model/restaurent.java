package com.example.restaurent.model;

public class restaurent {
    private String restoName;
    private String restoAddress;
    private int number;
    private String speciality;

    private int totalStaff;

    restaurent() {}

    public restaurent(String restoName, String restoAddress, int number, String speciality, int totalStaff) {
        this.restoName = restoName;
        this.restoAddress = restoAddress;
        this.number = number;
        this.speciality = speciality;
        this.totalStaff = totalStaff;
    }

    public String getRestoName() {
        return restoName;
    }

    public void setRestoName(String restoName) {
        this.restoName = restoName;
    }

    public String getRestoAddress() {
        return restoAddress;
    }

    public void setRestoAddress(String restoAddress) {
        this.restoAddress = restoAddress;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getTotalStaff() {
        return totalStaff;
    }

    public void setTotalStaff(int totalStaff) {
        this.totalStaff = totalStaff;
    }

    @Override
    public String toString() {
        return "restaurent{" +
                "restoName='" + restoName + '\'' +
                ", restoAddress='" + restoAddress + '\'' +
                ", number=" + number +
                ", speciality='" + speciality + '\'' +
                ", totalStaff=" + totalStaff +
                '}';
    }
}
