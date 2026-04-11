package org.acme.repository;

import java.util.List;

import org.acme.model.PedidoItem;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoItemRepository implements PanacheRepository<PedidoItem> {

    public List<PedidoItem> findByIds(List<Long> ids) {
        return list("id in ?1", ids);
    }

    public long countByProdutoId(Long produtoId) {
        return count("produto.id = ?1", produtoId);
    }

}
