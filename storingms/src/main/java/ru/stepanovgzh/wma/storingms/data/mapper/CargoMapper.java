package ru.stepanovgzh.wma.storingms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import ru.stepanovgzh.wma.storingms.cqrs.event.CargoCreatedEvent;
import ru.stepanovgzh.wma.storingms.cqrs.event.CargoUpdatedEvent;
import ru.stepanovgzh.wma.storingms.data.model.Cargo;
import ru.stepanovgzh.wma.storingms.data.view.CargoView;

@Mapper(componentModel = "spring")
public interface CargoMapper 
{
    Cargo map(CargoCreatedEvent cargoCreatedEvent);
    Cargo map(CargoUpdatedEvent cargoUpdatedEvent);
    Cargo merge(Cargo cargoFromEvent, @MappingTarget Cargo cargoFromDb);
    CargoView map(Cargo cargo);
}
