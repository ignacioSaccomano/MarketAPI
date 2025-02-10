package com.ignaciosaccomano.proyecto_market.persistance.mapper;

import com.ignaciosaccomano.proyecto_market.domain.Purchase;
import com.ignaciosaccomano.proyecto_market.domain.PurchaseItem;
import com.ignaciosaccomano.proyecto_market.persistance.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemMapper.class})
public interface PurchaseMapper {

    @Mappings({
            @Mapping(source = "idCompra", target = "purchaseId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "productos", target = "items"),
    })
    Purchase toPurchase(Compra compra);

    // No hace falta definir el mapper porque es una generalizacion de lo de arriba.
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true) // La clase destino tiene que tener todos los mapeos, por eso lo ignoro explicitamente.
    Compra toCompra(Purchase purchase);
}
