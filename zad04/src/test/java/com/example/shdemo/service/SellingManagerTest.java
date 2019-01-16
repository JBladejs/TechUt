package com.example.shdemo.service;

import com.example.shdemo.domain.*;
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
    private final String LOGIN_2 = "MyLogin";

    private final String FIRST_NAME_1 = "Alan";
    private final String FIRST_NAME_2 = "Joseph";

    private final String LAST_NAME_1 = "Testowany";
    private final String LAST_NAME_2 = "Kowalski";

    private final String MODEL_1 = "GTX 750 Ti";
    private final String MODEL_2 = "RTX 2080 Ti";
    private final String MODEL_3 = "Radeon Vega 56";

    private final String NAME_1 = "Nvidia";
    private final String NAME_2 = "AMD";

    private final String COUNTRY_1 = "United States";

    @Test
    public void addProducerTest(){
        List<Producer> receivedProducers = sellingManager.getAllProducers();
        for (Producer producer: receivedProducers) {
            if(producer.getName().equals(NAME_1))
                sellingManager.deleteProducer(producer);
        }

        Executive e1 = new Executive(FIRST_NAME_1, LAST_NAME_1);
        Executive e2 = new Executive(FIRST_NAME_2, LAST_NAME_2);

        Producer producer = new Producer(NAME_1, COUNTRY_1);
        producer.getExecutives().add(e1);
        producer.getExecutives().add(e2);

        sellingManager.addProducer(producer);

        Producer receivedProducer = sellingManager.findProducerById(producer.getId());
        assertEquals(NAME_1, receivedProducer.getName());
        assertEquals(COUNTRY_1, receivedProducer.getCountry());

        assertEquals(2, receivedProducer.getExecutives().size());
        assertEquals(FIRST_NAME_1, receivedProducer.getExecutives().get(0).getFirstName());
        assertEquals(LAST_NAME_1, receivedProducer.getExecutives().get(0).getLastName());
        assertEquals(FIRST_NAME_2, receivedProducer.getExecutives().get(1).getFirstName());
        assertEquals(LAST_NAME_2, receivedProducer.getExecutives().get(1).getLastName());
    }

    @Test
    public void deleteProducerTest(){
        List<Producer> receivedProducers = sellingManager.getAllProducers();
        for (Producer producer: receivedProducers) {
            if(producer.getName().equals(NAME_2))
                sellingManager.deleteProducer(producer);
        }

        Producer producer = new Producer(NAME_2, COUNTRY_1);
        sellingManager.addProducer(producer);

        assertEquals(NAME_2, sellingManager.findProducerById(producer.getId()).getName());

        sellingManager.deleteProducer(producer);
        assertNull(sellingManager.findProducerById(producer.getId()));
    }

    @Test
    public void getAllProducersTest(){
        List<Producer> receivedProducers = sellingManager.getAllProducers();
        for (Producer producer: receivedProducers) {
            sellingManager.deleteProducer(producer);
        }
        receivedProducers = sellingManager.getAllProducers();

        assertEquals(0, receivedProducers.size());

        Executive e1 = new Executive(FIRST_NAME_1, LAST_NAME_1);
        Executive e2 = new Executive(FIRST_NAME_2, LAST_NAME_2);

        Producer p1 = new Producer(NAME_1);
        Producer p2 = new Producer(NAME_2);

        p1.getExecutives().add(e1);
        p1.getExecutives().add(e2);

        p2.getExecutives().add(e2);

        sellingManager.addProducer(p1);
        sellingManager.addProducer(p2);

        receivedProducers = sellingManager.getAllProducers();

        assertEquals(2, receivedProducers.size());
        assertEquals(NAME_1, receivedProducers.get(0).getName());
        assertEquals(NAME_2, receivedProducers.get(1).getName());
        assertEquals(2, receivedProducers.get(0).getExecutives().size());
        assertEquals(1, receivedProducers.get(1).getExecutives().size());
    }

    @Test
    public void findProducerByIdTest(){
        List<Producer> receivedProducers = sellingManager.getAllProducers();
        for (Producer producer: receivedProducers) {
            if(producer.getName().equals(NAME_1))
                sellingManager.deleteProducer(producer);
        }

        Producer producer = new Producer(NAME_1);

        sellingManager.addProducer(producer);
        assertEquals(producer, sellingManager.findProducerById(producer.getId()));
    }

    @Test
    public void addGraphicsCardTest(){
        double t_flops = 1.728;
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_1);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);

        gpu = new GraphicsCard(MODEL_1, t_flops);
        sellingManager.addGraphicsCard(gpu);

        GraphicsCard retrievedGpu = sellingManager.findGraphicsCardByModel(MODEL_1);

        assertEquals(MODEL_1, retrievedGpu.getGraphicsCardInfo().getModel());
        assertEquals(retrievedGpu.getGraphicsCardInfo().getTflops(), t_flops, 0.0);
        assertFalse(retrievedGpu.isSold());
    }

    @Test
    public void deleteGraphicsCardTest(){
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_2);
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

        client = new Client(LOGIN_1);
        client.setFirstName(FIRST_NAME_1);
        client.setLastName(LAST_NAME_1);

        sellingManager.addClient(client);

        Client retrievedClient = sellingManager.findClientByLogin(LOGIN_1);

        assertEquals(LAST_NAME_1, retrievedClient.getLastName());
        assertEquals(FIRST_NAME_1, retrievedClient.getFirstName());
        assertEquals(LOGIN_1, retrievedClient.getLogin());
    }

    @Test
    public void deleteClientTest() {
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_3);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client(LOGIN_1);
        gpu = new GraphicsCard(MODEL_3);
        gpu.setSold(true);
        client.getGraphicsCards().add(gpu);

        sellingManager.addGraphicsCard(gpu); 
        sellingManager.addClient(client);
        assertEquals(LOGIN_1, sellingManager.findClientById(client.getId()).getLogin());
        assertTrue(sellingManager.findGraphicsCardById(gpu.getId()).isSold());

        sellingManager.deleteClient(client);
        assertNull(sellingManager.findClientById(client.getId()));
        assertFalse(gpu.isSold());
    }

    @Test
    public void getAllClientsTest(){
        List<Client> retrievedClients = sellingManager.getAllClients();

        for(Client client : retrievedClients){
            sellingManager.deleteClient(client);
        }
        assertEquals(0, sellingManager.getAllClients().size());

        Client c1 = new Client(LOGIN_1);
        Client c2 = new Client(LOGIN_2);

        sellingManager.addClient(c1);
        sellingManager.addClient(c2);

        retrievedClients = sellingManager.getAllClients();
        assertEquals(2, retrievedClients.size());
        assertEquals(LOGIN_1, retrievedClients.get(0).getLogin());
        assertEquals(LOGIN_2, retrievedClients.get(1).getLogin());
    }

    @Test
    public void findClientByLoginTest(){
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client(LOGIN_1);

        sellingManager.addClient(client);
        assertEquals(client, sellingManager.findClientByLogin(LOGIN_1));
    }

    @Test
    public void findClientByIdTest(){
        Client client = sellingManager.findClientByLogin(LOGIN_2);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client(LOGIN_2);

        sellingManager.addClient(client);
        assertEquals(client, sellingManager.findClientById(client.getId()));
    }

    @Test
    public void sellAndTakeBackGraphicsCardTest(){
        GraphicsCard gpu = sellingManager.findGraphicsCardByModel(MODEL_1);
        if (gpu!=null)
            sellingManager.deleteGraphicsCard(gpu);
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        gpu = new GraphicsCard(MODEL_1);
        client = new Client(LOGIN_1);

        sellingManager.addGraphicsCard(gpu);
        sellingManager.addClient(client);

        sellingManager.sellGraphicsCard(gpu.getId(), client.getId());

        assertEquals(1, client.getGraphicsCards().size());
        assertEquals(gpu, sellingManager.findClientById(client.getId()).getGraphicsCards().get(0));
        assertTrue(sellingManager.findGraphicsCardById(gpu.getId()).isSold());

        sellingManager.takeBackGraphicsCard(gpu, client);

        assertEquals(0, client.getGraphicsCards().size());
        assertFalse(sellingManager.findGraphicsCardById(gpu.getId()).isSold());
    }
}
