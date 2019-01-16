package com.example.shdemo.service;

import com.example.shdemo.domain.Client;

import java.util.List;

public interface SellingManager {

    void addClient(Client client);
    List<Client> getAllClients();
    void deleteClient(Client client);
    Client findClientByLogin(String login);

}
