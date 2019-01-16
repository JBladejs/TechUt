package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.GraphicsCard;
import com.example.shdemo.domain.Producer;

import java.util.List;

public interface SellingManager {
    void addGraphicsCard(GraphicsCard gpu);
    void deleteGraphicsCard(GraphicsCard gpu);
    List<GraphicsCard> getAvailableGraphicsCards();
    GraphicsCard findGraphicsCardByModel(String model);
    GraphicsCard findGraphicsCardById(Long id);

    void addClient(Client client);
    void deleteClient(Client client);
    List<Client> getAllClients();
    Client findClientByLogin(String login);
    Client findClientById(Long id);

    void sellGraphicsCard(Long gpuId, Long clientId);
    void takeBackGraphicsCard(GraphicsCard gpu, Client client);

}
