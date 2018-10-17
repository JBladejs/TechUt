package com.example.zad02.domain;

public class GraphicsCard {
    private long id;

    private String model;
    private String producer;
    private double tflops;

    public GraphicsCard(){
    }

    public GraphicsCard(String model, String producer, double tflops){
        super();
        this.model=model;
        this.producer=producer;
        this.tflops=tflops;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
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
}
