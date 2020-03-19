package views;

import models.Polynomial;
import util.PolynomialUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewController {

    private JPanel panel;
    private JTextField firstPolynom;
    private JTextField secondPolynom;
    private JButton btnDeriveFirst;
    private JButton btnDeriveSecond;
    private JButton btnMultiply;
    private JButton btnDivide;
    private JButton btnAdd;
    private JButton btnSubtract;
    private JButton btnIntegrateFirst;
    private JButton btnIntegrateSecond;
    private JTextArea resultPolynom;
    private JTextArea feedback;

    public ViewController() {
        JFrame frame = new JFrame("Polynomial Calculator");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.addListeners();
    }

    private void deriveFirstListener() {
        btnDeriveFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial polynomial = Polynomial.parsePolynomial(getFirstPolynom());
                    setResult(PolynomialUtil.derive(polynomial).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private void deriveSecondListener() {
        btnDeriveSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial polynomial = Polynomial.parsePolynomial(getSecondPolynom());
                    setResult(PolynomialUtil.derive(polynomial).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private void integrateFirstListener() {
        btnIntegrateFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial polynomial = Polynomial.parsePolynomial(getFirstPolynom());
                    setResult(PolynomialUtil.integrate(polynomial).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private void integrateSecondListener() {
        btnIntegrateSecond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial polynomial = Polynomial.parsePolynomial(getSecondPolynom());
                    setResult(PolynomialUtil.integrate(polynomial).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private void muliplyListener() {
        btnMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial first = Polynomial.parsePolynomial(getFirstPolynom());
                    Polynomial second = Polynomial.parsePolynomial(getSecondPolynom());
                    setResult(PolynomialUtil.multiply(first, second).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private void divideListener() {
        btnDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial first = Polynomial.parsePolynomial(getFirstPolynom());
                    Polynomial second = Polynomial.parsePolynomial(getSecondPolynom());
                    setResult(PolynomialUtil.divide(first, second).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private void addListener() {
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial first = Polynomial.parsePolynomial(getFirstPolynom());
                    Polynomial second = Polynomial.parsePolynomial(getSecondPolynom());
                    setResult(PolynomialUtil.add(first, second).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private void subtractListener() {
        btnSubtract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    Polynomial first = Polynomial.parsePolynomial(getFirstPolynom());
                    Polynomial second = Polynomial.parsePolynomial(getSecondPolynom());
                    setResult(PolynomialUtil.subtract(first, second).getString());
                    setFeedback("All good");
                } catch (Exception e) {
                    setFeedback(e.getMessage());
                }
            }
        });
    }

    private String simplify(String value) {
        return value.replace(" ", "").replace("*", "");
    }

    private String getFirstPolynom() {
        return simplify(firstPolynom.getText());
    }

    private String getSecondPolynom() {
        return simplify(secondPolynom.getText());
    }

    private void setResult(String value) {
        this.resultPolynom.setText(value);
    }

    private void setFeedback(String value) {
        this.feedback.setText(value);
    }

    private void addListeners() {
        subtractListener();
        divideListener();
        deriveFirstListener();
        deriveSecondListener();
        integrateFirstListener();
        integrateSecondListener();
        addListener();
        muliplyListener();
    }

}
