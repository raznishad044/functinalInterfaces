package com.app.functinalInterfaces.unaryOperator;

import java.util.function.IntUnaryOperator;

public class IdentityUnaryOperator {

    /**
     * 1. identity()
     * This method returns an IntUnaryOperator, which takes in one int value and returns it. The returned IntUnaryOperator does not perform any operation on its only value.
     *
     * Syntax:
     *
     * static IntUnaryOperator identity()
     *
     * Parameters: This method does not take any parameters.
     * Returns: It returns an IntUnaryOperator, which takes in one value and returns it.
     *
     */

    public static void main(String[] args) {



        IntUnaryOperator op = IntUnaryOperator.identity();
        System.out.println(" Year Of Implementation:" +op.applyAsInt(2025));
    }
}
