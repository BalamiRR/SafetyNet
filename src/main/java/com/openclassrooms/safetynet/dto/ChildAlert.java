package com.openclassrooms.safetynet.dto;
import java.util.List;

public class ChildAlert {
    private List<PersonDto> familyMembers;
    private List<ChildrenDto> children;

    public ChildAlert(){

    }

    public ChildAlert(List<PersonDto> familyMembers, List<ChildrenDto> children) {
        this.familyMembers = familyMembers;
        this.children = children;
    }

    public List<PersonDto> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<PersonDto> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public List<ChildrenDto> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenDto> children) {
        this.children = children;
    }
}
