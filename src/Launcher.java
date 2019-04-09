import ui.MainController;
import ui.MainFront;

/**
 * Copyright (c) Anton on 18.01.2018.
 */
public class Launcher {

    public static void main(String[] args) {

        Launcher launcher = new Launcher();
        launcher.run();


    }

    private void run(){

        MainController mainController = new MainController();
        mainController.showGUI();
        System.out.println("mainController = "+8);

    }


}
