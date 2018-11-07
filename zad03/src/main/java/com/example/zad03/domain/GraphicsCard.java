package com.example.zad03.domain;

public class GraphicsCard {
    private long id;

    private int model;
    private String producer;
    private double tflops;
    private float price;

    public GraphicsCard(){
    }

    public GraphicsCard(int model, String producer, double tflops, float price){
        super();
        this.model=model;
        this.producer=producer;
        this.tflops=tflops;
        this.price=price;
    }


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
