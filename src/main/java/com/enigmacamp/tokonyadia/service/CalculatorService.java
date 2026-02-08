package com.enigmacamp.tokonyadia.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public int addNumber(int a, int b){
        return a+b;
    }
    public int minNumber(int a, int b){
        a = 10;
        b=2;
        return a-b;
    }
}
