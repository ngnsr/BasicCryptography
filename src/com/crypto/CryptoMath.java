package com.crypto;

import java.util.Arrays;

public class CryptoMath {
    public int gcd(int a, int b) {
        int temp;
        while (a != 0) {
            temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

    public long gcd(long a, long b) {
        long temp;
        while (a != 0) {
            temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }

    public Integer findModInverse(int a, int m) {
        if (gcd(a, m) != 1) return null;
        double u1 = 1, u2 = 0, u3 = a;
        double v1 = 0, v2 = 1, v3 = m;
        while (v3 != 0) {
            int q = (int) (u3 / v3);
            double tv1 = v1, tv2 = v2, tv3 = v3;
            v1 = u1 - q * v1;
            v2 = u2 - q * v2;
            v3 = u3 - q * v3;
            u1 = tv1;
            u2 = tv2;
            u3 = tv3;
        }
        int i = (int) u1 % m;
        if (i == 0) return null;
        return (i < 0) ? i + m : i;
    }

    public Long findModInverse(long a, long m) {
        if (gcd(a, m) != 1) return null;
        double u1 = 1, u2 = 0, u3 = a;
        double v1 = 0, v2 = 1, v3 = m;
        while (v3 != 0) {
            long q = (long) (u3 / v3);
            double tv1 = v1, tv2 = v2, tv3 = v3;
            v1 = u1 - q * v1;
            v2 = u2 - q * v2;
            v3 = u3 - q * v3;
            u1 = tv1;
            u2 = tv2;
            u3 = tv3;
        }
        long i = (long) u1 % m;
        if (i == 0) return null;
        return (i < 0) ? i + m : i;
    }

    public int[] getPrimeNumberOfRange(int range){
        int[] num = new int[range];
        boolean prime;
        num[0] = 1;
        int primeInd = 0;
        for (int i = 0; i < range; i++) {
            prime = true;
            int nextNumber = i + 1;
            for (int j = 1; j < primeInd - 1; j++) {
                if (num[j] == -1) continue;
                if (nextNumber % num[j] == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime){
                num[primeInd] = nextNumber;
                primeInd++;
            }
        }
       /* for (int i = 2; i < range; i++) {

            if (num[i] == -1) continue;
            for (int j = i+1; j < range  ; j++) {
                if (num[j] % modeN == 0) num[j] = -1;
            }
            modeN = num[i];

        }*/
        return Arrays.copyOfRange(num,0, primeInd);
        //return Arrays.stream(num).filter(i -> i!=0).toArray();
    }




}
