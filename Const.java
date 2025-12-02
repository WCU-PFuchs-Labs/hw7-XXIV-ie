    public class Const extends Unop {
    private double value;

    public Const(double value) {
        this.value = value;
    }

    @Override
    public double eval(double[] values) {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
