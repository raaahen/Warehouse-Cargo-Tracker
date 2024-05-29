package ru.stepanovgzh.wct.receivingms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wct.receivingms.data.entity.Transporter;
import ru.stepanovgzh.wct.receivingms.data.repository.TransporterRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cargoreceiving/transporters")
@RequiredArgsConstructor
public class TransporterController 
{
    private final TransporterRepository transporterRepository;

    @PostMapping
    public ResponseEntity<Transporter> addTransporter(@RequestBody Transporter transporter) 
    {
        Transporter savedTransporter = transporterRepository.save(transporter);
        return new ResponseEntity<>(savedTransporter, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransporter(@PathVariable UUID id) 
    {
        if (transporterRepository.existsById(id)) 
        {
            transporterRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } 
        else 
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Transporter>> getAllTransporters() 
    {
        List<Transporter> transporters = transporterRepository.findAll();
        return new ResponseEntity<>(transporters, HttpStatus.OK);
    }
}
