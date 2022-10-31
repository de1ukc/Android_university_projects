package com.example.calculator.services;


import static java.lang.Double.isNaN;

import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;


public class CalcsMath {
    public static String Calculate(String expr) {
        Expression expression = new Expression(expr);
        double answer = expression.calculate();


        if (isNaN(answer)) {
            return "Wrong Expression";
        }

        if (expr.contains(".0") && expr.length() > 16 && answer == 0){
            return "Lol";
        }
        String s = Double.toString(answer);
        if (s.length() > 1 && s.substring(s.length() - 2, s.length()).equals(".0")) {
            return s.substring(0, s.length() - 2);
        } else {
            return s;
        }
    }
}
