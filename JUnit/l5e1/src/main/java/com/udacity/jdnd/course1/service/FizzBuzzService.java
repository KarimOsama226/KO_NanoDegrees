package com.udacity.jdnd.course1.service;

import java.lang.reflect.UndeclaredThrowableException;

public class FizzBuzzService {

    /**
     * If number is divisible by 3, return "Fizz". If divisible by 5,
     * return "Buzz", and if divisible by both, return "FizzBuzz". Otherwise,
     * return the number itself.
     *
     * @Throws IllegalArgumentException for values < 1
     */
    public String fizzBuzz(int number)  {
        String result = String.valueOf(number);
        if (number < 1 )
        {
            throw new IllegalArgumentException("Input cannot be negative: " + number);
        }
        else if ((number % 3 == 0) && (number % 5 == 0))
        {
            result = "FizzBuzz";
        }
        else if (number % 3 == 0)
        {
            result = "Fizz";
        }
        else if (number % 5 == 0)
        {
            result = "Buzz";
        }
        return result;
    }


}
