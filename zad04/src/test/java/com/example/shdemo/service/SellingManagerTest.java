package com.example.shdemo.service;

import com.example.shdemo.domain.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

    @Autowired
    SellingManager sellingManager;

    private final String LOGIN_1 = "Tester";
    private final String LAST_NAME_1 = "Testowany";

    private final String LOGIN_2 = "MyLogin";
    private final String LAST_NAME_2 = "Kowalski";


    @Test
    public void distributionManagerTest() {
        assertNotNull(sellingManager);
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
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client();
        client.setLogin(LOGIN_1);

        sellingManager.addClient(client);
        assertEquals(LOGIN_1, sellingManager.findClientById(client.getId()).getLogin());

        sellingManager.deleteClient(client);
        assertNull(sellingManager.findClientById(client.getId()));
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
        c2.setLastName(LAST_NAME_2);

        sellingManager.addClient(c1);
        sellingManager.addClient(c2);

        retrievedClients = sellingManager.getAllClients();
        assertEquals(2, retrievedClients.size());
        assertEquals(LOGIN_1, retrievedClients.get(0).getLogin());
        assertEquals(LOGIN_2, retrievedClients.get(1).getLogin());
        assertEquals(LAST_NAME_1, retrievedClients.get(0).getLastName());
        assertEquals(LAST_NAME_2, retrievedClients.get(1).getLastName());
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
        Client client = sellingManager.findClientByLogin(LOGIN_1);
        if (client!=null)
            sellingManager.deleteClient(client);

        client = new Client();
        client.setLogin(LOGIN_1);

        sellingManager.addClient(client);
        assertEquals(client, sellingManager.findClientById(client.getId()));
    }
}
