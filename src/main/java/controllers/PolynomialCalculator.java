package controllers;

import models.Polynomial;
import util.PolynomialUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PolynomialCalculator {

    private JPanel panel;
    private JTextField firstPolynom;
    private JTextField secondPolynom;

//    public String getFirstPolynomValue() {
//        return firstPolynom.getText();
//    }
//
//    public String getSecondPolynomValue() {
//        return secondPolynom.getText();
//    }
//
//    public void setFirstPolynomValue(String value) {
//        //todo check for validation
//        firstPolynom.setText(value);
//    }
//
//    public void setSecondPolynomValue(String value) {
//        //todo check for validation
//        secondPolynom.setText(value);
//    }
//
//    public void setResultPolynomValue(String value) {
//        //todo check for validation
//        resultPolynom.setText(value);
//    }
    private JButton btnDeriveFirst;
    private JButton btnDeriveSecond;
    private JButton btnMultiply;
    private JButton btnDivide;
    private JButton btnAdd;
    private JButton btnSubtract;
    private JButton btnIntegrateFirst;
    private JButton btnIntegrateSecond;
    private JTextArea resultPolynom;
    public PolynomialCalculator() {
        JFrame frame = new JFrame("Polynomial Calculator");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.addListeners();
    }

    private void addListeners(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                //check + transform + make polynom
                String inputFirst = firstPolynom.getText().replace(" ", "").replace("*", "");
                String inputSecond = secondPolynom.getText().replace(" ", "").replace("*", "");

                Polynomial first = Polynomial.parsePolynomial(inputFirst);
                Polynomial second = Polynomial.parsePolynomial(inputSecond);
                String result = "";
                switch (actionEvent.getActionCommand()){
                    case "deriveFirst":
                        result = PolynomialUtil.derive(first).getString();
                        break;
                    case "deriveSecond":
                        result = PolynomialUtil.derive(second).getString();
                        break;
                    case "integrateFirst":
                        result = PolynomialUtil.integrate(first).getString();
                        break;
                    case "integrateSecond":
                        result = PolynomialUtil.integrate(second).getString();
                        break;
                    case "add":
                        result = PolynomialUtil.add(first,second).getString();
                        break;
                    case "subtract":
                        result = PolynomialUtil.subtract(first, second).getString();
                        break;
                    case "divide":
                        result = PolynomialUtil.divide(first,second).getString();
                        break;
                    case "multiply":
                        result = PolynomialUtil.multiply(first, second).getString();
                        break;
                    default:
                        //todo throw exception here
                        break;
                }
                resultPolynom.setText(result);
            }
        };

        firstPolynom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

        secondPolynom.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });

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
