package com.apiexercises.frontend.services;

import com.apiexercises.frontend.models.DoubledNumber;
import org.springframework.stereotype.Component;

@Component
public class DoublingService {
    public DoubledNumber doubleNumber(Long number) {
        return new DoubledNumber(number, number+number);
    }
}
