package com.bladejs.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "graphicsCard.available", query = "Select g from GraphicsCard g where g.sold = false"),
        @NamedQuery(name = "graphicsCard.byModel", query = "Select g from GraphicsCard g where g.model = :model")
})

public class GraphicsCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private GraphicsCardInfo graphicsCardInfo;
    private Boolean sold = false;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public GraphicsCardInfo getGraphicsCardInfo() {
        return graphicsCardInfo;
    }
    public void setGraphicsCardInfo(GraphicsCardInfo graphicsCardInfo) {
        this.graphicsCardInfo = graphicsCardInfo;
    }

    public boolean isSold() {
        return sold;
    }
    public void sold() {
        this.sold = true;
    }
}
