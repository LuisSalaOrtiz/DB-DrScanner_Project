package com.example.gilbertojimenezorench.myapp;

/**
 * Created by alber on 11/20/2016.
 */

public class PersonalInfo {

    int age;
    String email,mstatus, gender, phone, weight, height, blood;
    Address address;
    Healthcare health;



    public PersonalInfo(int age, String email, String mstatus, String gender, String phone, String weight, String height, String blood, Address address, Healthcare health) {

        this.age = age;
        this.email = email;
        this.mstatus = mstatus;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.weight = weight;
        this.height = height;
        this.blood = blood;
        this.health = health;

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Healthcare getHealth() {
        return health;
    }

    public void setHealth(Healthcare health) {
        this.health = health;
    }
}
