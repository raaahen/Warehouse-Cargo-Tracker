package ru.stepanovgzh.wma.storingms.projection;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoCreatedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoDeletedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoMovedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoStatusChangedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoUpdatedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.query.AllCargoQuery;
import ru.stepanovgzh.wma.storingms.data.mapper.CargoMapper;
import ru.stepanovgzh.wma.storingms.data.model.Cargo;
import ru.stepanovgzh.wma.storingms.data.repository.CargoRepository;
import ru.stepanovgzh.wma.storingms.data.view.CargoView;

@Service
@RequiredArgsConstructor
public class CargoProjection 
{
    private final CargoMapper cargoMapper;
    private final CargoRepository cargoRepository;

    @EventHandler
    public void on(CargoCreatedEvent cargoCreatedEvent)
    { 
        cargoRepository.save(cargoMapper.map(cargoCreatedEvent));
    }

    @EventHandler
    public void on(CargoMovedEvent cargoMovedEvent)
    {
        Cargo cargo = cargoRepository.findById(cargoMovedEvent.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                    "Cargo not found, id = " + cargoMovedEvent.getId()));
        cargo.setLocation(cargoMovedEvent.getLocation());
        cargoRepository.save(cargo);
    }

    @EventHandler
    public void on(CargoStatusChangedEvent cargoStatusChangedEvent)
    {
        Cargo cargo = cargoRepository.findById(cargoStatusChangedEvent.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                    "Cargo not found, id = " + cargoStatusChangedEvent.getId()));
        cargo.setStatus(cargoStatusChangedEvent.getStatus());
        cargoRepository.save(cargo);
    }

    @EventHandler
    public void on(CargoUpdatedEvent cargoUpdatedEvent)
    {
        Cargo cargoFromDb = cargoRepository.findById(cargoUpdatedEvent.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                    "Cargo not found, id = " + cargoUpdatedEvent.getId()));
        Cargo cargoFromEvent = cargoMapper.map(cargoUpdatedEvent);
        cargoRepository.save(cargoMapper.merge(cargoFromEvent, cargoFromDb));
    }

    @EventHandler
    public void on(CargoDeletedEvent cargoDeletedEvent) 
    {
        Cargo cargo = cargoRepository.findById(cargoDeletedEvent.getId())
            .orElseThrow(() -> new EntityNotFoundException(
                    "Cargo not found, id = " + cargoDeletedEvent.getId()));
        cargoRepository.delete(cargo);
    }

    @QueryHandler
    public List<CargoView> handleCargoList(AllCargoQuery allCargoQuery)
    {
        return cargoRepository.findAll().stream()
            .map(cargoMapper::map)
            .collect(Collectors.toList());
    }
}
