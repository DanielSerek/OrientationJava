package com.example.orientationtestexample.services;

import com.example.orientationtestexample.models.URLAlias;
import com.example.orientationtestexample.repositories.URLAliasRepository;
import com.example.orientationtestexample.viewmodels.URLAliasDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class URLAliasServiceImpl implements URLAliasService{

    private URLAliasRepository urlAliasRepository;

    public URLAliasServiceImpl(URLAliasRepository urlAliasRepository) {
        this.urlAliasRepository = urlAliasRepository;
    }

    @Override
    public void addURLAlias(URLAlias urlAlias) {
        this.urlAliasRepository.save(urlAlias);
    }

    @Override
    public boolean checkAlias(URLAlias urlAlias) {
        for (URLAlias alias : this.urlAliasRepository.findAll()) {
            if(alias.getAlias().equals(urlAlias.getAlias())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getCode(String alias) {
        URLAlias urlAlias = getAlias(alias);
        if(urlAlias != null) {
            return urlAlias.getCode();
        }
        return null;
    }

    @Override
    public String getUrl(String alias) {
        URLAlias urlAlias = getAlias(alias);
        if(urlAlias != null) {
            return urlAlias.getUrl();
        }
        return null;
    }

    @Override
    public String hitCount(String alias) {
        URLAlias urlAlias = getAlias(alias);
        if(checkAlias(urlAlias)){
            if(urlAlias.getHitCount() == null){
                urlAlias.setHitCount(0L);
            }
            urlAlias.setHitCount(urlAlias.getHitCount() + 1);
            this.urlAliasRepository.save(urlAlias);
            return urlAlias.getUrl();
        }
        else{
            return "notfound";
        }

    }

    @Override
    public List<URLAlias> getAll() {
        return this.urlAliasRepository.findAll();
    }

    @Override
    public List<URLAliasDTO> getAllDTOs() {
        List<URLAlias> urlAliases = getAll();
        List<URLAliasDTO> urlAliasDTOS = new ArrayList<>();
        for (URLAlias urlAlias : urlAliases) {
            urlAliasDTOS.add(new URLAliasDTO(urlAlias));
        }
        return urlAliasDTOS;
    }

    @Override
    public String delete(Long id, String code) {
        URLAlias urlAlias = getAliasById(id);
        String status;
        if(urlAlias == null){
            return "notfound";
        }
        if(urlAlias.getCode().equals(code)){
            this.urlAliasRepository.deleteById(id);
            return "success";
        }
        return "incorrectcode";
    }

    private URLAlias getAlias(String alias){
        for (URLAlias urlalias : this.urlAliasRepository.findAll()) {
            if(urlalias.getAlias().equals(alias)){
                return urlalias;
            }
        }
        return null;
    }

    private URLAlias getAliasById(Long id){
        for (URLAlias urlalias : this.urlAliasRepository.findAll()) {
            if(urlalias.getId()==id){
                return urlalias;
            }
        }
        return null;
    }
}
