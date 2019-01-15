package com.bladejs.shdemo.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GraphicsCard {
    private long id;

    private String model;
    private double tflops;
    private float price;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public double getTflops() {
        return tflops;
    }
    public void setTflops(double tflops) {
        this.tflops = tflops;
    }

    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
}
