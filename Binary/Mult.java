public class Mult extends Binop {
    public double eval(double left, double right) {

        double product = left * right;
        return (double) Math.round(product * 100)/100;
    }

    public String toString() {

        return "*";
    }
}
