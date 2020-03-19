package util;

import exceptions.DifferentPowerException;
import exceptions.NegativePowerException;
import exceptions.ZeroDivisionException;
import models.Monomial;
import models.Polynomial;
import models.PolynomialDivision;

import static validators.PolynomialValidator.isNull;
import static validators.PolynomialValidator.isZero;

public class PolynomialUtil {

    public static Polynomial add(Polynomial first, Polynomial second) throws DifferentPowerException {
        if (isNull(first)) {
            throw new NullPointerException("Add polynom 1");
        } else if (isNull(second)) {
            throw new NullPointerException("Add polynom 2");
        }

        Polynomial result = new Polynomial(first);
        for (Integer key : second.getPolynomial().keySet()) {
            Monomial monomial = second.getPolynomial().get(key);
            result.push(monomial);
        }
        return result;
    }

    public static Polynomial subtract(Polynomial first, Polynomial second) throws DifferentPowerException {
        if (isNull(first)) {
            throw new NullPointerException("Add polynom 1");
        } else if (isNull(second)) {
            throw new NullPointerException("Add polynom 2");
        }

        Polynomial result = new Polynomial(first);
        for (Integer key : second.getPolynomial().keySet()) {
            Monomial monomial = MonomialUtil.multiply(second.getPolynomial().get(key), -1);
            result.push(monomial);
        }
        return result;
    }

    public static Polynomial multiply(Polynomial first, Polynomial second) throws DifferentPowerException {
        if (isNull(first)) {
            throw new NullPointerException("Add polynom 1");
        } else if (isNull(second)) {
            throw new NullPointerException("Add polynom 2");
        }

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

    public static Polynomial multiply(Polynomial polynomial, Monomial monomial) throws DifferentPowerException {
        if (isNull(polynomial)) throw new NullPointerException("Add polynom");

        Polynomial result = new Polynomial();
        for (Integer keyOne : polynomial.getPolynomial().keySet()) {
            Monomial monomialOne = polynomial.getPolynomial().get(keyOne);
            result.push(MonomialUtil.multiply(monomialOne, monomial));
        }

        return result;
    }

    public static Polynomial derive(Polynomial first) throws DifferentPowerException {
        if (isNull(first)) throw new NullPointerException("Add polynom");

        Polynomial result = new Polynomial();
        for (Integer key : first.getPolynomial().keySet()) {
            Monomial monomial = MonomialUtil.derive(first.getPolynomial().get(key));
            result.push(monomial);
        }

        return result;
    }

    public static Polynomial integrate(Polynomial first) throws DifferentPowerException {
        if (isNull(first)) throw new NullPointerException("Add polynom");

        Polynomial result = new Polynomial();
        for (Integer key : first.getPolynomial().keySet()) {
            Monomial monomial = MonomialUtil.integrate(first.getPolynomial().get(key));
            result.push(monomial);
        }

        return result;
    }

    public static Polynomial divide(Polynomial numerator, Polynomial denominator) throws NegativePowerException, DifferentPowerException, ZeroDivisionException {
        if (isNull(numerator)) {
            throw new NullPointerException("Add polynom 1");
        } else if (isNull(denominator)) {
            throw new NullPointerException("Add polynom 2");
        } else if (isZero(denominator)) {
            throw new ZeroDivisionException();
        }
        PolynomialDivision result = new PolynomialDivision();

        if (numerator.compareTo(denominator) < 0) {
            result.setRemainder(numerator);
            return result;
        }

        Monomial denominatorHigh = denominator.getPolynomial().get(denominator.getPolynomial().firstKey());
        while (numerator.getPolynomial().size() != 0 && numerator.compareTo(denominator) >= 0) {
            Monomial numeratorHigh = numerator.getPolynomial().get(numerator.getPolynomial().firstKey());
            Monomial monomialDivison = MonomialUtil.divide(numeratorHigh, denominatorHigh);
            result.push(monomialDivison);
            numerator = PolynomialUtil.subtract(numerator, PolynomialUtil.multiply(denominator, monomialDivison));
        }
        result.setRemainder(numerator);
        return result;
    }
}
