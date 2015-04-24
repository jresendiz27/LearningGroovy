/**
 *
 * @author pepo27
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcesosJava {

    public static void main(String args[]) throws IOException {
        try {
            System.out.println("Anota el comando del sistema a mostrar: ");
            String command = Utils.readString();
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }

        } catch (IOException | InterruptedException e1) {
        }

        System.out.println("Done");
    }
}