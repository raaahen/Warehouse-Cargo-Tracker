package ru.stepanovgzh.wct.orderingms.data.input;

import java.util.UUID;

import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.value.SkuPickingStatus;

@Value
public class PickCargoInput 
{
    UUID pickingOrderId;
    UUID detailId;
    UUID cargoId;
    SkuPickingStatus skuPickingStatus;
}
