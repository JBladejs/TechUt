package com.bladejs.shdemo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "client.all", query = "Select c from Client c")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GraphicsCard> graphicsCards;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public List<GraphicsCard> getGraphicsCards() {
        return graphicsCards;
    }
    public void setGraphicsCards(List<GraphicsCard> graphicsCards) {
        this.graphicsCards = graphicsCards;
    }
}
