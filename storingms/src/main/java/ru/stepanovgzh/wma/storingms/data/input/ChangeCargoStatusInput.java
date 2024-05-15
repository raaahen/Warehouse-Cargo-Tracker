package ru.stepanovgzh.wma.storingms.data.input;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wma.storingms.data.value.CargoStatus;

@Value
public class ChangeCargoStatusInput 
{
    UUID id;
    CargoStatus status;
}
