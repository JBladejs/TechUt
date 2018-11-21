package com.bladejs.zad04.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GraphicsCard {
    private long id;

    private int model;
    private String producer;
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

    public int getModel() {
        return model;
    }
    public void setModel(int model) {
        this.model = model;
    }

    public String getProducer() {
        return producer;
    }
    public void setProducer(String producer) {
        this.producer = producer;
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
