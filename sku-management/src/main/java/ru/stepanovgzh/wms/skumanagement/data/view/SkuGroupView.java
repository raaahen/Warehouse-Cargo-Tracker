package ru.stepanovgzh.wms.skumanagement.data.view;

import java.util.UUID;

import lombok.Value;

@Value
public class SkuGroupView 
{
    private UUID skuId;
    private String name;
    private String description;
}
