package com.epam.jmp.spring.service.impl;

import com.epam.jmp.spring.model.Client;
import com.epam.jmp.spring.repository.ClientRepository;
import com.epam.jmp.spring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void createOrUpdateClient(Client client) {
        clientRepository.save(client);
    }

    @Override
    public List<Client> clientAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findOne(id);
    }

    @Override
    public void removeClientById(Long id) {
        clientRepository.delete(id);
    }
}
