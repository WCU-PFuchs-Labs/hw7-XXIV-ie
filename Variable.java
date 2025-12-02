package Binary;

public class Variable extends Unop {
    private int index;

    public Variable(int index) {
        this.index = index;
    }

    @Override
    public double eval(double[] values) {
        return values[index];
    }

    @Override
    public String toString() {
        return "X" + index;
    }
}
