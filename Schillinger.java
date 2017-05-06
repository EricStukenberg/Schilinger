/*
  Western Washington University
  CSCI 491-3 Music Project: Schillinger System
  Winter 2017- Fall 2017
  Project members: Nate Caprile, Peter Lagow, Jack Mace, Eric Stukenberg
  Project Administrator: James Hearne
*/
import java.util.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Schillinger {
  public static void main(String[] args) {
    ArrayList<Character> music = new ArrayList<Character>();
     music = getFile();
     // call rhythm(music), harmony(music), melody(music), and counterpoint(music) here
  }
  /*
    prompts user for file and opens the file
    then returns the contents of the file as a
    String array.
  */
  public static ArrayList<Character> getFile(){
    ArrayList<Character> notes = new ArrayList<Character>();
    Scanner console = new Scanner(System.in);
    System.out.print("Enter a valid abc Music notation file.");
    String fileName = console.nextLine();
    File file = new File(fileName);
    BufferedReader fileReader = null;
    try {
      fileReader = new BufferedReader(new FileReader(file));
      String fileContents = null;
      while ((fileContents = fileReader.readLine()) != null) {
        int lineLen = fileContents.length();
        for(int i = 0; i < lineLen; i++) {
          notes.add(fileContents.charAt(i));
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
        try {
            if (fileReader != null) {
              fileReader.close();
            }
        } catch (IOException ioe) {
        }
    }
    return notes;
  }

}
