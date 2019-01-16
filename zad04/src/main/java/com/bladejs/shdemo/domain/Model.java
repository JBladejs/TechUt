package com.bladejs.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "model.all", query = "Select m from Model m")
})
public class Model {

    private Long id;

    private double tFlops;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
