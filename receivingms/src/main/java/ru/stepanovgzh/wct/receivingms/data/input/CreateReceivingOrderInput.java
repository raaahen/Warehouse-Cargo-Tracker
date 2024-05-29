package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class CreateReceivingOrderInput 
{
    @NotNull
    UUID supplierId;

    @NotBlank
    String supplierName;

    @NotNull
    UUID transporterId;

    @NotBlank
    String transporterName;

    @NotNull
    @FutureOrPresent
    Date date;
}
