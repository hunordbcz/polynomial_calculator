package validators;

import models.Monomial;
import models.Polynomial;
import util.Constants;

import java.util.regex.Pattern;

public class PolynomialValidator {

    public static Boolean validate(String toValidate) {
        Pattern pattern = Pattern.compile(Constants.PATTERN_REGEX);
        return pattern.matcher(toValidate).replaceAll("").equals("");
    }

    public static Boolean isZero(Polynomial polynomial) {
        if (polynomial.getPolynomial().size() > 0) {
            Monomial monomial = polynomial.getPolynomial().get(polynomial.getPolynomial().firstKey());
            return monomial.getPower() == 0 && monomial.getCoefficient() == 0;
        }
        return true;
    }

    public static Boolean isNull(Polynomial polynomial) {
        return polynomial == null;
    }
}
