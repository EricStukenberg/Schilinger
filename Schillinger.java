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
    
    static String[] userQuestions = {
        "How would you like to change this file?\n\t1: Rhythm\n\t2: Melody\n\t3: Both\n\n",
        "Choose either: \n\t1: Rhythm\n\t2: Melody\n\t3: Both\n\n",
        "How do you want the rhythm to change?\n\t1: Reflection\n\t2: Circular Rotation\n\n",
        "Choose either: \n\t1: Reflection\n\t2: Circular Rotation\n\n",
        "How do you want the melody to change?\n\t1: Binary\n\t2: Fibonacci\n\n",
        "Choose either: \n\t1: Binary\n\t2: Fibonacci\n\n",
        "What would you like to do?\n\t1: Change the file again\n\t2: Export\n\n",
        "Choose either: \n\t1: Change the file again\n\t2: Export\n\n"};
    
    
    
    public static void main(String[] args) {
                
        
        abc music = getFile();        
        //System.out.println("Got music file");
        rhythm rhyme = new rhythm();
        melody mldy = new melody();
        
        boolean done = false;
        
        abc newFile = new abc(music);
        //System.out.println("Copied music");
        while (!done) {
            int choice = getUserInfo(0);
            int subChoice;
            
            //System.out.println("Choice in main: '"+choice+"'");
            
            /* This means that we are going to change the Rhythm */
            if (choice == 1) {
                //System.out.println("All bout that ryhtmCHeck");
                subChoice = getUserInfo(2);
                //System.out.println("All bout that ryhtm");
                if (subChoice == 1) {
                    //System.out.println("We are reflectingin");
                    newFile.setNotes(rhyme.reflection(music));
                    newFile.flipBars();
                } else if (subChoice == 2) {
                    //newFile.setNotes(rhyme.circularRotation(music));
                } else {
                    System.out.println("Should not be here rhy");
                    /* Do Nothing */
                }
            
            /* This means that we are going to change the Melody */
            } else if (choice == 2) {
                subChoice = getUserInfo(4);
                
                if (subChoice == 1) {
                    newFile.setNotes(mldy.binary(music));
                } else if (subChoice == 2) {
                    newFile.setNotes(mldy.fibonacci(music));
                } else {
                    System.out.println("Should not be here mel");
                    /* Do Nothing */
                }
            } else {
                System.out.println("Shouldn't be here");
                /* Do Nothing */
            }
            
            choice = getUserInfo(6);
            if (choice == 1) {
                /* Don't have to do anything since we will run this again */
            } else if (choice == 2) {
                writeFile(newFile, "output.abc");
                System.out.println("\nFile called 'output.abc' has been created\n");
                done = true;
            } else {
                /* Do nothing as of now... */
            }
        }
    }
    
    /*
     * Gets user choice
     * Input: The location of the beginning of the question set
     */
     public static int getUserInfo(int num) {
        //Scanner console = new Scanner(System.in);
        int choice = 0;
        boolean ans = false;
        System.out.print(userQuestions[num]);
        
        while (!ans) {
            Scanner console = new Scanner(System.in);
            while (console.hasNextLine()) {
                if (console.hasNextInt()) {
                    choice = console.nextInt();
                    //System.out.println("Choice: "+choice);
                    if (choice == 1 || choice == 2) {
                        ans = true;
                        break;
                    }
                } else {
                    console.nextLine();
                }
                if (!ans) {
                    System.out.print(userQuestions[num+1]);
                }
            }
        }
        return choice;
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
     * For testing purposes, this was created to see if the file that we create
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
