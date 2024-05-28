package ru.stepanovgzh.wct.storingms.data.input;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.stepanovgzh.wct.storingms.data.value.CargoStatus;

@Value
public class ChangeCargoStatusInput 
{
    @NotNull
    UUID id;

    @NotNull
    CargoStatus status;
}
