import java.util.*;

public class abc {
    
    //private static final HashSet<Character> preSet = {'.', '<', '>', '^', '@', '='};
    //private static final HashSet<Character> postSet = {'/', '\', '#', '='};
    
    private ArrayList<note> notes;
    private ArrayList<String> header;
    private ArrayList<Integer> bars;
    private ArrayList<Integer> repeats;
    private HashSet<Character> preSet;
    private HashSet<Character> postSet;
    private String stringNotes;
    
    
    /*
     * Creates the necessary HashSets
     */
    public abc () {
        this.notes = new ArrayList<note>();
        this.header = new ArrayList<String>();
        this.bars = new ArrayList<Integer>();
        this.repeats = new ArrayList<Integer>();
        
        stringNotes = "";
        preSet = new HashSet<Character>();
        
        preSet.add('.');
        preSet.add('<');
        preSet.add('>');
        preSet.add('^');
        preSet.add('@');
        preSet.add('=');
        preSet.add((char) 32);
        
        postSet = new HashSet<Character>();
        
        postSet.add('/');
        postSet.add('\'');
        postSet.add('#');
        postSet.add((char) 32);
    }
    
    public abc (abc file) {
        this.notes = new ArrayList<note>();
        this.header = file.copyHeader();
        this.bars = new ArrayList<Integer>(file.getBars());
        this.repeats = new ArrayList<Integer>(file.getRepeats());
        
        stringNotes = "";
        preSet = new HashSet<Character>();
        
        preSet.add('.');
        preSet.add('<');
        preSet.add('>');
        preSet.add('^');
        preSet.add('@');
        preSet.add('=');
        preSet.add((char) 32);
        
        postSet = new HashSet<Character>();
        
        postSet.add('/');
        postSet.add('\'');
        postSet.add('#');
        postSet.add((char) 32);
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
            
            stringNotes = line;
            int i = 0;
            int check;
            while (i < lineLen) {
                tempPre = new ArrayList<Character>();
                tempNote = new ArrayList<Character>();
                tempPost = new ArrayList<Character>();
                check = 0;
                //System.out.println(i);
                //notes.add(line.charAt(i));
                System.out.printf("%d:  %c, %d\n",i, line.charAt(i), (int) line.charAt(i));
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
                    /*System.out.printf("%d\n", (int)'\'');
                    if (line.charAt(i) == 39) {
                        System.out.println("Found the apos");
                    } else if (line.charAt(i) == 8217) {
                        System.out.println("Found the RSQ");
                    } else {
                        System.out.println("..."+line.charAt(i)+"..." + (int) line.charAt(i));
                    }*/
                    /* 8217 equals Right Single Quotation Mark.
                     *   Some computers/users use this marks as an apostrophe
                     * 39 equals apostrophe
                     */
                    while ((i < lineLen) && ((line.charAt(i) == ',') || (line.charAt(i) == 39)|| (line.charAt(i) == 8217))) {
                        //System.out.println("Found the extras");
                        tempNote.add(line.charAt(i));
                        i++;
                    }
                    //System.out.println(tempNote);
                }

                    
                while ((i < lineLen) && (checkPost(line.charAt(i)))) {
                    //System.out.println("post...");
                    tempPost.add(line.charAt(i));
                    i++;
                    check = 1;
                }
                
                /* Adding bars to the abc file */
                if ((i < (lineLen)) && (line.charAt(i) == '|')) {
                    //System.out.println("bar...");
                    //System.out.println("Barlines is empty1: " +bars.isEmpty());
                    if (line.charAt(i-1) == ':') {
                        repeats.add(notes.size());
                        System.out.println("Adding repeat at: " +(notes.size()));
                    } else if (((i+1) < lineLen) && (line.charAt(i+1) == ':')) {
                        repeats.add(notes.size());
                        System.out.println("Adding repeat at: " +(notes.size()));
                    }
                    bars.add(notes.size());
                    System.out.println("Adding bar at: " +(notes.size()));
                    i++;
                    check = 1;
                    //System.out.printf("adding bar at: %d\n", notes.size());
                }
                
                if (check == 0) {
                    i++;
                }
                if (!tempNote.isEmpty()) {
                    //System.out.println("TempNote: "+tempNote);
                    
                    notes.add(new note(tempPre, tempNote, tempPost));
                    //System.out.println("NewNote: "+ notes.get(notes.size()-1).toString());
                    //System.out.println("tempPre: "+ tempPre);
                    if ((bars.size() > 0) && (bars.get(bars.size()-1) == (notes.size()-1))) {
                        System.out.println("Add Bar here: " + (notes.size()-1));
                    }
                    
                }
                //System.out.println("Barlines is empty3: " +barLines.isEmpty());
            }
            addBars();
            System.out.println("Barlines is empty(After AddBars): " +notes.size());
            System.out.println("Replines is empty(After AddBars): " +repeats.size());
        }
        
        //System.out.println("Barlines is empty (end of line): " +bars.isEmpty());
        //System.out.println("Header is empty: " +header.isEmpty());
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
     *  gets for the barlines
     */
    public ArrayList<Integer> getBars() {
        return new ArrayList<Integer>(bars);
    }
    
    /*
     *  gets for the repeats for barlines
     */
    public ArrayList<Integer> getRepeats() {
        return repeats;
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
     * Changes the bars to the new file
     */
    public void setBars(ArrayList<Integer> tempBars, ArrayList<Integer> tempRepeats) {
        bars = tempBars;
        repeats = tempRepeats;
    }
    
    /*
     * Flips the positions of the bars
     */
    public void flipBars() {
        ArrayList<Integer> tempBars = new ArrayList<Integer>();
        int size = notes.size();
        int tempInt;
        for (int i=bars.size()-2; i >= 0; i--) {
            tempInt = bars.get(i);
            System.out.printf("Bars: %d - %d\n", size, tempInt);
            tempBars.add(size-(tempInt+2));
        }
        tempBars.add(bars.get(bars.size()-1));
        bars = tempBars;
        
        ArrayList<Integer> tempRepeats = new ArrayList<Integer>();
        size = notes.size();
        for (int i=repeats.size()-2; i >= 0; i--) {
            tempInt = repeats.get(i);
            //System.out.printf("Repeats: %d - %d\n", size, tempInt);
            tempRepeats.add(size-tempInt);
        }
        repeats = tempRepeats;
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
        //System.out.println(bars);
        note tempNote;
        for (int i = 0; i < size; i++) {
            tempNote = notes.get(i);
            entireFile += tempNote.toString();
            //System.out.println(tempNote.toString());


            //System.out.println("Barlines is empty(toString): " +bars.isEmpty());
            //System.out.println("Header is empty(toString): " +header.isEmpty());
            if (repeats.contains(i)) {
                entireFile += ":";
            }
            if (bars.contains(i)) {
                entireFile += "|";
                //System.out.println("Outputing Bar");
            }
        }
        if (repeats.contains(size)) {
            entireFile += ":";
        }
        if (bars.contains(size)) {
            entireFile += "|";
        }
        //System.out.println("StringNotes: "+ stringNotes);
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
        //System.out.println("Barlines is empty (AddBars): " +bars.isEmpty());
//          /* Retrieve the beat length and the length of a meter */
//          String noteLength = get('L');
//          String meterType = get('M');
// 
//          /* Figure out the type of length and meter */
//         int baseSize = Integer.parseInt(noteLength);
//         int meterSize = Integer.parseInt(meterType);
// 
//          /* Run through the line notes array and place where the bars should be */
//         int size = notes.size(); 
//         int curTotal;
//         note tempNote;
//         ArrayList<Character> tempPost;
//         for(int i = 0; i < size; i++) {
//             tempNote = notes.get(i);
//             tempPost = tempNote.getPost();
//             curTotal = 0;
//             
// 
//             
//             
//         }
// 
//          //stringNotes
//          
     }
}