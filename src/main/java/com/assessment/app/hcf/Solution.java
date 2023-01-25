package com.assessment.app.hcf;

public class Solution {

    public static int highestCommonFactor(int[] numbers) {
        if (numbers == null || numbers.length <= 1) {
            throw new IllegalArgumentException("The numbers array should contain at least 2 values");
        }

        int max = numbers[0], min = numbers[0];

        for (int number: numbers) {
            max = Math.max(max, number);
            min = Math.min(min, number);
        }

        return gcd(max, min);
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        if (a < b) return gcd(b, a);
        return gcd(a % b, b);
    }

}
