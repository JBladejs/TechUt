package com.example.shdemo.service;

import com.example.shdemo.domain.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.ArrayList;
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
    public void addExecutive(Executive executive) {
        executive.setId(null);
        sessionFactory.getCurrentSession().persist(executive);
    }

    @Override
    public void deleteExecutive(Executive executive) {
        executive = (Executive) sessionFactory.getCurrentSession().get(Executive.class, executive.getId());
        sessionFactory.getCurrentSession().delete(executive);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Executive> getAllExecutives() {
        return sessionFactory.getCurrentSession().getNamedQuery("executive.all").list();
    }

    @Override
    public Executive findExecutiveById(Long id) {
        return (Executive) sessionFactory.getCurrentSession().get(Executive.class, id);
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
    @SuppressWarnings("unchecked")
    public List<Producer> getAllProducers() {
        return sessionFactory.getCurrentSession().getNamedQuery("producer.all").list();
    }

    @Override
    public Producer findProducerById(Long id) {
        return (Producer) sessionFactory.getCurrentSession().get(Producer.class, id);
    }

    @Override
    public void addGraphicsCard(GraphicsCard gpu) {
        GraphicsCardInfo gpuInfo = gpu.getGraphicsCardInfo();
        gpu.setId(null);
        gpuInfo.setId(null);
        sessionFactory.getCurrentSession().persist(gpuInfo);
        sessionFactory.getCurrentSession().persist(gpu);
}

    @Override
    public void deleteGraphicsCard(GraphicsCard gpu) {
        gpu = (GraphicsCard) sessionFactory.getCurrentSession().get(GraphicsCard.class, gpu.getId());
        GraphicsCardInfo gpuInfo = (GraphicsCardInfo) sessionFactory.getCurrentSession().get(GraphicsCardInfo.class, gpu.getGraphicsCardInfo().getId());

        sessionFactory.getCurrentSession().delete(gpu);
        sessionFactory.getCurrentSession().delete(gpuInfo);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<GraphicsCard> getAvailableGraphicsCards() {
        return sessionFactory.getCurrentSession().getNamedQuery("graphicsCard.available").list();
    }

    @Override
    public List<GraphicsCard> getAvailableGraphicsCardsByProducer(Producer producer) {
        List<GraphicsCard> availableGpus = getAvailableGraphicsCards();
        List<GraphicsCard> resultSet = new ArrayList<GraphicsCard>();
        for (GraphicsCard gpu: availableGpus) {
            if(gpu.getProducer()==producer)
                resultSet.add(gpu);
        }
        return resultSet;
    }

    @Override
    public GraphicsCard findGraphicsCardByModel(String model) {
        return (GraphicsCard) sessionFactory.getCurrentSession().getNamedQuery("graphicsCard.byModel").setString("model", model).uniqueResult();
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
        for (GraphicsCard gpu : ownedGpus) {
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

    @Override
    public void sellGraphicsCard( Long gpuId, Long clientId) {
        Client client = findClientById(clientId);
        GraphicsCard gpu = findGraphicsCardById(gpuId);
        gpu.setSold(true);
        client.getGraphicsCards().add(gpu);
    }

    @Override
    public void sellGraphicsCard(GraphicsCard gpu, Client client) {
        sellGraphicsCard(gpu.getId(), client.getId());
    }

    @Override
    public void takeBackGraphicsCard(GraphicsCard gpu, Client client) {
        client = (Client) sessionFactory.getCurrentSession().get(Client.class, client.getId());
        gpu = (GraphicsCard) sessionFactory.getCurrentSession().get(GraphicsCard.class, gpu.getId());

        GraphicsCard toRemove = null;

        for(GraphicsCard graphicsCard: client.getGraphicsCards()){
            if(graphicsCard.getId() == (gpu.getId())){
                toRemove = graphicsCard;
                break;
            }
        }
        if(toRemove != null)
            client.getGraphicsCards().remove(toRemove);
        gpu.setSold(false);
    }

    @Override
    public void takeBackGraphicsCard(Long gpuId, Long clientId) {
        takeBackGraphicsCard(findGraphicsCardById(gpuId), findClientById(clientId));
    }


}
