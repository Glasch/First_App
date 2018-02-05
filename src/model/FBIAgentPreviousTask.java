package model;


import java.time.LocalDate;
import java.util.Date;

/**
 * Copyright (c) Anton on 04.02.2018.
 */
public class FBIAgentPreviousTask {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String city;
    private FBIAgent fbiAgent;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FBIAgent getFbiAgent() {
        return fbiAgent;
    }

    public void setFbiAgent(FBIAgent fbiAgent) {
        this.fbiAgent = fbiAgent;
    }
}
