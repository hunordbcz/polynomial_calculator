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

    private void addListeners() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String inputFirst = firstPolynom.getText().replace(" ", "").replace("*", "");
                String inputSecond = secondPolynom.getText().replace(" ", "").replace("*", "");

                Polynomial first = null;
                Polynomial second = null;
                String feedbackText = "All good";
                String result = "";

                try {
                    second = Polynomial.parsePolynomial(inputSecond);
                } catch (Exception e) {
                    feedbackText = "Second polynom is invalid";
                }

                try {
                    first = Polynomial.parsePolynomial(inputFirst);
                } catch (Exception e) {
                    feedbackText = "First polynom is invalid";
                }

                if (feedbackText.equals("All good")) {
                    switch (actionEvent.getActionCommand()) {
                        case "deriveFirst":
                            try {
                                result = PolynomialUtil.derive(first).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage() + " 1";
                            }
                            break;
                        case "deriveSecond":
                            try {
                                result = PolynomialUtil.derive(second).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage() + " 2";
                            }
                            break;
                        case "integrateFirst":
                            try {
                                result = PolynomialUtil.integrate(first).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage() + " 1";
                            }
                            break;
                        case "integrateSecond":
                            try {
                                result = PolynomialUtil.integrate(second).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage() + " 2";
                            }
                            break;
                        case "add":
                            try {
                                result = PolynomialUtil.add(first, second).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage();
                            }
                            break;
                        case "subtract":
                            try {
                                result = PolynomialUtil.subtract(first, second).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage();
                            }
                            break;
                        case "divide":
                            try {
                                result = PolynomialUtil.divide(first, second).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage();
                            }
                            break;
                        case "multiply":
                            try {
                                result = PolynomialUtil.multiply(first, second).getString();
                            } catch (Exception e) {
                                feedbackText = e.getMessage();
                            }
                            break;
                        default:
                            feedbackText = "Invalid operation";
                            break;
                    }
                }
                resultPolynom.setText(result);
                feedback.setText(feedbackText);
            }
        };

        btnDeriveFirst.addActionListener(actionListener);
        btnDeriveSecond.addActionListener(actionListener);
        btnMultiply.addActionListener(actionListener);
        btnDivide.addActionListener(actionListener);
        btnAdd.addActionListener(actionListener);
        btnSubtract.addActionListener(actionListener);
        btnIntegrateFirst.addActionListener(actionListener);
        btnIntegrateSecond.addActionListener(actionListener);
    }

}
