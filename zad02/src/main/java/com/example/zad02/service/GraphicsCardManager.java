package com.example.zad02.service;

import com.example.zad02.domain.GraphicsCard;

import java.util.List;

public interface GraphicsCardManager {
    int addGraphicsCard(GraphicsCard gpu);
    List<GraphicsCard> getAllGraphicsCards();
    void addAllGraphicsCards(List<GraphicsCard> graphicsCards);
}
