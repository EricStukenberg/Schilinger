import java.lang.*;
import java.io.*;
import java.util.*;

public class melody {
    public melody() {
        /* DO nothing */
    }
   // creates melody table           key  1     2     3     4     5     6     7     8
   public String[][] melodyTable = {{"C", "C",  "D",  "E",  "F",  "G",  "A",  "B",  "C'",},
                                    {"D", "D",  "E",  "^F", "G",  "A",  "B",  "^C", "D",},
                                    {"E",	"E",	"^F",	"^G",	"A",	"B",	"^C",	"^D",	"E",},
                                    {"F",	"F",	"G",	"A",  "B",	"C",	"D", 	"E",	"F",},
                                    {"G",	"G",	"A",	"B",	"C",	"D",	"E",	"^F",	"G",},
                                    {"A",	"A",	"B",	"^C",	"D",	"E",	"^F",	"^G",	"A",},
                                    {"B",	"B",	"^C",	"^D",	"E",	"^F",	"^G",	"^A",	"B",},
                                    {"C#/Db1",	"^C/ D",	"^D/ E",	"F",	"^F, G",	"^G/ A",	"^A/ B",	"C",	"^C/ D",},
                                    {"D#/Eb1",	"^D/ E",	"F",	"G",	"^G/ A",	"^A/ B",	"C",	"D",	"^D/ E",},
                                    {"F#/Gb1",	"^F/ G",	"^G/ A",	"^A/ B",	"B/ C",	"^C/ D",	"^D/ E",	"^E/F",	"^F/ G",},
                                    {"G#/Ab1",	"^G/ A",	"^A/ B",	"C",	"^C/ D",	"^D/ E",	"F",	"G",	"^G/ A",},
                                    {"A#/Bb1",	"^A/ B",	"C",	"D",	"^D/ E",	"F",	"G",	"A",	"^A/ B",}};

   
   /* 
      changes melody based on binary sequence
   */
   public ArrayList<note> binary(abc file) {
      String header = file.getHeader();
      // gets Key (the key will always be last element of header string)
      String key = Character.toString(header.charAt(header.length() - 2));
      // finds the in the table (key location
      int keyLoc = 0;
      while(!(key.equals(melodyTable[keyLoc][0]))) {
         keyLoc++;
      }
      
        ArrayList<note> notes = file.getNotes();
        int size = notes.size();
        ArrayList<note> newNotes = new ArrayList<note>();
        
        ArrayList<Character> tempPre = new ArrayList<Character>(); 
        ArrayList<Character> tempNote = new ArrayList<Character>();
        ArrayList<Character> tempPost = new ArrayList<Character>();
        
        note currNote;

        
        int binary = 1;
        for (int i = 0; i < size; i++) {
            String newNote = melodyTable[keyLoc][binary];
            int noteLen = newNote.length();
            currNote = notes.get(i);
            
            //System.out.printf("oldNote: |%s|\n", currNote.toString());
            
            tempPre = currNote.getPre();
            tempNote = currNote.getNote();
            for(int j = 0; j < noteLen; j++) {
               tempNote.set(j, newNote.charAt(j));
            }
            tempPost = currNote.getPost();
            
            //System.out.printf("changed Note: |%s|\n\n", tempNote.toString());
            
            newNotes.add(new note(tempPre, tempNote, tempPost));
            binary = binary + binary;
            if(binary > 8) {
               binary = 1;
            }
        }
        
        return newNotes;
        
      
   }

}