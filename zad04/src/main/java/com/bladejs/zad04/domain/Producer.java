package com.bladejs.zad04.domain;

public class Producer {
    private long id;
    private String name;
    private String country;
    private CEO ceo;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public CEO getCeo() {
        return ceo;
    }
    public void setCeo(CEO ceo) {
        this.ceo = ceo;
    }
}
