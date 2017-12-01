import com.sun.security.auth.SolarisPrincipal;

import java.util.*;
import java.lang.Math.*;

public class maxPrime {
    public static void main(String[] args) {
        int MaxPrime = 0;
        for(int i=10000000;i>=0;i--)
        {
            if(IsPrime(i))
            {
                MaxPrime = i;
                break;
            }
        }

        System.out.println("10000000内最大的素数是"+MaxPrime);
    }

    static boolean IsPrime(int number) {
        int k = (int) Math.sqrt(number);

        for (int i = 2; i <= k; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
