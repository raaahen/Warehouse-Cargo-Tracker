package ru.stepanovgzh.wct.pickingms.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.stepanovgzh.wct.pickingms.data.entity.*;
import ru.stepanovgzh.wct.pickingms.data.repository.*;

import java.util.*;

@RestController
@RequestMapping("/cargopicking/transporters")
@RequiredArgsConstructor
public class TransporterController
{
    private final TransporterRepository transporterRepository;

    @PostMapping
    public ResponseEntity<Transporter> addTransporter(@RequestBody String name)
    {
        Transporter transporter = new Transporter(UUID.randomUUID(), name);
        transporterRepository.save(transporter);
        return new ResponseEntity<>(transporter, HttpStatus.CREATED);
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
