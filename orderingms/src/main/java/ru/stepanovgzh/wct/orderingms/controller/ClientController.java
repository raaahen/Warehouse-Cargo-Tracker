package ru.stepanovgzh.wct.orderingms.controller;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.stepanovgzh.wct.orderingms.data.entity.*;
import ru.stepanovgzh.wct.orderingms.data.repository.*;

import java.util.*;

@RestController
@RequestMapping("/cargopicking/clients")
@RequiredArgsConstructor
public class ClientController
{
    private final ClientRepository clientRepository;

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody String name)
    {
        Client client = new Client(UUID.randomUUID(), name);
        clientRepository.save(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id)
    {
        if (clientRepository.existsById(id))
        {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients()
    {
        List<Client> clients = clientRepository.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}

