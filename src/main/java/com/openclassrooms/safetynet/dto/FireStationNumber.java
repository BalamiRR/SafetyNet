package com.openclassrooms.safetynet.dto;
import java.util.Set;

public class FireStationNumber {
    private Set<PersonDto> listPerson;
    private int adult;
    private int child;

    public FireStationNumber(){

    }

    public FireStationNumber(Set<PersonDto> listPerson, int adult, int child) {
        this.listPerson = listPerson;
        this.adult = adult;
        this.child = child;
    }

    public Set<PersonDto> getListPerson() {
        return listPerson;
    }

    public void setListPerson(Set<PersonDto> listPerson) {
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
