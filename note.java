import java.util.*;

public class note {
    private ArrayList<Character> pre;
    private ArrayList<Character> Note;
    private ArrayList<Character> post;
    
    public note(ArrayList<Character> pre, ArrayList<Character> Note, ArrayList<Character> post) {
        this.pre = pre;
        this.Note = Note;
        this.post = post;
    }
    
    /* Getters */
    public ArrayList<Character> getPre() {
        return pre;
    }
    
    public ArrayList<Character> getNote() {
        return Note;
    }
    
    public ArrayList<Character> getPost() {
        return post;
    }
    
    /* a toString function for this note */
    public String toString() {
        String entireNote = "";
        
        entireNote += toStringHelper(pre);
        entireNote += toStringHelper(Note);
        entireNote += toStringHelper(post);
        
        return entireNote;
    }
    
    public String toStringHelper(ArrayList<Character> list) {
        String tempString = "";
        int size = list.size();
        for (int i = 0; i < size; i++) {
            tempString += list.get(i);
        }
        //System.out.printf("toString Note: |%s|\n", tempString);
        
        return tempString;
    }
        
}

