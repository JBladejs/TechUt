package com.example.zad03.service;

import com.example.zad03.domain.GraphicsCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicsCardManagerJDBC implements GraphicsCardManager{

    private final String URL = "jdbc:hsqldb:hsql://localhost/workdb";

    private Connection connection;

    private Statement statement;
    private String createTableGraphicsCard = "CREATE TABLE GraphicsCard(id bigint GENERATED BY DEFAULT AS IDENTITY, model int UNIQUE, producer varchar(32), tflops double, price float)";

    private PreparedStatement addGraphicsCardStmt;
    private PreparedStatement deleteAllGraphicsCardsStmt;
    private PreparedStatement getAllGraphicsCardsStmt;


    public GraphicsCardManagerJDBC() throws SQLException {
        connection = DriverManager.getConnection(URL);
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null,null,null,null);
        boolean tableExists = false;
        while(rs.next()){
            if ("GraphicsCard".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                tableExists = true;
                break;
            }
        }

        if(!tableExists)
            statement.executeUpdate(createTableGraphicsCard);

        addGraphicsCardStmt = connection.prepareStatement("INSERT INTO GraphicsCard(model, producer, tflops, price) VALUES (?, ?, ?, ?)");
        deleteAllGraphicsCardsStmt = connection.prepareStatement("DELETE FROM GraphicsCard");
        getAllGraphicsCardsStmt = connection.prepareStatement("SELECT id, model, producer, tflops, price FROM GraphicsCard");
    }

    public Connection getConnection() {
        return connection;
    }

    public void clearGraphicsCards() throws SQLException {
        deleteAllGraphicsCardsStmt.executeUpdate();
    }

    @Override
    public int addGraphicsCard(GraphicsCard gpu){
        try {
            addGraphicsCardStmt.setString(1, Integer.toString(gpu.getModel()));
            addGraphicsCardStmt.setString(2, gpu.getProducer());
            addGraphicsCardStmt.setString(3, Double.toString(gpu.getTflops()));
            addGraphicsCardStmt.setString(4, Float.toString(gpu.getPrice()));

            return addGraphicsCardStmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<GraphicsCard> getAllGraphicsCards(){
        List<GraphicsCard> graphicsCards = new ArrayList<>();
        try {
            ResultSet rs = getAllGraphicsCardsStmt.executeQuery();

            while (rs.next()) {
                GraphicsCard gpu = new GraphicsCard();

                gpu.setId(rs.getInt("id"));
                gpu.setModel(rs.getInt("model"));
                gpu.setProducer(rs.getString("producer"));
                gpu.setTflops(rs.getDouble("tflops"));
                gpu.setPrice(rs.getFloat("price"));

                graphicsCards.add(gpu);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return graphicsCards;
    }

    @Override
    public void addAllGraphicsCards(List<GraphicsCard> persons) {

        try {
            connection.setAutoCommit(false);
            for (GraphicsCard gpu : persons) {
                addGraphicsCardStmt.setString(1, Integer.toString(gpu.getModel()));
                addGraphicsCardStmt.setString(2, gpu.getProducer());
                addGraphicsCardStmt.setString(3, Double.toString(gpu.getTflops()));
                addGraphicsCardStmt.setString(4, Float.toString(gpu.getPrice()));

                addGraphicsCardStmt.executeUpdate();
            }
            connection.commit();

        } catch (SQLException exception) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
