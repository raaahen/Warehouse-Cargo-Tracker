package ru.stepanovgzh.wms.productmanagement.domain.model;

import java.lang.invoke.MethodHandles;
import java.util.UUID;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class Product 
{
    private final static Logger logger 
        = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @AggregateIdentifier
    private UUID productId;
    private Barcode barcode;
    private Name name;
    private Description description;

    protected Product()
    {
        logger.info("Empty product created");
    }
}
