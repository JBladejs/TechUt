package com.bladejs.zad04.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GraphicsCard {
    private long id;

    private int model;
    private Producer producer;
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

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
