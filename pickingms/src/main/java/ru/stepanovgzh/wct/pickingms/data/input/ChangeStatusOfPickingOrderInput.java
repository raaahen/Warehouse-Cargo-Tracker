package ru.stepanovgzh.wct.pickingms.data.input;

import jakarta.validation.constraints.NotNull;
import lombok.Value;
import ru.stepanovgzh.wct.pickingms.data.value.PickingStatus;

import java.util.UUID;

@Value
public class ChangeStatusOfPickingOrderInput
{
    @NotNull
    UUID id;

    @NotNull
    PickingStatus status;
}
