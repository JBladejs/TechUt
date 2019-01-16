package com.bladejs.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "car.available", query = "Select g from GraphicsCard g where g.sold = false")
})

public class GraphicsCard {
    private Long id;

    private Model model;
    private double tFlops;
    private Boolean sold = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }

    public double getTflops() {
        return tFlops;
    }
    public void setTflops(double tflops) {
        this.tFlops = tflops;
    }

    public boolean isSold() {
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
