package teszt2;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class teszt2 {

    private Map<String, List> words = new TreeMap<>();

    public Map<String, List> getWords() {
        return words;
    }

    public void setWords(Map<String, List> words) {
        this.words = words;
    }



    void readFile(Path p) throws IllegalStateException {
        try {
            List<String> lines = Files.readAllLines(p);
            int lineNumber = 1;
            for (String line : lines) {
                String strings[] = line.split(" ");
                for(String s:strings) {
                    if(!words.containsKey(s))
                        words.put(s, new ArrayList());
                    words.get(s).add(lineNumber);
                }
                lineNumber++;
            }
        }catch(IOException ise)
        {
            throw new IllegalStateException("Can't read file!",ise);
        }
    }


    void writeFile(Path p) throws IOException {
        FileWriter outFile = new FileWriter(p.toString());
        for (H h : h) {
            char first = h.getIdentityNumber().charAt(0);
            if (first == '1' || first == '3') {
                outFile.write(h.getName());
                outFile.write(";");
                outFile.write(h.getIdentityNumber());
                outFile.write("\n");
            }
        }
        outFile.close();

    }

    public static void main(String[] args) {
        teszt2 teszt2 = new teszt2();


    }

}

