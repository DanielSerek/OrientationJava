package com.example.orientationtestexample.controllers;

import com.example.orientationtestexample.models.URLAlias;
import com.example.orientationtestexample.services.URLAliasService;
import com.example.orientationtestexample.viewmodels.URLAliasDTO;
import com.fasterxml.jackson.databind.node.TextNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class URLAliasController {

    private URLAliasService urlAliasService;

    public URLAliasController(URLAliasService urlAliasService) {
        this.urlAliasService = urlAliasService;
    }

    @GetMapping("/")
    public String getIndex(@RequestParam(required = false) String status,
                           @RequestParam(required = false) String alias,
                           @RequestParam(required = false) String url,
                           Model model){
        if(status == null){
            return "index";
        }
        if(status.equals("error")){
            model.addAttribute("login", "error");
            model.addAttribute("url", url);
            model.addAttribute("alias", alias);
            return "index";
        }
        else if (status.equals("success")){
            model.addAttribute("login", "success");
            model.addAttribute("alias", alias);
            model.addAttribute("code",this.urlAliasService.getCode(alias));
            return "index";
        }
        else return "index";
    }

    @PostMapping("/save-link")
    public String saveLink(@ModelAttribute("urlAlias") URLAlias urlAlias){
        if(urlAliasService.checkAlias(urlAlias)){
            return "redirect:/?status=error&alias=" + urlAlias.getAlias() + "&url=" + urlAlias.getUrl();
        }
        else {
            urlAlias.setCode();
            urlAliasService.addURLAlias(urlAlias);
            return "redirect:/?status=success&alias=" + urlAlias.getAlias();
        }
    }
    @GetMapping("/a/{alias}")
    public ModelAndView hitCount(@PathVariable (required = false) String alias, HttpServletResponse resp) {
        String response = urlAliasService.hitCount(alias);
        if(response.equals("notfound")){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        else{
            return new ModelAndView("redirect:" + response);
        }
    }

    @GetMapping("/api/links")
    public ResponseEntity<?> apiLinks() {
        return new ResponseEntity<List<URLAliasDTO>>(this.urlAliasService.getAllDTOs(), HttpStatus.OK);
    }

    @PostMapping("/api/links/{id}")
    public String deleteAlias(@PathVariable Long id, @RequestBody TextNode secretCode, HttpServletResponse resp) {
        String status = urlAliasService.delete(id, String.valueOf(secretCode));
        if(status == "success"){
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        else if(status == "notfound"){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else if(status == "incorrectcode"){
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return "index";
    }

}
