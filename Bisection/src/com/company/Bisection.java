package com.company;

import org.jetbrains.annotations.Contract;

import java.util.Scanner;

public class Bisection {

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("x^3 - 10x + 23 = 0");
        System.out.printf("low= ");
        double lo = in.nextDouble();
        System.out.printf("high= ");
        double hi = in.nextDouble();
        System.out.printf("r= ");
        double r = in.nextDouble();

        System.out.println("The result of x is "+solve(lo,hi,r));
    }

    public static double func(double x)
    {
        return (x*x*x)-10*x+23;
    }

    public static double solve(double lo, double hi, double r)
    {
        double mi = ( lo + hi ) / 2;
        if(func(mi) == 0 || Math.abs(lo-hi) <= r ) return mi;
        if(func(lo) * func(mi) < 0) return solve(lo, mi, r);
        else return solve(mi, hi, r);
    }
}
