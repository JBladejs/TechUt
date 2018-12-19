package com.bladejs.zad04.service;

import com.bladejs.zad04.domain.CEO;
import com.bladejs.zad04.domain.GraphicsCard;
import com.bladejs.zad04.domain.Producer;

import java.util.List;

public interface SellingManager {

    void addCeo(CEO ceo);
    List<CEO> getAllCEOs();
    CEO findCEObyProducer(Producer producer);

    void addNewGraphicsCard(GraphicsCard gpu);
    List<GraphicsCard> getAvailableCars();
    void sellGraphicsCard();
}
