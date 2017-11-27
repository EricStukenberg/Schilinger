/*
  Western Washington University
  CSCI 491-3 Music Project: Schillinger System
  Winter 2017- Fall 2017
  Project members: Nate Caprile, Peter Lagow, Eric Stukenberg
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
        abc newFile = new abc();
        newFile.setHeader(music.copyHeader());
        ArrayList<note> newNotes = new ArrayList<note>();
        newNotes = rhyme.reflection(music);
        newFile.setNotes(newNotes);
        
        // melody
        melody mldy = new melody();
        abc newMelodyFile = new abc();
        newMelodyFile.setHeader(music.copyHeader());
        ArrayList<note> newNotesMelody = new ArrayList<note>();
        newNotesMelody = mldy.binary(music);
        newMelodyFile.setNotes(newNotesMelody);
        //System.out.println(music.get('T'));
        writeFile(newFile, "output1.abc");
        writeFile(newMelodyFile, "output2.abc");
        System.out.println("\nFiles called 'output1.abc' and 'output2.abc' have been created\n");
        //compare(newFile);
    }
  /*
   * prompts user for file and opens the file
   *  then returns the contents of the file as a
   *  String array.
   */
    public static abc getFile(){
        ArrayList<Character> notes = new ArrayList<Character>();
        abc newFile = new abc();
        boolean isSuccessful = false;
    
        while (!isSuccessful) {
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
                isSuccessful = true;
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                System.out.println("File not found, enter a valid filename\n");
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
        }
    
        return newFile;
    }
    
    /*
     * Write to a created file
     */
    public static void writeFile(abc file, String fileName) {
        BufferedWriter bufwrite = null;
        FileWriter filewrite = null;

        try {

            filewrite = new FileWriter(fileName);
            bufwrite = new BufferedWriter(filewrite);
    
            String entireFile = file.toString();
            bufwrite.write(entireFile);
    
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
    
    
    /*
     * For testing purposes, thi8s was created to see if the file that we create
     *  with our algorithms matches the correct answer
     *
     * With the newest changes, this will not work
     */
    public static void compare(abc algFile) {
        System.out.println("Comparing algorithmic output with correct output");
        abc corFile = getFile();
        ArrayList<note> algOut = algFile.getNotes();
        ArrayList<note> corOut = corFile.getNotes();

        boolean same = true;

        int algLen = algOut.size();
        int corLen = corOut.size();
        int len = algLen;

        if (algLen != corLen) {
            same = false;
            System.out.printf("The length of all of the Notes is off.\n\talgLen = %s\n\tcorLen = %s\n", algLen, corLen);
            if (corLen < algLen)
                len = corLen;
        }

        for (int i = 0; i < len; i++) {
            note algNote = algOut.get(i);
            note corNote = corOut.get(i);

            if (compareNote(algNote.getPre(),corNote.getPre()) || compareNote(algNote.getNote(),corNote.getNote())
                || compareNote(algNote.getPost(),corNote.getPost())) {
                System.out.printf("Notes at %d, are not the same.\n", i);
                System.out.printf("Was '%s', should be '%s'\n", algNote.toString(), corNote.toString());
                same = false;
            }
        }
        if (same) {
            System.out.println("Both were the same");
        }
    }
  
    public static boolean compareNote(ArrayList<Character> alg, ArrayList<Character> cor) {
        int size = alg.size();
        if (size != cor.size()) {
            return false;
        }

        char algChar;
        char corChar;
        for (int i = 0; i < size; i++) {
        algChar = alg.get(i);
            corChar = cor.get(i);
            if (algChar != corChar) {
                return false;
            }
        }
        return true;
    }
        
            
}
