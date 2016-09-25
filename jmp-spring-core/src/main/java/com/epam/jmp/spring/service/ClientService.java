package com.epam.jmp.spring.service;


import com.epam.jmp.spring.model.Client;

import java.util.List;

public interface ClientService {

    void createOrUpdateClient(Client client);
    List<Client> clientAll();
    Client getClientById(Long id);
    void removeClientById(Long id);
}
