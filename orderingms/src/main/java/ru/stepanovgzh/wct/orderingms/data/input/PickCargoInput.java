package ru.stepanovgzh.wct.orderingms.data.input;

import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.value.SkuPickingStatus;

@Value
public class PickCargoInput 
{
    @NotNull
    UUID pickingOrderId;

    @NotNull
    UUID detailId;

    @NotNull
    UUID cargoId;

    @NotNull
    SkuPickingStatus skuPickingStatus;
}
