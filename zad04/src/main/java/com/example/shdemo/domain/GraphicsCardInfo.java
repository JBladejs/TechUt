package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
public class GraphicsCardInfo {

    GraphicsCardInfo(String model, Double tFlops){
        this.model = model;
        this.tFlops = tFlops;
    }

    GraphicsCardInfo(String model){
        this.model = model;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double tFlops;
    @Column(unique = true, nullable = false)
    private String model;

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getTflops() {
        return tFlops;
    }
    public void setTflops(Double tflops) {
        this.tFlops = tflops;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
