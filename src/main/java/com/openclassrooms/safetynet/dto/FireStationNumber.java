package com.openclassrooms.safetynet.dto;
import java.util.List;

public class FireStationNumber {
    private List<PersonDto> listPerson;
    private int adult;
    private int child;

    public FireStationNumber(){

    }

    public FireStationNumber(List<PersonDto> listPerson, int adult, int child) {
        this.listPerson = listPerson;
        this.adult = adult;
        this.child = child;
    }

    public List<PersonDto> getListPerson() {
        return listPerson;
    }

    public void setListPerson(List<PersonDto> listPerson) {
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
