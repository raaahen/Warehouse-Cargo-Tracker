package ru.stepanovgzh.wct.storingms.data.input;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class MoveCargoInput 
{
    @NotNull
    UUID id;

    @NotBlank
    @Size(min = 1, max = 50)
    String zone;

    @NotBlank
    @Size(min = 1, max = 50)
    String cell;
}
