import java.util.*;

public class abc {
    private ArrayList<Character> notes;
    private ArrayList<String> header;
    
    public abc () {
        notes = new ArrayList<Character>();
        header = new ArrayList<String>();
    }
    
    public void add(String line) {
        if ((line.length() >= 2) && (line.charAt(1) == ':')) {
            header.add(line);
        } else {
            int lineLen = line.length();
            for (int i = 0; i < lineLen; i++) {
                //System.out.println(i);
                notes.add(line.charAt(i));
            }
        }
    }
                
    
    /* getters for all of the headers and notes */
    public ArrayList<Character> getNotes () {
        //System.out.println(notes); 
        return notes;
    }
    
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
    
    public String getHeader() {
        String headerString = "";
        int len = header.size();
        for(int i = 0; i < len; i++) {
            headerString = headerString + header.get(i)+ "\n";
        }
        return headerString;
    }

    public void setNotes (ArrayList<Character> line) {   
        notes = line;
    }
}