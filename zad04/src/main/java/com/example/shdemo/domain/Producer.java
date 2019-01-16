package com.example.shdemo.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "producer.all", query = "Select p from Producer p")
})
public class Producer {

    public Producer(String name, String country){
        this.name = name;
        this.country = country;
    }

    public Producer(String name){
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true, nullable=false)
    private String name;
    private String country="unknown";
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Executive> executives = new ArrayList<Executive>();

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public List<Executive> getExecutives() {
        return executives;
    }
    public void setExecutives(List<Executive> executives) {
        this.executives = executives;
    }

}
