package com.bladejs.shdemo.service;

import com.bladejs.shdemo.domain.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class SellingManagerHibernate implements  SellingManager{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addClient(Client client) {
        client.setId(null);
        sessionFactory.getCurrentSession().persist(client);
    }

    @Override
    public List<Client> getAllClients() {
        return sessionFactory.getCurrentSession().getNamedQuery("client.all").list();
    }

    @Override
    public void deleteClient(Client client) {
        client = (Client) sessionFactory.getCurrentSession().get(Client.class, client.getId());

        //to add removing client's owned gpu's
    }

    @Override
    public Client findClientByLogin(String login) {
        return (Client) sessionFactory.getCurrentSession().getNamedQuery("client.byLogin").setString("login", login).uniqueResult();
    }

}
