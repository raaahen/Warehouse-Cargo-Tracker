package ru.stepanovgzh.wct.pickingms.data.input;

import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class CreatePickingOrderInput 
{
    @NotNull
    private UUID clientId;

    @NotBlank
    private String clientName;

    @NotNull
    private UUID transporterId;

    @NotBlank
    private String transporterName;

    @NotNull
    @FutureOrPresent
    private Date preparationDeadline;
}
