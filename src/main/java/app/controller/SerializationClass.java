package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;

import java.io.*;

public class SerializationClass {
    private Company company;
    private App app;

    public SerializationClass() {
        app = App.getInstance();
        this.company = app.getCompany();
        File file1 = new File(Constants.SERIALIZATION_DIRECTORY_PATH);
        file1.mkdir();
    }

    public boolean checkFileEmpty() {
        File file = new File(Constants.SERIALIZATION_COMPANY_PATH);
        return file.length() == 0;
    }

    public void serializeCompany() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Constants.SERIALIZATION_COMPANY_PATH));
            this.company = app.getCompany();
            out.writeObject(company);
            out.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void deserializeCompany() {
        if (!checkFileEmpty()) {
            Company comp;
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(Constants.SERIALIZATION_COMPANY_PATH));
                comp = (Company) in.readObject();
                app.setCompany(comp);
                in.close();
            } catch (IOException i) {
                System.out.println("IOException");
                i.printStackTrace();

            } catch (ClassNotFoundException i) {
                System.out.println("ClassNotFoundException");
                i.printStackTrace();

            }
        } else {
            System.out.println("Serialization file empty");
        }
    }
}
