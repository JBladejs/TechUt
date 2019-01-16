package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "graphicsCardInfo.all", query = "Select m from GraphicsCardInfo m")
})
public class GraphicsCardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double tFlops;
    @Column(unique = true, nullable = false)
    private String model;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getTflops() {
        return tFlops;
    }
    public void setTflops(double tflops) {
        this.tFlops = tflops;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}
