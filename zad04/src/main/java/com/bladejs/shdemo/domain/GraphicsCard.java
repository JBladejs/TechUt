package com.bladejs.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "graphicsCard.available", query = "Select g from GraphicsCard g where g.sold = false"),
        @NamedQuery(name = "graphicsCard.byModel", query = "Select g from GraphicsCard g where g.model = :model")
})

public class GraphicsCard {
    private Long id;

    private Model model;
    private Boolean sold = false;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Model getModel() {
        return model;
    }
    public void setModel(Model model) {
        this.model = model;
    }

    public boolean isSold() {
        return sold;
    }
    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
