import java.util.*;

public class abc {
    private String X;
    private String T;
    private String C;
    private String M;
    private String L;
    private String Q;
    private String K;
    private ArrayList<Character> notes;
    
    public abc () {
        X = null;
        T = null;
        C = null;
        M = null;
        L = null;
        Q = null;
        K = null;
        notes = new ArrayList<Character>();
    }
    
    public void add(String line) {
        //System.out.printf("line: %s\n", line);
        switch (line.charAt(0)) {
            case 'X':
                X = line;
                break;
            case 'T':
                T = line;
                break;
            case 'C':
                C = line;
                break;
            case 'M':
                M = line;
                break;
            case 'L':
                L = line;
                break;
            case 'Q':
                Q = line;
                break;
            case 'K':
                K = line;
                break;
            default:
                int lineLen = line.length();
                for (int i = 0; i < lineLen; i++) {
                    //System.out.println(i);
                    notes.add(line.charAt(i));
                }
        }
    }
                
    
    /* getters for all of the headers and notes */
    public String getX () {   
        return X;
    }    
    public String getT () {   
        return T;
    }
    public String getC () {   
        return C;
    }
    public String getM () {   
        return M;
    }
    public String getL () {   
        return L;
    }
    public String getQ () {   
        return Q;
    }
    public String getK () {   
        return K;
    }
    public ArrayList<Character> getNotes () {   
        return notes;
    }
    
    public String getHeader() {
        String header = X + "\n" + T + "\n" + C + "\n" + M + "\n" + L + "\n" + Q + "\n" + K + "\n";
        return header;
    }
    
        
    /* Setters for all of the headers and notes */
    public void setX (String line) {   
        X = line;
    }    
    public void setT (String line) {   
        T = line;
    }
    public void setC (String line) {   
        C = line;
    }
    public void setM (String line) {   
        M = line;
    }
    public void setL (String line) {   
        L = line;
    }
    public void setQ (String line) {   
        Q = line;
    }
    public void setK (String line) {   
        K = line;
    }
    public void setNotes (ArrayList<Character> line) {   
        notes = line;
    }
}