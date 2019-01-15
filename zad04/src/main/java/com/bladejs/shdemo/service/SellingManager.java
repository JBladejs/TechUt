package com.bladejs.shdemo.service;

import com.bladejs.shdemo.domain.CEO;
import com.bladejs.shdemo.domain.Client;
import com.bladejs.shdemo.domain.GraphicsCard;
import com.bladejs.shdemo.domain.Producer;

import java.util.List;

public interface SellingManager {

    void addCeo(CEO ceo);
    List<CEO> getAllCEOs();
    CEO findCEOByProducer(Producer producer);

    void addNewGraphicsCard(GraphicsCard gpu);
    List<GraphicsCard> getAvailableGraphicsCards();
    void sellGraphicsCard(Client client, GraphicsCard gpu);
    GraphicsCard findGraphicsCardById(Long id);

    List<GraphicsCard> getOwnnedGraphicsCards(Client client);
    void sellGraphicsCard(Long clientId, Long graphicsCardID);


}
