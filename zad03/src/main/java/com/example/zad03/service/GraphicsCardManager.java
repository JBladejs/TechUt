package com.example.zad03.service;

import com.example.zad03.domain.GraphicsCard;

import java.util.List;

public interface GraphicsCardManager {
    int addGraphicsCard(GraphicsCard gpu);
    List<GraphicsCard> getAllGraphicsCards();
    void addAllGraphicsCards(List<GraphicsCard> graphicsCards);
}
