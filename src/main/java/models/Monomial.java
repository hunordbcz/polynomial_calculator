package models;

public class Monomial implements Comparable<Monomial> {
    private Integer power;
    private Double coefficient;

    public Monomial(Double coefficient, Integer power) {
        this.power = power;
        this.coefficient = coefficient;
    }

    public static Monomial parseMonomial(String val) {
        Monomial monomial = new Monomial(1d, 0);

        if (val.contains("x")) {
            monomial.power = 1;
        }
        String[] value = val.split("x\\^|x");

        if (value.length > 0) {
            if (value[0].equals("-")) {
                monomial.coefficient = -1d;
            } else if (value[0].equals("+")) {
                monomial.coefficient = 1d;
            } else if (!value[0].equals("")) {
                monomial.coefficient = Double.parseDouble(value[0]);
            }
            if (value.length > 1) {
                monomial.power = Integer.parseInt(value[1]);
            }
        }
        return monomial;
    }

    public Integer getPower() {
        return power;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public int compareTo(Monomial monomial) {
        return this.power - monomial.power;
    }
}
