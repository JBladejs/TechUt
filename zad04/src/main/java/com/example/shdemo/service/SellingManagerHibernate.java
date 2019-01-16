package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.GraphicsCard;
import com.example.shdemo.domain.GraphicsCardInfo;
import com.example.shdemo.domain.Producer;
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
    public void addProducer(Producer producer) {
        producer.setId(null);
        sessionFactory.getCurrentSession().persist(producer);
    }

    @Override
    public void deleteProducer(Producer producer) {
        producer = (Producer) sessionFactory.getCurrentSession().get(Producer.class, producer.getId());
        sessionFactory.getCurrentSession().delete(producer);
    }

    @Override
    public List<Producer> getAllProducers() {
        return null;
    }

    @Override
    public Producer findProducerById(Long id) {
        return null;
    }

    @Override
    public void addGraphicsCard(GraphicsCard gpu) {
        gpu.setId(null);
        sessionFactory.getCurrentSession().persist(gpu);
}

    @Override
    public void deleteGraphicsCard(GraphicsCard gpu) {
        gpu = (GraphicsCard) sessionFactory.getCurrentSession().get(GraphicsCard.class, gpu.getId());
        GraphicsCardInfo gpuInfo = (GraphicsCardInfo) sessionFactory.getCurrentSession().get(GraphicsCardInfo.class, gpu.getGraphicsCardInfo().getId());

        sessionFactory.getCurrentSession().delete(gpuInfo);
        sessionFactory.getCurrentSession().delete(gpu);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GraphicsCard> getAllGraphicsCards() {
        return sessionFactory.getCurrentSession().getNamedQuery("graphicsCard.all").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GraphicsCard> getGraphicsCardByProducer(Producer producer) {
        return sessionFactory.getCurrentSession().getNamedQuery("graphicsCard.byProducer").setString("producer", producer.getName()).list();
    }

    @Override
    public GraphicsCard findGraphicsCardById(Long id) {
        return (GraphicsCard) sessionFactory.getCurrentSession().get(GraphicsCard.class, id);
    }

    @Override
    public void addClient(Client client) {
        client.setId(null);
        sessionFactory.getCurrentSession().persist(client);
    }

    @Override
    public void deleteClient(Client client) {
        client = (Client) sessionFactory.getCurrentSession().get(Client.class, client.getId());

        List<GraphicsCard> ownedGpus = client.getGraphicsCards();
        for (GraphicsCard gpu: ownedGpus) {
            gpu.setSold(false);
        }
        sessionFactory.getCurrentSession().delete(client);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getAllClients() {
        return sessionFactory.getCurrentSession().getNamedQuery("client.all").list();
    }

    @Override
    public Client findClientByLogin(String login) {
        return (Client) sessionFactory.getCurrentSession().getNamedQuery("client.byLogin").setString("login", login).uniqueResult();
    }

    @Override
    public Client findClientById(Long id) {
        return (Client) sessionFactory.getCurrentSession().get(Client.class, id);
    }


}
