import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordInputReader {
    private ArrayList<String> passwords;

    public void readPasswords(String fileName) {
        try {
            ArrayList<String> passwords = new ArrayList<>();
            FileReader input = new FileReader(fileName);
            Scanner scanner = new Scanner(input);
            while(scanner.hasNextLine()){
                passwords.add(scanner.nextLine());
            }
            this.passwords = passwords;
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }
    public ArrayList<String> getPasswords() {
        return passwords;
    }
}
