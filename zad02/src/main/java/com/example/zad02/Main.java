package com.example.zad02;

import com.example.zad02.domain.GraphicsCard;
import com.example.zad02.service.GraphicsCardService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            GraphicsCardService gpuService = new GraphicsCardService();
            gpuService.clearGraphicsCards();
            GraphicsCard gpu1 = new GraphicsCard(1080, "Nvidia", 9.0, 2714.74f);
            GraphicsCard gpu2 = new GraphicsCard(1070, "Nvidia", 6.5, 1949.00f);
            gpuService.addGraphicsCard(gpu1);
            gpuService.addGraphicsCard(gpu2);
            List<GraphicsCard> graphicsCards = gpuService.getAllGraphicsCards();
            for (GraphicsCard gpu: graphicsCards) {
                System.out.println(gpu.getId()+" "+gpu.getModel()+" "+gpu.getProducer()+" "+gpu.getTflops()+" "+gpu.getPrice());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
