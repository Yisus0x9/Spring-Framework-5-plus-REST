package org.yisus.spring.aop.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.yisus.spring.aop.example.services.ClientService;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    public void createClient(String name, String email) {
        log.info("Se ha creado el cliente con exito: {}",clientService.createClient(name,email));
    }

    public void updateClient(Integer id, String email) {
        log.info("Se ha actualizado el cliente con exito: {}",clientService.updateClientEmail(id,email));
    }
    public void deleteClient(Integer id) {
        log.info("{}",clientService.deleteClient(id));
    }

    public void listClients() {
        log.info("Lista de clientes: {}", clientService.getClients().stream().toList());
    }
}
