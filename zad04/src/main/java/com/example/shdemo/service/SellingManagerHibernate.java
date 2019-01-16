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
        Client client = (Client) sessionFactory.getCurrentSession().get(Client.class, clientId);
        GraphicsCard gpu = (GraphicsCard) sessionFactory.getCurrentSession().get(GraphicsCard.class, gpuId);
        gpu.setSold(true);
        client.getGraphicsCards().add(gpu);
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


}
