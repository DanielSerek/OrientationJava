package com.apiexercises.frontend.services;

import com.apiexercises.frontend.models.Camellzer;
import com.apiexercises.frontend.models.CamellzerTranslated;
import org.springframework.stereotype.Component;

@Component
public class CamellzerService {
    public CamellzerTranslated translate(Camellzer camellzer) {
        String text = camellzer.getText();
        String newText = "";
        String append = "";

        if(camellzer.getLang().equals("hungarian")){
            append = "ve";
        }

        if(camellzer.getLang().equals("english")){
            append = "sh";
        }

        for (int i = 0; i < text.length(); i++) {
            char x = text.charAt(i);
            if (x == 'a' || x == 'A' || x == 'e' || x == 'E' || x == 'i' || x == 'I'
            || x == 'o' || x == 'O' || x == 'u' || x == 'U') {
                newText += x + append;
            }
            else{
                newText += x;
            }
        }
        if(camellzer.getLang().equals("hungarian")){
            return new CamellzerTranslated(newText, "teve");
        }
        if(camellzer.getLang().equals("english")){
            return new CamellzerTranslated(newText, "gibberish");
        }
        else return null;
    }
}
