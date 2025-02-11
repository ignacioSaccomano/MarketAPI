package com.ignaciosaccomano.proyecto_market.persistance;

import com.ignaciosaccomano.proyecto_market.domain.Purchase;
import com.ignaciosaccomano.proyecto_market.domain.repository.PurchaseRepository;
import com.ignaciosaccomano.proyecto_market.persistance.crud.CompraCrudRepository;
import com.ignaciosaccomano.proyecto_market.persistance.entity.Compra;
import com.ignaciosaccomano.proyecto_market.persistance.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;      // El repositorio debe hablar en terminos del dominio, por eso el mapeo.

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);  // Esto porque compraCrudRepository funciona con Compra no con purchase.

        compra.getProductos().forEach(producto -> producto.setCompra(compra));  // Antes de registrar la compra debemos indicarle
        // a los productos seleccionados a que compra pertenecen. Luego los registramos en cascada.

        // Una vez guardada la compra queremos devolverla pero en su version del dominio, que es purchase.
        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
