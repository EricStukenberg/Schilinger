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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Schillinger {
  public static void main(String[] args) {
     
     //music = getFile();
     abc music = getFile();
     rhythm rhyme = new rhythm();
     //System.out.println(music.getNotes());
     // call rhythm(music), harmony(music), melody(music), and counterpoint(music) here
     ArrayList<Character> newNotes = new ArrayList<Character>();
     newNotes = rhyme.reflection(music.getNotes());
     music.setNotes(newNotes);
     //System.out.println(music.getNotes());
     writeFile(music);
     System.out.println("\nFile called 'output.abc' has been created\n");
  }
  /*
    prompts user for file and opens the file
    then returns the contents of the file as a
    String array.
  */
  public static abc getFile(){
    ArrayList<Character> notes = new ArrayList<Character>();
    abc newFile = new abc();
    Scanner console = new Scanner(System.in);
    System.out.print("Enter a valid abc Music notation file.\n");
    String fileName = console.nextLine();
    File file = new File(fileName);
    BufferedReader fileReader = null;
    try {
      fileReader = new BufferedReader(new FileReader(file));
      String fileContents = null;
      while ((fileContents = fileReader.readLine()) != null) {
        /*int lineLen = fileContents.length();
        for(int i = 0; i < lineLen; i++) {
          notes.add(fileContents.charAt(i));
        }*/
        //System.out.println("About to add");
        newFile.add(fileContents);
        //System.out.println(".");
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
    return newFile;
  }
  
  public static void writeFile(abc file) {
    BufferedWriter bufwrite = null;
    FileWriter filewrite = null;
    
    try {
    
        filewrite = new FileWriter("output.abc");
        bufwrite = new BufferedWriter(filewrite);
        bufwrite.write(file.getHeader());
        
        ArrayList<Character> notes = file.getNotes();
        String allNotes = "";
        int size = notes.size();
        for (int i = 0; i < size; i++) {
            allNotes += notes.get(i);
        }
        bufwrite.write(allNotes);
        
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            if (bufwrite != null) {
                bufwrite.close();
            }
            
            if (filewrite != null) {
                filewrite.close();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
  }
            
}
