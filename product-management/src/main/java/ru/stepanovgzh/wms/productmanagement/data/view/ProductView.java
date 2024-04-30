package ru.stepanovgzh.wms.productmanagement.data.view;

import java.util.UUID;

import lombok.Value;

@Value
public class ProductView 
{
    private UUID productId;
    private String barcode;
    private String name;
    private String description;
}
