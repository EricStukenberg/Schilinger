//import java.lang.Character.isLetter;
import java.lang.*;
import java.io.*;
import java.util.*;

public class melody {
    public melody() {
        /* DO nothing */
    }
   // creates melody table           
   public String[][] melodyTable = {
       /*    key        1        2        3        4        5        6        7       8  */
            {"C",      "C",     "D",     "E",     "F",     "G",     "A",     "B",    "C'"},
            {"D",      "D",     "E",     "^F",    "G",     "A",     "B",     "^C",   "D"},
            {"E",      "E",	    "^F",    "^G",	  "A",     "B",	    "^C",    "^D",	 "E"},
            {"F",      "F",	    "G",     "A",     "B",     "C",	    "D",     "E",	 "F"},
            {"G",      "G",	    "A",     "B",	  "C",     "D",	    "E",     "^F",	 "G"},
            {"A",      "A",	    "B",     "^C",	  "D",     "E",	    "^F",    "^G",	 "A"},
            {"B",      "B",	    "^C",    "^D",	  "E",     "^F",	"^G",    "^A",	 "B"},
            {"C#/Db1", "^C/ D",	"^D/ E", "F",	  "^F, G", "^G/ A",	"^A/ B", "C",	 "^C/ D"},
            {"D#/Eb1", "^D/ E",	"F",	 "G",	  "^G/ A", "^A/ B",	"C",	 "D",	 "^D/ E"},
            {"F#/Gb1", "^F/ G",	"^G/ A", "^A/ B", "B/ C",  "^C/ D",	"^D/ E", "^E/F", "^F/ G"},
            {"G#/Ab1", "^G/ A",	"^A/ B", "C",	  "^C/ D", "^D/ E",	"F",	 "G",	 "^G/ A"},
            {"A#/Bb1", "^A/ B",	"C",	 "D",	  "^D/ E", "F",	    "G",	 "A",	 "^A/ B"}
            };

   
   /* 
      changes melody based on binary sequence
   */
   public ArrayList<note> binary(abc file) {
      String header = file.getHeader();
      // gets Key (the key will always be last element of header string)
      String key = Character.toString(header.charAt(header.length() - 2));
      // finds the key in the table (key location)
      int keyLoc = 0;
      while(!(key.equals(melodyTable[keyLoc][0]))) {
         keyLoc++;
      }
      
        ArrayList<note> notes = file.getNotes();
        int size = notes.size();
        ArrayList<note> newNotes = new ArrayList<note>();
        
        ArrayList<Character> tempPre;// = new ArrayList<Character>(); 
        ArrayList<Character> tempNote;// = new ArrayList<Character>();
        ArrayList<Character> tempPost;// = new ArrayList<Character>();
        //String[] newNote = melodyTable[keyLoc];
        
        note currNote;

        
        int binary = 1;
        int power = 0;
        for (int i = 0; i < size; i++) {
            tempNote = new ArrayList<Character>();
            String newNote = melodyTable[keyLoc][binary];
            int noteLen = newNote.length();
            currNote = notes.get(i);
            
            //System.out.printf("oldNote: |%s|\n", currNote.toString());
            
            tempPre = new ArrayList<Character>(currNote.getPre());
            tempPost = new ArrayList<Character>(currNote.getPost());
            
            int letter = 0;
            tempNote = new ArrayList<Character>(currNote.getNote());
            while(!Character.isLetter(tempNote.get(letter))) {
                letter++;
            }
            /* Means it's a rest */
            if (tempNote.get(letter) == 'z') {
                /*Do nothing */
            } else {
                //System.out.println("Old: " +tempNote.get(letter)+ " new: "+ newNote);
                newNote = caseHelper(letter, tempNote, newNote);
                //System.out.println("Old: " +tempNote.get(letter)+ " new: "+ newNote);
                tempNote.set(letter, newNote.charAt(0)); 
                for(int j = 1; j < noteLen; j++) {
                   tempNote.add(j+letter, newNote.charAt(j));
                }
                binary += (int) Math.pow(2, power);
                power += 1;
                if(binary > 8) {
                   binary = 1;
                   power = 0;
                }
            }
            //System.out.printf("changed Note: |%s|\n\n", tempNote.toString());
            newNotes.add(new note(tempPre, tempNote, tempPost));
            
        }
        
        return newNotes;
        
      
   }


   /* 
      changes melody based on fibonacci sequence
   */
   public ArrayList<note> fibonacci(abc file) {
      String header = file.getHeader();
      // gets Key (the key will always be last element of header string)
      String key = Character.toString(header.charAt(header.length() - 2));
      // finds the key in the table (key location)
      int keyLoc = 0;
      while(!(key.equals(melodyTable[keyLoc][0]))) {
         keyLoc++;
      }
      
        ArrayList<note> notes = file.getNotes();
        int size = notes.size();
        ArrayList<note> newNotes = new ArrayList<note>();
        
        ArrayList<Character> tempPre;// = new ArrayList<Character>(); 
        ArrayList<Character> tempNote;// = new ArrayList<Character>();
        ArrayList<Character> tempPost;// = new ArrayList<Character>();
        //String[] newNote = melodyTable[keyLoc];
        
        note currNote;

        
        int fibPrev = 0;
        int fibCur = 0;
        int total = 1;
        //int start = -1;
        for (int i = 0; i < size; i++) {
            //System.out.printf("Beg:  fibCur: %d, fibPrev: %d total: %d\n", fibCur, fibPrev, total); 
            tempNote = new ArrayList<Character>();
            String newNote = melodyTable[keyLoc][total];
            int noteLen = newNote.length();
            currNote = notes.get(i);
            
            //System.out.printf("oldNote: |%s|\n", currNote.toString());
            
            tempPre = new ArrayList<Character>(currNote.getPre());
            tempPost = new ArrayList<Character>(currNote.getPost());
            
            int letter = 0;
            tempNote = new ArrayList<Character>(currNote.getNote());
            while(!Character.isLetter(tempNote.get(letter))) {
                letter++;
            }
            /* Means it's a rest */
            if (tempNote.get(letter) == 'z') {
                /*Do nothing */
                //System.out.println("Z man");
            } else {
                //System.out.println("Old: " +tempNote.get(letter)+ " new: "+ newNote);
                newNote = caseHelper(letter, tempNote, newNote);
                //System.out.println("Old: " +tempNote.get(letter)+ " new: "+ newNote);
                tempNote.set(letter, newNote.charAt(0)); 
                for(int j = 1; j < noteLen; j++) {
                   tempNote.add(j+letter, newNote.charAt(j));
                }
                fibCur = fibPrev;
                fibPrev = total;
                total += fibCur;
                //System.out.printf("fibCur: %d, fibPrev: %d total: %d\n", fibCur, fibPrev, total); 
                //start = 0;
                if(total > 8) {
                   fibPrev = 0;
                   fibCur = 0;
                   total = 1;
                }
                
                //1 1 2 3 5 8
                
                
            }
                //System.out.printf("changed Note: |%s|\n\n", tempNote.toString());
            newNotes.add(new note(tempPre, tempNote, tempPost));
        }
        return newNotes;
   }
   
   /* This will check how high on the scale we have gone */
   private String caseHelper(int let, ArrayList<Character> origNote, String biNote) {
      if (Character.isUpperCase(origNote.get(let))) {
        //System.out.println("do we get here? " +biNote);
        if (biNote.contains( "'") || biNote.contains("’")) {
            biNote = biNote.toLowerCase();
            //System.out.println(origNote + "biNOte: "+biNote);
            if (origNote.contains(39) || origNote.contains(8217)) {
                System.out.println("Found the fun dudes");
                biNote = biNote.replace((char) 39,(char) 0);
                biNote = biNote.replace((char) 8217,(char) 0);
            }
        } else {
         //System.out.println("Is Upperase");
            biNote = biNote.toUpperCase();
            
        }
      } else {
         //System.out.println("THis will be lowercase");
         biNote = biNote.toLowerCase();
         if (origNote.contains(39) || origNote.contains(8217)) {
            System.out.println("Found the fun dudes");
            biNote = biNote.replace((char) 39,(char) 0);
            biNote = biNote.replace((char) 8217,(char) 0);
         }
         //System.out.println("Lowercase?: "+biNote);
      }
      return biNote;
   }            

}






