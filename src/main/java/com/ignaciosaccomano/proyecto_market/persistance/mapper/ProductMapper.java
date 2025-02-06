package com.ignaciosaccomano.proyecto_market.persistance.mapper;

import com.ignaciosaccomano.proyecto_market.domain.Product;
import com.ignaciosaccomano.proyecto_market.persistance.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})   // Cuando convierta categoria en category sabe que tiene que usar este mapper.
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
    })
    Product toProduct(Producto producto);

    List<Product> toProducts(List<Producto> productos); // Mapstuct internamente sabe que tiene que aplicar la conversion superior para la lista asi que no defino nada mas.

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);
}
