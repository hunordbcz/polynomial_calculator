package util;

import exceptions.DifferentPowerException;
import exceptions.NegativePowerException;
import models.Monomial;

import java.security.InvalidParameterException;

import static validators.MonomialValidator.*;

public class MonomialUtil {

    public static Monomial add(Monomial first, Monomial second) throws DifferentPowerException {
        if (!samePower(first, second)) throw new DifferentPowerException("Different Power Exception at subtract");
        if (isNull(first) || isNull(second)) throw new InvalidParameterException();
        return new Monomial(first.getCoefficient() + second.getCoefficient(), first.getPower());
    }

    public static Monomial subtract(Monomial first, Monomial second) throws DifferentPowerException {
        if (!samePower(first, second)) throw new DifferentPowerException("Different Power Exception at subtract");
        if (isNull(first) || isNull(second)) throw new NullPointerException();
        second = MonomialUtil.multiply(first, -1);
        return MonomialUtil.add(first, second);
    }

    public static Monomial multiply(Monomial first, Monomial second) {
        if (isNull(first) || isNull(second)) throw new NullPointerException();
        return new Monomial(first.getCoefficient() * second.getCoefficient(), first.getPower() + second.getPower());
    }

    public static Monomial multiply(Monomial first, Integer value) {
        if (isNull(first)) throw new NullPointerException();
        return new Monomial(first.getCoefficient() * value, first.getPower());
    }

    public static Monomial derive(Monomial first) {
        if (isNull(first)) throw new NullPointerException();
        if (first.getPower() > 0) {
            Double coefficient = first.getCoefficient() * first.getPower();
            Integer power = first.getPower() - 1;
            return new Monomial(coefficient, power);
        }
        return new Monomial(0d, 0);
    }

    public static Monomial integrate(Monomial first) {
        if (isNull(first)) throw new NullPointerException();
        Integer power = first.getPower() + 1;
        Double coefficent = first.getCoefficient() / power;
        return new Monomial(coefficent, power);
    }

    public static Monomial divide(Monomial first, Monomial second) throws NegativePowerException {
        if (isNull(first) || isNull(second)) throw new NullPointerException();
        if (smallerPower(first, second))
            if (!samePower(first, second)) {
                throw new NegativePowerException("Negative Power at divide function");
            }
        return new Monomial(first.getCoefficient() / second.getCoefficient(), first.getPower() - second.getPower());
    }
}
