import ui.Front;

import javax.swing.*;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class Launcher {

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.run();


    }

    private void run(){

        Front front = new Front();
        front.createMainMenu();

    }


}
