package model;

import java.util.ArrayList;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class FBIAgent {
    private int id;
    private String surname;
    private String name;
    private Boolean sex;
    private String nickname;
    private boolean physicalPower;
    private boolean mentalStrength;
    private boolean patriotism;
    private FBIAgentStatus status;
    private byte[] image;
    private ArrayList<FBIAgentPreviousTask> previousTasks = new ArrayList <>() ;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isPhysicalPower() {
        return physicalPower;
    }

    public void setPhysicalPower(boolean physicalPower) {
        this.physicalPower = physicalPower;
    }

    public boolean isMentalStrength() {
        return mentalStrength;
    }

    public void setMentalStrength(boolean mentalStrength) {
        this.mentalStrength = mentalStrength;
    }

    public boolean isPatriotism() {
        return patriotism;
    }

    public void setPatriotism(boolean patriotism) {
        this.patriotism = patriotism;
    }

    public FBIAgentStatus getStatus() {
        return status;
    }

    public void setStatus(FBIAgentStatus status) {
        this.status = status;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList <FBIAgentPreviousTask> getPreviousTasks() {
        return previousTasks;
    }

    public void setPreviousTasks(ArrayList <FBIAgentPreviousTask> previousTasks) {
        this.previousTasks = previousTasks;
    }
}
