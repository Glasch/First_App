package model;

/**
 * Copyright (c) Anton on 20.02.2018.
 */
public class MiniAgent {

private int id;
private String surname;
private String name;
private FBIAgentStatus status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public FBIAgentStatus getStatus() {
        return status;
    }

    public void setStatus(FBIAgentStatus status) {
        this.status = status;
    }
}
