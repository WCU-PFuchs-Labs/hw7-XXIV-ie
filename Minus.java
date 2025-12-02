package Binary;

public class Minus extends Binop {
    public double eval(double left, double right) {
       double difference = left - right;
       return (double) Math.round(difference*100)/100;
    }

    public String toString() {
        return "-";
    }
}
