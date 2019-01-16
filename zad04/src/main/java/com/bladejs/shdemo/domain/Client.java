package com.bladejs.shdemo.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "client.byLogin", query = "Select c from Client c where c.login = :login")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String login;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GraphicsCard> graphicsCards;
    private String firstName="unkown";
    private String lastName="unkown";

    public long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public List<GraphicsCard> getGraphicsCards() {
        return graphicsCards;
    }
    public void setGraphicsCards(List<GraphicsCard> graphicsCards) {
        this.graphicsCards = graphicsCards;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
