package com.app.functinalInterfaces.unaryOperator;

import java.util.function.IntUnaryOperator;

public class AndThen {

    public static void main(String[] args) {

        IntUnaryOperator op = a->12*a;
       op= op.andThen(a->23+a);
        System.out.println(op.applyAsInt(12));
    }
}
