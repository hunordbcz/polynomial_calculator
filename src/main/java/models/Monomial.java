package models;

public class Monomial implements Comparable<Monomial> {
    private Integer power = 0;
    private Double coefficient = 1d;

    public Monomial(String val) {
        if (val.contains("x")) {
            this.power = 1;
        }
        String[] value = val.split("x\\^|x");

        if (value.length > 0) {
            if (value[0].equals("-")) {
                this.coefficient = -1d;
            } else if (value[0].equals("+")) {
                this.coefficient = 1d;
            } else if (!value[0].equals("")) {
                this.coefficient = Double.parseDouble(value[0]);
            }
            if (value.length > 1) {
                this.power = Integer.parseInt(value[1]);
            }
        }
    }

    public Monomial(Double coefficient, Integer power) {
        //todo check for validation
        this.power = power;
        this.coefficient = coefficient;
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
