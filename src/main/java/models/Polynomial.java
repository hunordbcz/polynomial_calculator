package models;

import exceptions.DifferentPowerException;
import exceptions.InvalidInputException;
import util.Constants;
import util.MonomialUtil;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static validators.PolynomialValidator.validate;

public class Polynomial implements Comparable<Polynomial> {
    private SortedMap<Integer, Monomial> polynomial;

    public Polynomial() {
        this.polynomial = new TreeMap<>(Collections.reverseOrder());
    }

    public Polynomial(Polynomial polynomial) {
        this.polynomial = polynomial.getPolynomial();
    }

    public static Polynomial parsePolynomial(String value) throws InvalidInputException, DifferentPowerException {
        if (!validate(value)) throw new InvalidInputException("Polynomial entered is invalid.");

        Pattern pattern = Pattern.compile(Constants.PATTERN_REGEX);
        Matcher matcher = pattern.matcher(value);

        Polynomial polynomial = null;
        while (matcher.find()) {
            Monomial monomial = Monomial.parseMonomial(matcher.group());
            if (polynomial == null) {
                polynomial = new Polynomial();
            }
            polynomial.push(monomial);
        }
        return polynomial;
    }

    public void push(Monomial monomial) throws DifferentPowerException {
        if (this.polynomial.containsKey(monomial.getPower())) {
            Monomial insideMonomial = this.polynomial.get(monomial.getPower());
            insideMonomial = MonomialUtil.add(insideMonomial, monomial);
            polynomial.remove(insideMonomial.getPower());
            if (insideMonomial.getCoefficient() != 0) polynomial.put(insideMonomial.getPower(), insideMonomial);
        } else {
            this.polynomial.put(monomial.getPower(), monomial);
        }
    }

    public String getString() {

        StringBuilder result = new StringBuilder();

        for (Integer key : this.polynomial.keySet()) {
            Monomial monomial = this.polynomial.get(key);
            if (!result.toString().equals("") && monomial.getCoefficient() > 0) result.append("+");
            result.append(monomial.getCoefficient());
            if (monomial.getPower() > 0) {
                result.append("x");
                if (monomial.getPower() > 1) {
                    result.append("^").append(monomial.getPower());
                }
            }
        }
        if (result.length() == 0) {
            return "0";
        }
        return result.toString();
    }

    public SortedMap<Integer, Monomial> getPolynomial() {
        return this.polynomial;
    }

    @Override
    public int compareTo(Polynomial polynomial) {
        return this.polynomial.firstKey() - polynomial.getPolynomial().firstKey();
    }
}
