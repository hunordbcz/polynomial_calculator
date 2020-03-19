package validators;

import models.Monomial;
import models.Polynomial;

import java.util.regex.Pattern;

public class PolynomialValidator {

    public static final String regex = "[+-]?((?:\\d*x\\^\\d+)|(?:\\d+x)|(?:x)|(?:\\d+))";

    public static Boolean validate(String toValidate) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toValidate).replaceAll("").equals("");
    }

    public static Boolean isZero(Polynomial polynomial) {
        Monomial monomial = polynomial.getPolynomial().get(polynomial.getPolynomial().firstKey());
        return monomial.getPower() == 0 && monomial.getCoefficient() == 0;
    }

    public static Boolean isNull(Polynomial polynomial) {
        return polynomial == null;
    }
}
