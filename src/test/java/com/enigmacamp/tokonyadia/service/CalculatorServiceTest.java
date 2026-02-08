package com.enigmacamp.tokonyadia.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorServiceTest {

    @Autowired
    CalculatorService calculatorService;

    @Test
    void addNumberMustBeReturn5() {
//        GIVEN / Provide
        int a = 2;
        int b = 3;

//        WHEN /Actual
        int result = calculatorService.addNumber(a, b);

//        THEN / Expected
        assertEquals(5, result);
    }

    @Test
    void addNumberMustBeReturn15() {
        int a = 12;
        int b = 3;

        int result = calculatorService.addNumber(a, b);

        assertEquals(15, result);
    }

    @Test
    void minNumber() {

    }
}