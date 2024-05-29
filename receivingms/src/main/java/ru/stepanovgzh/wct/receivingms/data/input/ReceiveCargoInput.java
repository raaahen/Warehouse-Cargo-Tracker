package ru.stepanovgzh.wct.receivingms.data.input;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.stepanovgzh.wct.receivingms.data.value.SkuReceivingStatus;

@Value
public class ReceiveCargoInput 
{
    @NotNull
    UUID receivingOrderId;

    @NotNull
    UUID detailId;

    @NotNull
    UUID cargoId;

    @NotNull
    SkuReceivingStatus skuReceivingStatus;
}
