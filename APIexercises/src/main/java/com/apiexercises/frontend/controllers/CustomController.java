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

    private DoublingService doublingService;
    private UntilService untilService;
    private ArrayService arrayService;
    private LogService logService;
    private SithService sithService;
    private CamellzerService camellzerService;

    @Autowired
    public CustomController(DoublingService doublingService, UntilService untilService, ArrayService arrayService, LogService logService, SithService sithService, CamellzerService camellzerService) {
        this.doublingService = doublingService;
        this.untilService = untilService;
        this.arrayService = arrayService;
        this.logService = logService;
        this.sithService = sithService;
        this.camellzerService = camellzerService;
    }

    @GetMapping("/doubling")
    public DoubledNumber doubling(@RequestParam(required = false) Long input) {
        logService.addLog(new Log("/doubling", "input=" + input));
        if (input == null) {
            return new DoubledNumber("Please provide an input!");
        } else return doublingService.doubleNumber(input);
    }

    @GetMapping("/greeter")
    public ResponseEntity<?> greeter(@RequestParam(required = false) String name, @RequestParam(required = false) String title, HttpServletResponse response) {
        logService.addLog(new Log("/greeter", "name=" + name + "&title=" + title));
        if (title == null || name == null) {
            if (name == null && title == null) {
                return new ResponseEntity<ErrorMessage>(new ErrorMessage("Please provide a name and a title!"), HttpStatus.BAD_REQUEST);
            } else if (name == null) {
                return new ResponseEntity<ErrorMessage>(new ErrorMessage("Please provide a name!"), HttpStatus.BAD_REQUEST);
            } else if (title == null) {
                return new ResponseEntity<ErrorMessage>(new ErrorMessage("Please provide a title!"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Greeting>(new Greeting (name, title), HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/appenda/{appended}")
    public ResponseEntity<?> appendA(@PathVariable (required = false) String appended) {
        if(appended == null){
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Missing input parameter"), HttpStatus.NOT_FOUND);
        }
        logService.addLog(new Log("/appenda/" + appended, "appended=" + appended));
        return new ResponseEntity<AppendA>(new AppendA(appended), HttpStatus.OK);
    }

    @PostMapping("/dountil/{action}")
    public NumberRequest doUntil(@PathVariable String action, @RequestBody NumberRequest nr) {
        logService.addLog(new Log("/dountil/" + action, "action=" + action));
        this.untilService.calculate(action, nr);
        return nr;
    }

    @PostMapping("/arrays")
    public ResponseEntity<?> arrayHandler(@RequestBody ArrayHandler arrayHandler, HttpServletResponse response) {
        logService.addLog(new Log("/arrays", arrayHandler.toString()));
        if(arrayHandler.getWhat() == null || arrayHandler.getNumbers() == null) {
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Please provide what to do with the numbers!"), HttpStatus.BAD_REQUEST);
        }

        if(arrayHandler.getWhat() == What.Double) {
            return new ResponseEntity<Result>((Result)this.arrayService.arrayHandlerDouble(arrayHandler), HttpStatus.OK);
        }
        else {
            this.arrayService.arrayHandler(arrayHandler);
            return new ResponseEntity<ArrayHandler>(arrayHandler, HttpStatus.OK);
        }
    }

    @GetMapping("/log")
    public List<Log> getLogs(@RequestParam(required = false) Long count, @RequestParam(required = false) Long page, @RequestParam(required = false) String q) {
        if(count != null){
            return this.logService.getLogs(count);
        }
        if(page != null){
            return this.logService.getPage(page);
        }
        if(q != null){
            return this.logService.search(q);
        }
        if(count == null) {
            return this.logService.getLogs(-1);
        }
        return null;
    }

    @PostMapping("/sith")
    public ResponseEntity<?> sithReverser(@RequestBody (required = false) Yoda yoda) {
        if(yoda.getText() == null){
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Feed me some text you have to, padawan young you are. Hmmm."), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Yoda>((Yoda) this.sithService.manipulateText(yoda),HttpStatus.OK);
    }

    @PostMapping("/translate")
    public ResponseEntity<?> camellzer(@RequestBody (required = false) Camellzer camellzer) {
        if(camellzer.getText() == null || camellzer.getLang() == null){
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("I can't translate that!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<CamellzerTranslated>((CamellzerTranslated) this.camellzerService.translate(camellzer),HttpStatus.OK);
    }
}

