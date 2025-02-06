package com.ignaciosaccomano.proyecto_market.persistance.mapper;

import com.ignaciosaccomano.proyecto_market.domain.Category;
import com.ignaciosaccomano.proyecto_market.persistance.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source="idCategoria", target="categoryId"),
            @Mapping(source="descripcion", target="category"),
            @Mapping(source="estado", target="active"),
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration    // Indica que hago la conversion inversa por lo que no tengo que definir nada.
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
