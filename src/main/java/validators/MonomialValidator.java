package validators;

import models.Monomial;

public class MonomialValidator {

    public static Boolean samePower(Monomial first, Monomial second) {
        return first.compareTo(second) == 0;
    }

    public static Boolean smallerPower(Monomial first, Monomial second) {
        return first.compareTo(second) < 0;
    }

    public static Boolean isNull(Monomial monomial) {
        return monomial == null;
    }

}
