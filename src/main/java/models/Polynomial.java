package models;

import util.MonomialUtil;
import util.PolynomialUtil;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    SortedMap<Integer, Monomial> polynomial;
    Integer remainder = 0;

    public Polynomial() {
        this.polynomial = new TreeMap<>(Collections.reverseOrder());
    }

    public Polynomial(Polynomial polynomial) {
        this.polynomial = polynomial.getPolynomial();
    }

    public static Polynomial parsePolynomial(String value) {
        if (!PolynomialUtil.validate(value)) {
            //todo show error
        }

        Pattern pattern = Pattern.compile(PolynomialUtil.regex);
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

    public void push(Monomial monomial) {
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
        return result.toString();
    }

    public SortedMap<Integer, Monomial> getPolynomial() {
        return this.polynomial;
    }
}
