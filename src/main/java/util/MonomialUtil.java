package util;

import models.Monomial;

import java.security.InvalidParameterException;

public class MonomialUtil implements Operations<Monomial> {

    private static Boolean samePower(Monomial first, Monomial second) {
        if (!first.getPower().equals(second.getPower())) {
            return false;
        }
        return true;
    }

    public static Monomial add(Monomial first, Monomial second) {
        if (!MonomialUtil.samePower(first, second)) throw new InvalidParameterException();
        return new Monomial(first.getCoefficient() + second.getCoefficient(), first.getPower());
    }

    public static Monomial subtract(Monomial first, Monomial second) {
        if (!MonomialUtil.samePower(first, second)) throw new InvalidParameterException();
        second = MonomialUtil.multiply(first, -1);
        return MonomialUtil.add(first, second);
    }

    public static Monomial multiply(Monomial first, Monomial second) {
        return new Monomial(first.getCoefficient() * second.getCoefficient(), first.getPower() + second.getPower());
    }

    public static Monomial multiply(Monomial first, Integer value) {
        return new Monomial(first.getCoefficient() * value, first.getPower());
    }

    public static Monomial derive(Monomial first) {
        if (first.getPower() > 0) {
            Double coefficient = first.getCoefficient() * first.getPower();
            Integer power = first.getPower() - 1;
            return new Monomial(coefficient, power);
        }
        return new Monomial(0d, 0);
    }

    public static Monomial integrate(Monomial first) {
        Double coefficent = first.getCoefficient() / first.getPower();
        Integer power = first.getPower() + 1;
        return new Monomial(coefficent, power);
    }

    public static Monomial divide(Monomial first, Monomial second) {
        //TODO make division
        return new Monomial(first.getCoefficient() * second.getCoefficient(), first.getPower() + second.getPower());
    }
}
