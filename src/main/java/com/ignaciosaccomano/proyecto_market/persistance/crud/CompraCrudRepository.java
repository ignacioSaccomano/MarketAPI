package com.ignaciosaccomano.proyecto_market.persistance.crud;

import com.ignaciosaccomano.proyecto_market.persistance.entity.Compra;
import org.hibernate.boot.archive.scan.internal.ScanResultImpl;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompraCrudRepository extends CrudRepository<Compra, Integer> {
    // Creamos query method para obtener por cliente
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
