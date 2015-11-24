
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author ankurpandit
 */
public class Convert {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        File file = new File(args[0]);
        
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = line.replaceAll("\"", "");
                String[] lineWords = line.split(" ");
                
                try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.csv", true))) {
                for (int i = 0; i < lineWords.length - 1; i++) {
                        bw.write(lineWords[i]+",");
                    }
                    bw.write(lineWords[lineWords.length - 1]);
                    bw.newLine();
                    bw.flush();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
