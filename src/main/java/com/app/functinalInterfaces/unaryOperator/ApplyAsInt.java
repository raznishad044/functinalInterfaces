package com.app.functinalInterfaces.unaryOperator;

import java.util.function.IntUnaryOperator;

public class ApplyAsInt {
    /**
     *
     * applyAsInt()
     * This method takes in one int value, performs the given operation and returns an int-valued result.
     *
     * Syntax:
     *
     * int applyAsInt(int operand)
     *
     * Parameters: This method takes in one int valued parameter.
     * Returns:: It returns an int valued result.
     *
     *
     */

    public static void main(String[] args) {
        IntUnaryOperator op = a->2*a;
        System.out.println(op.applyAsInt(2000));
    }
}
