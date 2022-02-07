import java.io.*;
import java.util.Scanner;

public class FileManager {

    PrintWriter fileOut = null;
    Scanner fileIn = null;

    public boolean isAvailable(String user) {
        // Creates the file if it does not exist
        try {
            new PrintWriter(new FileOutputStream("UserData.txt", true));
        } catch (FileNotFoundException e) {
            System.out.println("File UserData.txt could not be opened or found.");
        }

        try {
            fileIn = new Scanner(new FileInputStream("UserData.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File UserData.txt could not fe read");
            System.out.println("or does not exist.");
        }

        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            if (line.charAt(0) == 'U') { // Make sure it only checks usernames, not passwords
                if (line.length() > 10) {
                    line = line.substring(10);
                }
                if (line.equals(user))
                    return false;
            }
        }
        return true;
    }


    public boolean isInfoInDatabase(String user, String pass) {
        try {
            fileIn = new Scanner(new FileInputStream("UserData.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File UserData.txt could not fe read");
            System.out.println("or does not exist.");
        }
        boolean r = false;
        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            line = line.substring(10);
            if (line.equals(user)) {
                String line2 = fileIn.nextLine();
                line2 = line2.substring(10);
                if (line2.equals(pass)) {
                    r = true;
                }
            }
        }

        fileIn.close();
        return r;
    }

    // Sign-Up
    public void streamOut(String user, String pass) {
        try {
            fileOut = new PrintWriter(new FileOutputStream("UserData.txt", true));
            fileOut.println("Username: " + user);
            fileOut.println("Password: " + pass);
            fileOut.println("--------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("File UserData.txt could not be opened or found.");
        }
        fileOut.close();
    }

    // Login
    public void streamIn(String user, String pass) {
        try {
            fileIn = new Scanner(new FileInputStream("UserData.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File UserData.txt could not fe read");
            System.out.println("or does not exist.");
        }

        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            line = line.substring(10);
            if (line.equals(user)) {
                String line2 = fileIn.nextLine();
                line2 = line2.substring(10);
                if (line2.equals(pass)) {
                    break;
                }
            }
        }
        fileIn.close();
    }
}
