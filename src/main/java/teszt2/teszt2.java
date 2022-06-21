package teszt2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class teszt2 {

    private Map<String, List> words = new TreeMap<>();

    void readFile(Path p) throws IllegalStateException {
        try {
            List<String> lines = Files.readAllLines(p);
            for (String line : lines) {
                String s[] = line.split(" ");
                humans.add(new Human(s[0], s[1]));
            }
        }catch(IOException ise)
        {
            throw new IllegalStateException("Can't read file!",ise);
        }
    }


    void writeFile(Path p) throws IOException{
        FileWriter outFile = new FileWriter(p.toString());
        for(H h:h) {
            char first=h.getIdentityNumber().charAt(0);
            if(first=='1'||first=='3') {
                outFile.write(h.getName());
                outFile.write(";");
                outFile.write(h.getIdentityNumber());
                outFile.write("\n");
            }
        }
        outFile.close();
    }

