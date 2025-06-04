package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class FireStationNumber {
    private List<PersonDto> listPerson;
    private int adult;
    private int child;

    public FireStationNumber(){

    }

    public FireStationNumber(List<Person> listPerson, int adult, int child) {
        this.listPerson = listPerson;
        this.adult = adult;
        this.child = child;
    }

    public List<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<Person> listPerson) {
        this.listPerson = listPerson;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }
}
