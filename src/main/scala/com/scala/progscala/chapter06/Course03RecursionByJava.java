package com.scala.progscala.chapter06;

/**
 * Java  用命令式循环的一种阶乘实现
 */
public class Course03RecursionByJava {

    public static long factorial(long l){
        long result = 1L;
        for (long i = 2L; i<= l; i++){
            result *= i;
        }
        return result;
    }
    public static void main(String[] args) {
        // 循环变量 j 和计算结果均为可变变量
        for (long l = 1L; l <= 10; l++){
            System.out.printf("%d:\t%d\n", l, factorial(l));
        }
    }
}
