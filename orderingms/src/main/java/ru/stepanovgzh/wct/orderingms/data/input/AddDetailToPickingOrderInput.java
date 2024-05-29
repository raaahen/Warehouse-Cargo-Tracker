package ru.stepanovgzh.wct.orderingms.data.input;

import java.util.UUID;

import jakarta.validation.constraints.*;
import lombok.Value;
import ru.stepanovgzh.wct.orderingms.data.value.PackType;

@Value
public class AddDetailToPickingOrderInput 
{
    @NotNull
    UUID pickingOrderId;

    @NotBlank
    @Size(max = 50)
    String skuBarcode;

    @NotBlank
    @Size(max = 100)
    String skuName;

    @NotBlank
    @Size(max = 255)
    String skuDecription;

    @Min(value = 1)
    int qty;

    @NotNull
    PackType packType;

    @NotBlank
    @Size(max = 255)
    String packDescription;
}
