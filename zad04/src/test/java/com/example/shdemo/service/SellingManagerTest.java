package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import com.example.shdemo.domain.GraphicsCard;
import com.example.shdemo.domain.GraphicsCardInfo;
import com.example.shdemo.domain.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
public class SellingManagerTest {

    @Autowired
    SellingManager sellingManager;

    private final String LOGIN_1 = "Tester";
    private final String LAST_NAME_1 = "Testowany";

    private final String LOGIN_2 = "MyLogin";

    private final String MODEL_1 = "GTX 750 Ti";
    private final double T_FLOPS_1 = 1.728;

    private final String MODEL_2 = "RTX 2080 Ti";
    private final String MODEL_3 = "Radeon Vega 56";

    @Test
    public void addGraphicsCardTest(){
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_1);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);

        gpu = new GraphicsCard(MODEL_1, T_FLOPS_1);
        sellingManager.addGraphicsCard(gpu);

        GraphicsCard retrievedGpu = sellingManager.findGraphicsCardByModel(MODEL_1);

        assertEquals(MODEL_1, retrievedGpu.getGraphicsCardInfo().getModel());
        assertEquals(retrievedGpu.getGraphicsCardInfo().getTflops(), T_FLOPS_1, 0.0);
        assertFalse(retrievedGpu.isSold());
    }

    @Test
    public void deleteGraphicsCardTest(){
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_1);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);

        gpu = new GraphicsCard(MODEL_2);
        sellingManager.addGraphicsCard(gpu);

        assertEquals(MODEL_2, sellingManager.findGraphicsCardById(gpu.getId()).getGraphicsCardInfo().getModel());

        sellingManager.deleteGraphicsCard(gpu);
        assertNull(sellingManager.findClientById(gpu.getId()));
    }

    @Test
    public void getAvailableGraphicsCardsTest(){
        List<GraphicsCard> retrievedGpus = sellingManager.getAvailableGraphicsCards();

        for(GraphicsCard gpu : retrievedGpus){
            sellingManager.deleteGraphicsCard(gpu);
        }
        assertEquals(0, sellingManager.getAvailableGraphicsCards().size());

        GraphicsCard g1 = new GraphicsCard(MODEL_1);
        GraphicsCard g2 = new GraphicsCard(MODEL_2);
        GraphicsCard g3 = new GraphicsCard(MODEL_3);
        g2.setSold(true);

        sellingManager.addGraphicsCard(g1);
        sellingManager.addGraphicsCard(g2);
        sellingManager.addGraphicsCard(g3);

        retrievedGpus = sellingManager.getAvailableGraphicsCards();
        assertEquals(2, retrievedGpus.size());
        assertEquals(MODEL_1, retrievedGpus.get(0).getGraphicsCardInfo().getModel());
        assertEquals(MODEL_3, retrievedGpus.get(1).getGraphicsCardInfo().getModel());
    }

    @Test
    public void findGraphicsCardByModelTest(){
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_1);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);

        gpu = new GraphicsCard(MODEL_1);

        sellingManager.addGraphicsCard(gpu);
        assertEquals(gpu, sellingManager.findGraphicsCardByModel(MODEL_1));
    }

    @Test
    public void findGraphicsCardByIdTest(){
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_1);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);
        gpu = new GraphicsCard(MODEL_1);

        sellingManager.addGraphicsCard(gpu);
        assertEquals(gpu, sellingManager.findGraphicsCardById(gpu.getId()));
    }

    @Test
    public void addClientTest(){
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client();
        client.setLastName(LAST_NAME_1);
        client.setLogin(LOGIN_1);

        sellingManager.addClient(client);

        Client retrievedClient = sellingManager.findClientByLogin(LOGIN_1);

        assertEquals("unknown", retrievedClient.getFirstName());
        assertEquals(LAST_NAME_1, retrievedClient.getLastName());
        assertEquals(LOGIN_1, retrievedClient.getLogin());
    }

    @Test
    public void deleteClientTest() {
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_1);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client();
        client.setLogin(LOGIN_1);
        gpu = new GraphicsCard(MODEL_3);
        gpu.setSold(true);
        client.getGraphicsCards().add(gpu);

        sellingManager.addClient(client);
        sellingManager.addGraphicsCard(gpu);
        assertEquals(LOGIN_1, sellingManager.findClientById(client.getId()).getLogin());
        assertTrue(sellingManager.findGraphicsCardById(gpu.getId()).isSold());

        sellingManager.deleteClient(client);
        assertNull(sellingManager.findClientById(client.getId()));
        assertFalse(sellingManager.findGraphicsCardById(gpu.getId()).isSold());
    }

    @Test
    public void getAllClientsTest(){
        List<Client> retrievedClients = sellingManager.getAllClients();

        for(Client client : retrievedClients){
            sellingManager.deleteClient(client);
        }
        assertEquals(0, sellingManager.getAllClients().size());

        Client c1 = new Client();
        c1.setLogin(LOGIN_1);
        c1.setLastName(LAST_NAME_1);
        Client c2 = new Client();
        c2.setLogin(LOGIN_2);

        sellingManager.addClient(c1);
        sellingManager.addClient(c2);

        retrievedClients = sellingManager.getAllClients();
        assertEquals(2, retrievedClients.size());
        assertEquals(LOGIN_1, retrievedClients.get(0).getLogin());
        assertEquals(LOGIN_2, retrievedClients.get(1).getLogin());
        assertEquals(LAST_NAME_1, retrievedClients.get(0).getLastName());
    }

    @Test
    public void findClientByLoginTest(){
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client();
        client.setLogin(LOGIN_1);

        sellingManager.addClient(client);
        assertEquals(client, sellingManager.findClientByLogin(LOGIN_1));
    }

    @Test
    public void findClientByIdTest(){
        Client client = sellingManager.findClientByLogin(LOGIN_2);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client();
        client.setLogin(LOGIN_2);

        sellingManager.addClient(client);
        assertEquals(client, sellingManager.findClientById(client.getId()));
    }

    @Test
    public void sellGraphicsCardTest(){

    }
}
