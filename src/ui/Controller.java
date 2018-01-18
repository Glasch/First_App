package ui;

import model.DBManager;
import model.FBIAgent;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class Controller {

    private Front front;

    public Controller(Front front) {
        this.front = front;
    }

    public void onAddButtonClick() {

        FBIAgent fbiAgent = new FBIAgent();
        fbiAgent.setSurname(front.getSurnameTextField().getText());
        System.out.println(fbiAgent.getSurname());

        DBManager dbManager = new DBManager();
        try {
            dbManager.saveAgent(fbiAgent);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
