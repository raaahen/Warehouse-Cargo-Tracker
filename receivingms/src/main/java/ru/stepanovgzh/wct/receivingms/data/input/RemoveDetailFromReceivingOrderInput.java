package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RemoveDetailFromReceivingOrderInput 
{
    @NotNull
    UUID receivingOrderId;
    
    @NotNull
    UUID detailId;
}
