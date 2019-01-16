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

    @Test
    public void distributionManagerTest() {
        assertNotNull(sellingManager);
    }

    @Test
    public void addClientTest(){
        List<Client> retrievedClients = sellingManager.getAllClients();

        //to change it in the future
        for(Client client : retrievedClients){
            if (client.getLogin().equals(LOGIN_1))
                sellingManager.deleteClient(client);
        }

        Client client = new Client();
        client.setLastName(LAST_NAME_1);
        client.setLogin(LOGIN_1);

        sellingManager.addClient(client);

        Client retrievedClient = sellingManager.findClientByLogin(LOGIN_1);

        assertEquals("unknown", retrievedClient.getFirstName());
        assertEquals(LAST_NAME_1, retrievedClient.getLastName());
        assertEquals(LOGIN_1, retrievedClient.getLogin());
    }


}
