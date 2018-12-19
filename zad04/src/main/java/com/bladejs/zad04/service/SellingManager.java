package com.bladejs.zad04.service;

import com.bladejs.zad04.domain.CEO;
import com.bladejs.zad04.domain.Client;
import com.bladejs.zad04.domain.GraphicsCard;
import com.bladejs.zad04.domain.Producer;

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
