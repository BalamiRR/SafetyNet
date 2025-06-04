package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Person;

import java.util.List;

public class ChildAlert {
    private List<Person> familyMembers;
    private List<ChildrenDto> children;

    public ChildAlert(){

    }

    public ChildAlert(List<Person> familyMembers, List<ChildrenDto> children) {
        this.familyMembers = familyMembers;
        this.children = children;
    }

    public List<Person> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<Person> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public List<ChildrenDto> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenDto> children) {
        this.children = children;
    }
}
