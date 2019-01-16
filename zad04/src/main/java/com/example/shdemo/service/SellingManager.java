package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.GraphicsCard;
import com.example.shdemo.domain.Producer;

import java.util.List;

public interface SellingManager {
    void addGraphicsCard(GraphicsCard gpu);
    void deleteGraphicsCard(GraphicsCard gpu);
    List<GraphicsCard> getAllGraphicsCards();
    List<GraphicsCard> getGraphicsCardByProducer(Producer producer);
    GraphicsCard findGraphicsCardById(Long id);

    void addClient(Client client);
    void deleteClient(Client client);
    List<Client> getAllClients();
    Client findClientByLogin(String login);
    Client findClientById(Long id);

}
