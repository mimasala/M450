package ch.tbz.aufg3;
public class Calculator {

    public double add(double summand1, double summand2) {
        return summand1 + summand2;
    }
    public double multiply(double multiplier, double multiplicand) {
        return multiplier * multiplicand;
    }
    public double subtract(double minuend, double subtrahend) {
        return minuend - subtrahend;
    }


    public double divide(double dividend, double divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("can't divide through zero");
        }
        return dividend / divisor;
    }
}

