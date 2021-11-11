/*
package app.ui.console;


import app.controller.CreateClientController;
import app.controller.ImportTestsController;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ImportTestsUI implements Runnable {

    ImportTestsController itc;

    public ImportTestsUI() {
        itc = new ImportTestsController();
    }

    public void run() {

        System.out.println("\n\nINFORMATION REQUIRED\n--------------------");

        String path = Utils.readLineFromConsole("Path to the file: ");


        try {
            itc.readFile(path);
        } catch (IOException i) {
            System.out.println("couldn't find the file with this path");
        } catch (Exception e) {
            System.out.println("Error while reading the file");
        }


        System.out.println("file read with success");
    }
}*/
