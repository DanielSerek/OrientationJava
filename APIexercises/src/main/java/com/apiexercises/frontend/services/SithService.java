package com.apiexercises.frontend.services;

import com.apiexercises.frontend.models.Yoda;
import org.springframework.stereotype.Service;

@Service
public class SithService {

    public Object manipulateText(Yoda yoda) {


        String text = yoda.getText();
        text.replaceAll("\\.", "");
        String[] words = text.split("\\s+");
        String newSentence = new String();
        int length = words.length - 1;
        if(words.length % 2 != 0) {
            length = words.length - 1;
        }

        for (int i = 0; i < length; i++) {
            newSentence += words[i + 1] + " ";
            newSentence += words[i] + " ";
            i++;
        }

        newSentence += "Arrgh. Uhmmm.";

        yoda.setSith_text(newSentence);
        return yoda;
    }
}
