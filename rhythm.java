import java.lang.*;
import java.io.*;
import java.util.*;

public class rhythm {
    public rhythm() {
        /* DO nothing */
    }
    
    public ArrayList<Character> reflection(ArrayList<Character> notes) {
        // Ask here if the user wants to use a specific
        int size = notes.size();
        int temp = size - 1;
        ArrayList<Character> newNotes = new ArrayList<Character>();
        for(int i = size-2; i >= 0; i--) {
            if ((Character.isLetter(notes.get(i))) || (notes.get(i) == '|')) {
                for (int j = i; j < temp; j++) {
                    newNotes.add(notes.get(j));
                }
                temp = i;
            }
        }
        newNotes.add('|');
        return newNotes;
    }
}