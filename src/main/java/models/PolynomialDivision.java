package models;

import static validators.PolynomialValidator.isNull;
import static validators.PolynomialValidator.isZero;

public class PolynomialDivision extends Polynomial {
    private Polynomial remainder;

    @Override
    public String getString() {
        String response = super.getString();
        if (!isNull(remainder) && !isZero(remainder)) {
            response = response + " (r:" + remainder.getString() + ")";
        }
        return response;
    }

    public void setRemainder(Polynomial remainder) {
        this.remainder = remainder;
    }
}
