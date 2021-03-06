package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "graphicsCard.available", query = "Select g from GraphicsCard g where g.sold = false"),
        @NamedQuery(name = "graphicsCard.byModel", query = "Select g from GraphicsCard g where g.graphicsCardInfo.model = :model")
})

public class GraphicsCard {

    public GraphicsCard(String model, Double tFlops){
        this.graphicsCardInfo = new GraphicsCardInfo(model, tFlops);
    }

    public GraphicsCard(String model){
        this.graphicsCardInfo = new GraphicsCardInfo(model);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private GraphicsCardInfo graphicsCardInfo;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Producer producer;
    private Boolean sold = false;

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public GraphicsCardInfo getGraphicsCardInfo() {
        return graphicsCardInfo;
    }
    public void setGraphicsCardInfo(GraphicsCardInfo graphicsCardInfo) {
        this.graphicsCardInfo = graphicsCardInfo;
    }

    public Producer getProducer() {
        return producer;
    }
    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public boolean isSold() {
        return sold;
    }
    public void setSold(Boolean sold){
        this.sold=sold;
    }
}
