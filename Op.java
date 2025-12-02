package Binary;

/**
 * Code Template Author: David G. Cooper
 * Purpose: Tha base class for operations
 */
public abstract class Op implements Cloneable {
    public Object clone() {
        Object o = null;
        try {
            o =  super.clone();
        }
        catch(CloneNotSupportedException e) {
            System.out.println("Op can't clone.");
        }
        return o;
    }
}

