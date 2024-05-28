package ru.stepanovgzh.wct.storingms.data.input;

import java.util.UUID;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class UpdateCargoInput 
{
    @NotNull
    UUID id;

    @Min(1)
    int qty;
}
