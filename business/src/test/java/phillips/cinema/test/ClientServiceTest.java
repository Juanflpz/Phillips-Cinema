package phillips.cinema.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import phillips.cinema.entities.Client;
import phillips.cinema.services.ClientService;
import phillips.cinema.services.EmailService;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Transactional
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    @Test
    @Sql("classpath:dataset.sql")
    public void registerClientTest() {
        Client client = new Client("1234567890", "Juan Lopez", "juan.lopezc@example.com", "password123");
        try {
            Client client1 = clientService.register(client);
            Assertions.assertNotNull(client1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void updateClientTest() {
        try {
            Client client = clientService.getClient("1234567890");
            client.setEmail("felipe.lopez@example.com");
            Client client1 = clientService.updateClient(client);
            Assertions.assertEquals("felipe.lopez@example.com", client1.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void deleteClientTest() {
        try {
            clientService.deleteClient("1234567890");
        } catch (Exception e) {
            //verify if the operation doesnt fail
            Assertions.assertTrue(false);
        }

        try {
            Client client = clientService.getClient("1234567890");
        } catch (Exception e) {
            //verify if the operation fails
            Assertions.assertTrue(true);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void getClientTest() {
        try {
            Client client = clientService.getClient("1234567890");
            Assertions.assertNotNull(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void getAllClientsTest() {
        try {
            List<Client> clients = clientService.getAllClients();
            clients.forEach(System.out::println);
            Assertions.assertNotNull(clients);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void loginClientTest() {
        try {
            Client client = clientService.login("juan.lopez@example.com", "password123");
            Assertions.assertNotNull(client);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void sendEmailTest() {
        emailService.sendEmail("isamejvilla@gmail.com", "TE AMO", "PICOSSSSSSSSSSSSSSSSSSSSSS");
    }
}
