public class Divide extends Binop {
    public double eval(double left, double right) {
       double quotient = left / right;
       return (double) Math.round(quotient*100)/100;
    }
    public String toString() {
        return "/";
    }
}
