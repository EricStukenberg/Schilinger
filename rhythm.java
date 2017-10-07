import java.lang.*;
import java.io.*;
import java.util.*;

public class rhythm {
    public rhythm() {
        /* DO nothing */
    }
    
    /*
     * THe logic to rearrange the notes for reflection
     */
    public ArrayList<note> reflection(abc file) {
        // Ask here if the user wants to use a specific
        ArrayList<note> notes = file.getNotes();
        int size = notes.size();
        
        ArrayList<note> newNotes = new ArrayList<note>();
        
        ArrayList<Character> tempPre;
        ArrayList<Character> tempNote;
        ArrayList<Character> tempPost;
        
        note oldNote;
        note reflectedNote;
        
        for (int i = 0; i < size; i++) {
            oldNote = notes.get(i);
            reflectedNote = notes.get((size-1)-i);
            
            //System.out.printf("oldNote: |%s|\n", oldNote.toString());
            //System.out.printf("reflectedNote: |%s|\n\n", reflectedNote.toString());
            
            tempPre = switchPre(reflectedNote.getPre());
            tempNote = switchPre(oldNote.getNote());
            tempPost = switchPost(reflectedNote.getPost());
            
            newNotes.add(new note(tempPre, tempNote, tempPost));
        }
        return newNotes;
    }
    
    /*
     * rearrange certain characters if necessary for reflection
     */
    private ArrayList<Character> switchPre(ArrayList<Character> temp) {
        ArrayList<Character> newPre = new ArrayList<Character>();
        int total = temp.size();
        char curChar;
        for (int i = 0; i < total; i++) {
            curChar = temp.get(i);
            //System.out.printf("%c\n",curChar);
            switch (curChar) {
                case '<': curChar = '>';
                    break;
                case '>': curChar = '<';
                    break;
                default: /* DO NOTHING */
            }
            newPre.add(curChar);
        }
        return newPre;
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