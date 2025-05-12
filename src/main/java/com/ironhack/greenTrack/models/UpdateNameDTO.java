package com.ironhack.greenTrack.models;

public class UpdateNameDTO {
    private String newName;

    public String getNewName() {return newName;}

    public void setNewName(String newName) {this.newName = newName;}



    public String toString(){

        return "Renamed: " +newName;
    }

}
