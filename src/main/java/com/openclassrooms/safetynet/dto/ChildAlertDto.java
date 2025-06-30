package com.openclassrooms.safetynet.dto;
import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class ChildAlertDto {
    private List<Person> familyMembers;
    private List<ChildDto> children;

    public ChildAlertDto(){

    }

    public List<Person> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<Person> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public List<ChildDto> getChildren() {
        return children;
    }

    public void setChildren(List<ChildDto> children) {
        this.children = children;
    }
}
