package ru.stepanovgzh.wct.storingms.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import ru.stepanovgzh.wct.storingms.cqrs.event.CargoCreatedEvent;
import ru.stepanovgzh.wct.storingms.cqrs.event.CargoUpdatedEvent;
import ru.stepanovgzh.wct.storingms.data.model.Cargo;
import ru.stepanovgzh.wct.storingms.data.view.CargoView;

@Mapper(componentModel = "spring", 
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CargoMapper
{
    Cargo map(CargoCreatedEvent cargoCreatedEvent);
    Cargo map(CargoUpdatedEvent cargoUpdatedEvent);
    Cargo merge(Cargo cargoFromEvent, @MappingTarget Cargo cargoFromDb);
    CargoView map(Cargo cargo);
}
