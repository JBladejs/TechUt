package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.GraphicsCard;

import java.util.List;

public interface SellingManager {
    void addClient(Client client);
    void deleteClient(Client client);
    List<Client> getAllClients();
    Client findClientByLogin(String login);
    Client findClientById(Long id);

}
