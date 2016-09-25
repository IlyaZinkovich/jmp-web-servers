package com.epam.jmp.spring.repository;

import com.epam.jmp.spring.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
