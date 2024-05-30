package ru.stepanovgzh.wct.pickingms.data.input;

import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class DeletePickingOrderInput 
{
    @NotNull
    UUID id;
}
