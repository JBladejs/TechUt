package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.Executive;
import com.example.shdemo.domain.GraphicsCard;
import com.example.shdemo.domain.Producer;

import java.util.List;

public interface SellingManager {
    void addExecutive(Executive executive);
    void deleteExecutive(Executive executive);
    List<Executive> getAllExecutives();
    Executive findExecutiveById(Long id);

    void addProducer(Producer producer);
    void deleteProducer(Producer producer);
    List<Producer> getAllProducers();
    Producer findProducerById(Long id);

    void addGraphicsCard(GraphicsCard gpu);
    void deleteGraphicsCard(GraphicsCard gpu);
    List<GraphicsCard> getAvailableGraphicsCards();
    List<GraphicsCard> getAvailableGraphicsCardsByProducer(Producer producer);
    GraphicsCard findGraphicsCardByModel(String model);
    GraphicsCard findGraphicsCardById(Long id);

    void addClient(Client client);
    void deleteClient(Client client);
    List<Client> getAllClients();
    Client findClientByLogin(String login);
    Client findClientById(Long id);

    void sellGraphicsCard(Long gpuId, Long clientId);
    void sellGraphicsCard(GraphicsCard gpu, Client client);
    void takeBackGraphicsCard(GraphicsCard gpu, Client client);
    void takeBackGraphicsCard(Long gpuId, Long clientId);


}
