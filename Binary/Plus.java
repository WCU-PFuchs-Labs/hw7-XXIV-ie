/**
 * Code Template Author: David G. Cooper
 * Purpose: A binary operator for Addition
 */
 public class Plus extends Binop {
     /**
      * @param left the left value
      * @param right the right value
      * @return the result of adding
      * left to right 
      */
    public double eval(double left, double right) {

        double sum = left + right;
        return (double) Math.round(sum*100)/100;
    }
    public String toString() {
        return "+";
    }
}
