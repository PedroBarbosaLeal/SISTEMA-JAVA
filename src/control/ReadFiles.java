package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFiles {

    public static File file3;

    public static void lerInterface() {
        try {
            ReadFiles.readinterface(file3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final void readinterface(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String data = null;
        while ((data = reader.readLine()) != null) {
            view.Menu.inter = data;
        }
        fileReader.close();
        reader.close();
    }
}

