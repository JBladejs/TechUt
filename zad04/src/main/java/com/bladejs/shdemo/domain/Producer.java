package com.bladejs.shdemo.domain;

import javax.persistence.*;

public class Producer {
    private long id;
    private String name;
    private String country;
    private CEO ceo;
    private GraphicsCard gpu;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(unique=true, nullable=false)
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public CEO getCeo() {
        return ceo;
    }
    public void setCeo(CEO ceo) {
        this.ceo = ceo;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public GraphicsCard getGpu() {
        return gpu;
    }
    public void setGpu(GraphicsCard gpu) {
        this.gpu = gpu;
    }

}
