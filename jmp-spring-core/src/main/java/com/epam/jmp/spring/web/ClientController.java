package com.epam.jmp.spring.web;

import com.epam.jmp.spring.exceptions.ClientNotFoundException;
import com.epam.jmp.spring.model.Client;
import com.epam.jmp.spring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clients", headers = "Accept=application/json;charset=utf-8")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> getClientList() {
        return clientService.clientAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long createClient(@RequestBody Client client) {
        clientService.createOrUpdateClient(client);
        return client.getId();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updateClient(@RequestBody Client client, @PathVariable(value = "id") Long id) {
        client.setId(id);
        clientService.createOrUpdateClient(client);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Client getClientById(@PathVariable(value = "id") Long id) {
        Client client = clientService.getClientById(id);
        if (client == null) throw new ClientNotFoundException(id);
        return client;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void removeClientById(@PathVariable(value = "id") Long id) {
        clientService.removeClientById(id);
    }
}
