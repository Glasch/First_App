package model;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class FBIAgent {
  private   String surname;
  private   String name;
  private   Boolean sex;
  private   String nickname;
  private   boolean physicalPower;
  private   boolean mentalStrenght;
  private   boolean patriotism;
  private   FBIAgentStatus status;

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

    public boolean isMentalStrenght() {
        return mentalStrenght;
    }

    public void setMentalStrenght(boolean mentalStrenght) {
        this.mentalStrenght = mentalStrenght;
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
}
