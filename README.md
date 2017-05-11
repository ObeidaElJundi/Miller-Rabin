# Miller-Rabin
Efficiently testing whether a number is prime is a crucial problem in cryptography, because the security of many cryptosystems depends on the use of large randomly chosen primes.
The Miller-Rabin primality test is among the fastest and most widely used primality tests in computational practice. This JAVA GUI to can determine whether a given number is prime or not using Miller Rabin Algorithm. It works on Windows, Linux, and MAC.
#### Comparing the speed of the Miller-Rabin test to a trial division test
For small n, a brute force trial division test is much faster. For very large prime n, the Miller-Rabin test is the winner; for example, in Google Chrome 11 on a modern 2.5GHz laptop (a) 30 rounds of the Miller-Rabin test for a 19-digit prime take about 30 milliseconds, and (b) 30 rounds for a 30-digit prime take about 45 milliseconds; so a single round takes about 1.0ms to 1.5ms. The trial division test becomes extremely slow as n grows larger (it takes 1 minute for a 19-digit prime in the same browser on the same machine). (source: http://www.javascripter.net/math/primes/millerrabinprimalitytest.htm)

![miller](https://cloud.githubusercontent.com/assets/9033365/25948618/ffdfc868-365c-11e7-9da3-a83617e200bf.PNG)
