package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "graphicsCard.available", query = "Select g from GraphicsCard g where g.sold = false"),
        @NamedQuery(name = "graphicsCard.byProducer", query = "Select g from GraphicsCard g where g.producer.name = :producer")
})

public class GraphicsCard {

    GraphicsCard(Producer producer, String model){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
