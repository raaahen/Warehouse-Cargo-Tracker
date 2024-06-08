package ru.stepanovgzh.wct.pickingms.data.input;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.stepanovgzh.wct.pickingms.data.value.SkuPickingStatus;

import java.util.UUID;

@Value
public class ChangeStatusOfPODetailInput
{
    @NotNull
    UUID id;

    @NotNull
    UUID detailId;

    @NotNull
    SkuPickingStatus status;
}
