package com.apiexercises.frontend.controllers;

import com.apiexercises.frontend.models.*;
import com.apiexercises.frontend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CustomController {

    @Autowired
    DoublingService doublingService;
    @Autowired
    UntilService untilService;
    @Autowired
    ArrayService arrayService;
    @Autowired
    LogService logService;
    @Autowired
    SithService sithService;
    @Autowired
    CamellzerService camellzerService;


    @GetMapping("/doubling")
    DoubledNumber doubling(@RequestParam(required = false) Long input) {
        logService.addLog(new Log("/doubling", "input=" + input));
        if (input == null) {
            return new DoubledNumber("Please provide an input!");
        } else return doublingService.doubleNumber(input);
    }

    @GetMapping("/greeter")
    public Greeting greeter(@RequestParam(required = false) String name, @RequestParam(required = false) String title, HttpServletResponse response) {
        logService.addLog(new Log("/greeter", "name=" + name + "&title=" + title));
        if (title == null || name == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if (name == null && title == null) {
                return new Greeting("Please provide a name and a title!");
            } else if (name == null) {
                return new Greeting("Please provide a name!");
            } else if (title == null) {
                return new Greeting("Please provide a title!");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            return new Greeting(name, title);
        }
        return null;
    }

    @GetMapping("/appenda/{appended}")
    ResponseEntity<AppendA> appendA(@PathVariable String appended) {
        logService.addLog(new Log("/appenda/" + appended, "appended=" + appended));
        return new ResponseEntity<AppendA>(new AppendA(appended), HttpStatus.OK);
    }

    @GetMapping("/appenda")
    void noAppendableProvided(HttpServletResponse response) {
        logService.addLog(new Log("/appenda/", "Invalid operation"));
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @PostMapping("/dountil/{action}")
    NumberRequest doUntil(@PathVariable String action, @RequestBody NumberRequest nr) {
        logService.addLog(new Log("/dountil/" + action, "action=" + action));
        this.untilService.calculate(action, nr);
        return nr;
    }

    @PostMapping("/arrays")
    ResponseEntity<?> arrayHandler(@RequestBody ArrayHandler arrayHandler, HttpServletResponse response) {
        logService.addLog(new Log("/arrays", arrayHandler.toString()));
        if(arrayHandler.getWhat() == null || arrayHandler.getNumbers() == null) {
            return new ResponseEntity<ErrorObj>(new ErrorObj("Please provide what to do with the numbers!"), HttpStatus.BAD_REQUEST);
        }
        this.arrayService.arrayHandler(arrayHandler);
        if(arrayHandler.getResult() == null) return new ResponseEntity<Result>((Result)this.arrayService.arrayHandler(arrayHandler), HttpStatus.OK);
        else return new ResponseEntity<ArrayHandler>(arrayHandler, HttpStatus.OK);
    }

    @GetMapping("/log")
    List<Log> getLogs(@RequestParam(required = false) Long count, @RequestParam(required = false) Long page) {
        if(count != null){
            return this.logService.getLogs(count);
        }
        if(page != null){
            return this.logService.getPage(page);
        }
        if(count == null) {
            return this.logService.getLogs(-1);
        }
        return null;
    }

    @PostMapping("/sith")
    public ResponseEntity<?> sithReverser(@RequestBody (required = false) Yoda yoda) {
        if(yoda.getText() == null){
            return new ResponseEntity<ErrorObj>(new ErrorObj("Feed me some text you have to, padawan young you are. Hmmm."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Yoda>((Yoda) this.sithService.manipulateText(yoda),HttpStatus.OK);
    }

    @PostMapping("/translate")
    public ResponseEntity<?> camellzer(@RequestBody (required = false) Camellzer camellzer) {
        if(camellzer.getText() == null || camellzer.getLang() == null){
            return new ResponseEntity<ErrorObj>(new ErrorObj("I can't translate that!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CamellzerTranslated>((CamellzerTranslated) this.camellzerService.translate(camellzer),HttpStatus.OK);
    }
}

