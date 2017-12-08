import java.lang.*;
import java.io.*;
import java.util.*;

public class rhythm {

    /*
     * If this equals 0 then nothing will be added
     * If this equals 1 then we need to add '>' to the next preSet
     * If this equals 2 then we need to add '<' to the next preSet
     */
    private int preChange;
    
    public rhythm() {
        /* Do nothing */
        preChange = 0;
    }
    
    /*
     * The logic to rearrange the notes for reflection
     */
    public ArrayList<note> reflection(abc file) {
        // Ask here if the user wants to use a specific
        preChange = 0;
        ArrayList<note> notes = file.getNotes();
        int size = notes.size();
        
        ArrayList<note> newNotes = new ArrayList<note>();
        
        ArrayList<Character> tempPre;
        ArrayList<Character> tempNote;
        ArrayList<Character> tempPost;
        
        note oldNote;
        note reflectedNote;
        
        for (int i = 0; i < size; i++) {
            //preChange = 0;
            oldNote = notes.get(i);
            reflectedNote = notes.get((size-1)-i);
            
            //System.out.printf("oldNote: |%s|\n", oldNote.toString());
            //System.out.printf("reflectedNote: |%s|\n\n", reflectedNote.toString());
            
            /* Need to add the '>' or '<' for the +1 of the following preSet */
            
            tempPre = switchPre(reflectedNote.getPre());
            tempNote = switchNote(oldNote.getNote());
            tempPost = switchPost(reflectedNote.getPost());
            
            newNotes.add(new note(tempPre, tempNote, tempPost));
            
            //System.out.println("preChange value for next: "+ preChange);
        }
        return newNotes;
    }
    
    /*
     * Rearrange certain characters if necessary for reflection
     */
    private ArrayList<Character> switchPre(ArrayList<Character> temp) {
        ArrayList<Character> newPre = new ArrayList<Character>();
        int total = temp.size();
        char curChar;
        /* Add switched signs to new placements */
        if (preChange == 1) {
            newPre.add('>');
        } else if(preChange == 2) {
            newPre.add('<');
        }
        preChange = 0;
        
        for (int i = 0; i < total; i++) {
            curChar = temp.get(i);
            //System.out.printf("%c\n",curChar);
            switch (curChar) {
                case '<': 
                    preChange = 1; 
                    //curChar = ''; //curChar = '>';
                    //System.out.println("1");
                    break;
                case '>': 
                    preChange = 2; 
                    //System.out.println("2");
                    //curChar = ''; //curChar = '<';
                    break;
                default: 
                    //System.out.println("Moving to default: "+ preChange);
                    //preChange = 0;
                    newPre.add(curChar);
                    /* DO NOTHING */
            }
            
        }
        return newPre;
    }
    
    /*
     * rearrange certain characters if necessary for reflection
     */
    private ArrayList<Character> switchNote(ArrayList<Character> temp) {
        ArrayList<Character> newNote = new ArrayList<Character>();
        int total = temp.size();
        char curChar;
        for (int i = 0; i < total; i++) {
            curChar = temp.get(i);
            switch (curChar) {
                /*case '<': curChar = '>';
                    break;
                case '>': curChar = '<';
                    break;*/
                default: /* DO NOTHING */
            }
            newNote.add(curChar);
        }
        return newNote;
    }
    
    /*
     * rearrange certain characters if necessary for reflection
     */
    private ArrayList<Character> switchPost(ArrayList<Character> temp) {
        ArrayList<Character> newPost = new ArrayList<Character>();
        int total = temp.size();
        char curChar;
        for (int i = 0; i < total; i++) {
            curChar = temp.get(i);
            switch (curChar) {
                /*case '<': curChar = '>';
                    break;
                case '>': curChar = '<';
                    break;*/
                default: /* DO NOTHING */
            }
            newPost.add(curChar);
        }
        return newPost;
    }
    
    /* public ArrayList<Character> circularRotation(abc file) {
        ArrayList<Character> notes = new ArrayList<Character>();
        notes = file.getNotes();
        int size = notes.size();
        int temp = size - 1;
        ArrayList<Character> newNotes = new ArrayList<Character>();
        String time = file.get("M");
        
        
        for (int i = */
}