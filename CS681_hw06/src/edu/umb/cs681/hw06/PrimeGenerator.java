package edu.umb.cs681.hw06;

import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator {
    protected long from;
    protected long to;
    protected List<Long> primes =  new ArrayList<Long>();

    public PrimeGenerator(Long from, Long to){
        this.from = from;
        this.to = to;
    }



    public List<Long> getPrimes(){
        return this.primes;
    }

    // Optimal solution for PrimeNumber check BigO(Square root of n)
    protected Boolean IsPrime(long n){
        // Corner cases
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;

        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;

        return true;
    }
}
