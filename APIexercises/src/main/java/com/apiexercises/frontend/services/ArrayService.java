package com.apiexercises.frontend.services;

import com.apiexercises.frontend.models.ArrayHandler;
import com.apiexercises.frontend.models.Result;
import com.apiexercises.frontend.models.What;
import org.springframework.stereotype.Component;

@Component
public class ArrayService {

    public void arrayHandler(ArrayHandler arrayHandler) {
        if (arrayHandler.getWhat() == What.Sum) {
            long sum = 0;
            for (int number : arrayHandler.getNumbers()) {
                sum += number;
            }
            arrayHandler.setResult(sum);
        }

        if (arrayHandler.getWhat() == What.Multiply) {
            long sum = 1;
            for (int number : arrayHandler.getNumbers()) {
                sum *= number;
            }
            arrayHandler.setResult(sum);
        }
    }

    public Result arrayHandlerDouble(ArrayHandler arrayHandler){
        if(arrayHandler.getWhat()== What.Double){
            Integer[] output = new Integer[arrayHandler.getNumbers().length];
            for (int i = 0; i < arrayHandler.getNumbers().length; i++) {
                output[i] = 2 * arrayHandler.getNumbers()[i];
            }
            Result result = new Result();
            result.setResult(output);
            return result;
        }
        return null;
    }
}
