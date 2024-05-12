package ru.stepanovgzh.wma.storingms.data;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ru.stepanovgzh.wma.storingms.cqrs.event.CargoCreatedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoUpdatedEvent;
import ru.stepanovgzh.wma.storingms.data.model.Cargo;

@Mapper
public interface EntityMapper 
{
    Cargo map(CargoCreatedEvent cargoCreatedEvent);
    Cargo map(CargoUpdatedEvent cargoUpdatedEvent);
    Cargo merge(Cargo cargoFromEvent, @MappingTarget Cargo cargoFromDb);
}
