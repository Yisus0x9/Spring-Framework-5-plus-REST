package org.yisus.spring.aop.example.services;

import org.springframework.stereotype.Service;
import org.yisus.spring.aop.example.anotations.Audit;
import org.yisus.spring.aop.example.models.Client;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private List<Client> clients=new ArrayList<>();


    @Audit
    public Client createClient(String name, String email) {
        Client client = new Client(clients.size(), name, email);
        clients.add(client);
        return client;
    }

    @Audit
    public Client updateClientEmail(Integer id, String newEmail) {
        Client clientFind = clients.stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);
        if (clientFind != null) {
            clientFind.setEmail(newEmail);
        }
        return clientFind;
    }

    @Audit
    public String deleteClient(Integer id) {
        Client clientFind = clients.stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);
        if (clientFind != null) {
            clients.remove(clientFind);
            return "Client with id " + id + " deleted successfully";
        }
        return "Client with id " + id + " not found";
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

}
