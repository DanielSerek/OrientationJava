package com.example.orientationtestexample.viewmodels;

import com.example.orientationtestexample.models.URLAlias;

public class URLAliasDTO {

    private Long id;
    private String url;
    private String alias;
    private Long hitCount;

    public URLAliasDTO(URLAlias urlAlias){
        this.id = urlAlias.getId();
        this.url = urlAlias.getUrl();
        this.alias = urlAlias.getAlias();
        this.hitCount = urlAlias.getHitCount();
    }

    public URLAliasDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }
}
