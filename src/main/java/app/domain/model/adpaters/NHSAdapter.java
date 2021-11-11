package app.domain.model.adpaters;

import app.domain.shared.Constants;
import com.nhs.report.Report2NHS;

import java.io.File;

public class NHSAdapter {
    public NHSAdapter(){
    }

    public void write(String information){
        File file1 = new File(Constants.NHS_PATH);
        file1.mkdir();

        try {
            Report2NHS.writeUsingFileWriter(information);
        }catch (Exception error){
            System.out.println("folder not found");
        }
    }
}
