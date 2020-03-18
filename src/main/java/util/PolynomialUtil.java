package util;

import models.Monomial;
import models.Polynomial;

import java.util.regex.Pattern;

public class PolynomialUtil {
    public static final String regex = "[+-]?((?:\\d*x\\^\\d+)|(?:\\d+x)|(?:x)|(?:\\d+))";

    public static Boolean validate(String toValidate) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toValidate).replaceAll("").equals("");
    }

    public static Boolean validate(Polynomial polynomial) {
        return polynomial != null;
    }

    public static Polynomial add(Polynomial first, Polynomial second) {
        if (!PolynomialUtil.validate(first) || !PolynomialUtil.validate(second)) throw new IllegalArgumentException();

        Polynomial result = new Polynomial(first);
        for (Integer key : second.getPolynomial().keySet()) {
            Monomial monomial = second.getPolynomial().get(key);
            result.push(monomial);
        }
        return result;
    }

    public static Polynomial subtract(Polynomial first, Polynomial second) {
        if (!PolynomialUtil.validate(first) || !PolynomialUtil.validate(second)) throw new IllegalArgumentException();

        Polynomial result = new Polynomial(first);
        for (Integer key : second.getPolynomial().keySet()) {
            Monomial monomial = MonomialUtil.multiply(second.getPolynomial().get(key), -1);
            result.push(monomial);
        }
        return result;
    }

    public static Polynomial multiply(Polynomial first, Polynomial second) {
        if (!PolynomialUtil.validate(first) || !PolynomialUtil.validate(second)) throw new IllegalArgumentException();

        Polynomial result = new Polynomial();
        for (Integer keyOne : first.getPolynomial().keySet()) {
            Monomial monomialOne = first.getPolynomial().get(keyOne);
            for (Integer keyTwo : second.getPolynomial().keySet()) {
                Monomial monomialTwo = second.getPolynomial().get(keyTwo);
                result.push(MonomialUtil.multiply(monomialOne, monomialTwo));
            }
        }

        return result;
    }

    public static Polynomial multiply(Polynomial polynomial, Monomial monomial){
        if (!PolynomialUtil.validate(polynomial)) throw new IllegalArgumentException();

        Polynomial result = new Polynomial();
        for (Integer keyOne : polynomial.getPolynomial().keySet()) {
            Monomial monomialOne = polynomial.getPolynomial().get(keyOne);
            result.push(MonomialUtil.multiply(monomialOne, monomial));
        }

        return result;
    }

    public static Polynomial derive(Polynomial first) {
        if (!PolynomialUtil.validate(first)) throw new IllegalArgumentException();

        Polynomial result = new Polynomial();
        for (Integer key : first.getPolynomial().keySet()) {
            Monomial monomial = MonomialUtil.derive(first.getPolynomial().get(key));
            result.push(monomial);
        }

        return result;
    }

    public static Polynomial integrate(Polynomial first) {
        if (!PolynomialUtil.validate(first)) throw new IllegalArgumentException();

        Polynomial result = new Polynomial();
        for (Integer key : first.getPolynomial().keySet()) {
            Monomial monomial = MonomialUtil.integrate(first.getPolynomial().get(key));
            result.push(monomial);
        }

        return result;
    }

    public static Polynomial divide(Polynomial numerator, Polynomial denominator) {
        if(numerator.getPolynomial().firstKey() < denominator.getPolynomial().firstKey()){
            return numerator;
        }
        Polynomial result = new Polynomial();
        Monomial denominatorHigh = denominator.getPolynomial().get(denominator.getPolynomial().firstKey());
        while(numerator.getPolynomial().size() != 0 && numerator.getPolynomial().firstKey() >= denominatorHigh.getPower()){
            Monomial numeratorHigh = numerator.getPolynomial().get(numerator.getPolynomial().firstKey());
            Monomial monomialDivison = MonomialUtil.divide(numeratorHigh,denominatorHigh);
            result.push(monomialDivison);
            numerator = PolynomialUtil.subtract(numerator,PolynomialUtil.multiply(denominator,monomialDivison));
        }
        return result;
    }
}
