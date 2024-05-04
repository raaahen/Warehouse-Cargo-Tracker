package ru.stepanovgzh.wms.skumanagement.data.view;

import java.util.UUID;

import lombok.Value;

@Value
public class SkuView 
{
    private UUID skuId;
    private String barcode;
    private String name;
    private String description;
}
