package exceptions;

public class ZeroDivisionException extends Exception {
    public ZeroDivisionException() {
        super("Division with zero not allowed");
    }
}
