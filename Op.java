/**
 * Code Template Author: David G. Cooper
 * Purpose: Tha base class for operations
 */
public abstract class Op implements Cloneable {
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

