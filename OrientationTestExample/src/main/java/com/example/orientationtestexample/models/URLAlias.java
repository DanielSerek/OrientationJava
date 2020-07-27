package com.example.orientationtestexample.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class URLAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String alias;
    private String code;
    private Long hitCount;

    public URLAlias() {
    }

    public URLAlias(String url, String alias) {
        this.url = url;
        this.alias = alias;
        this.code = String.valueOf(Math.round(Math.random() * 10000) + 10000).substring(1);
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

    public String getCode() {
        return code;
    }

    public void setCode() {
        this.code = String.valueOf(Math.round(Math.random() * 10000) + 10000).substring(1);
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }
}
