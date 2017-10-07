import java.util.*;

public class abc {
    private ArrayList<note> notes;
    private ArrayList<String> header;
    private HashSet<Character> preSet;
    private HashSet<Character> postSet;
    private String stringNotes;
    
    
    /*
     * Creates the necessary HashSets
     */
    public abc () {
        notes = new ArrayList<note>();
        header = new ArrayList<String>();
        preSet = new HashSet<Character>();
        stringNotes = "";
        
        
        preSet.add('.');
        preSet.add('<');
        preSet.add('>');
        preSet.add('^');
        preSet.add('@');
        preSet.add('=');
        
        postSet = new HashSet<Character>();
        
        postSet.add('/');
        postSet.add('\'');
        postSet.add('#');
        postSet.add(' ');
    }
    
    /*
     * The is used to convert a string line into a readable list
     */
    public void add(String line) {
        if ((line.length() >= 2) && (line.charAt(1) == ':')) {
            header.add(line);
        } else {
            int lineLen = line.length();
            ArrayList<Character> tempPre;
            ArrayList<Character> tempNote;
            ArrayList<Character> tempPost;
            //System.out.println(line);
            int i = 0;
            int check;
            while (i < lineLen) {
                tempPre = new ArrayList<Character>();
                tempNote = new ArrayList<Character>();
                tempPost = new ArrayList<Character>();
                check = 0;
                //System.out.println(i);
                //notes.add(line.charAt(i));

                while ((i < lineLen) && (preSet.contains(line.charAt(i)))) {
                    //System.out.println("pre...");
                    tempPre.add(line.charAt(i));
                    i++;
                    check = 1;
                }

                if ((i < lineLen) && ((Character.isLetter(line.charAt(i))))) {
                    //System.out.println("note...");
                    tempNote.add(line.charAt(i));
                    i++;
                    check = 1;
                    while ((i < lineLen) && ((line.charAt(i) == ',') || (line.charAt(i) == '\''))) {
                        tempNote.add(line.charAt(i));
                        i++;
                    }
                }

                    
                while ((i < lineLen) && (checkPost(line.charAt(i)))) {
                    //System.out.println("post...");
                    tempPost.add(line.charAt(i));
                    i++;
                    check = 1;
                }
                
                if (check == 0) {
                    i++;
                }
                if (!tempNote.isEmpty()) {
                    //System.out.println("TempNote: "+tempNote);
                    notes.add(new note(tempPre, tempNote, tempPost));
                }
            }
            addBars();
            
        }
    }
    
    /*
     * Check to see if this note comes before after the note
     */
    private Boolean checkPost(Character tempNote) {
        if (postSet.contains(tempNote) || Character.isDigit(tempNote)) {
            //System.out.printf("This was post: |%c|\n",tempNote);
            return true;
        }
        return false;
    }       
            
                
    
    /* 
     * getters for all of the headers and notes 
     */
    public ArrayList<note> getNotes () {
        //System.out.println(notes); 
        return notes;
    }
    
    
    /*
     * Get the part of the header with that specific letter 
     */
    public String get(char letter) {
        String line = "";
        int len = header.size();
        for(int i = 0; i < len; i++) {
            String temp = header.get(i);
            if (letter == temp.charAt(0)) {
                line = header.get(i);
            }
        }
        return line;
    }
    
    /* 
     * Returned the String version of the Header
     */
    public String getHeader() {
        String headerString = "";
        int len = header.size();
        for(int i = 0; i < len; i++) {
            headerString = headerString + header.get(i)+ "\n";
        }
        return headerString;
    }
    
    /* 
     * Copies the header to a new ArrayList
     */
    public ArrayList<String> copyHeader() {
        return new ArrayList<String>(header);
    }
    
    /* 
     * Change the notes to a new set
     */
    public void setNotes (ArrayList<note> line) {   
        notes = line;
        addBars();
    }
    
    /* 
     * Changes the header to a new set
     */
    public void setHeader (ArrayList<String> hdr) {
        header = hdr;
    }
    
    /* 
     * toString() method 
     */
    public String toString() {
        String entireFile = getHeader();
        
        int size = notes.size();
        note tempNote;
        for (int i = 0; i < size; i++) {
            tempNote = notes.get(i);
            entireFile += tempNote.toString();
        }
        return entireFile;
        
        //return stringNotes
    }
    
    
    /*
     * THIS IS WHERE I THINK WE SHOULD PUT THE BAR LOGIC
     * 
     * Maybe instead of a toString method we just create a
     *  new String that will have the bars built in, and we will
     *  just create a new String every time the notes are changed.
     * 
     * I added the variable "stringNotes" for this class, and added
     *  where I think this method should be. If this doesn't work the
     *  best then feel free to change it. It was just a thought.
     */
     private void addBars() {
     
     }
}