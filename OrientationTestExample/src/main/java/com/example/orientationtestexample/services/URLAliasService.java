package com.example.orientationtestexample.services;

import com.example.orientationtestexample.models.URLAlias;
import com.example.orientationtestexample.viewmodels.URLAliasDTO;

import java.util.List;

public interface URLAliasService {

    void addURLAlias(URLAlias urlAlias);

    boolean checkAlias(URLAlias urlAlias);

    String getCode(String alias);

    String getUrl(String alias);

    String hitCount(String alias);

    List<URLAlias> getAll();

    String delete(Long id, String code);

    List<URLAliasDTO> getAllDTOs();
}
