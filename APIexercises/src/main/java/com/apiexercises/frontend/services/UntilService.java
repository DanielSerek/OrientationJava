package com.apiexercises.frontend.services;

import com.apiexercises.frontend.models.NumberRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class UntilService {
    public void calculate(@PathVariable String action, @RequestBody NumberRequest nr) {
        if(action.equals("sum")){
            int sum = 0;
            for (int i = 0; i < nr.getUntil() + 1; i++){
                sum += i;
            }
            nr.setResult(sum);
        }
        if(action.equals("factor")){
            int factor = 1;
            for (int i = 1; i < nr.getUntil() + 1; i++){
                factor *= i;
            }
            nr.setResult(factor);
        }
    }
}
