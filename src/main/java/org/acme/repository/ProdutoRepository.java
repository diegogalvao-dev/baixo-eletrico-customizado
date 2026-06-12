package org.acme.repository;

import org.acme.model.Produto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public PanacheQuery<Produto> searchByTerm(String term) {
        String q = term.toLowerCase() + "%";
        return find("lower(name) like ?1", q);
    }

    public PanacheQuery<Produto> searchByPriceRange(Double min, Double max) {
        return find("price between ?1 and ?2", min, max);
    }

    public PanacheQuery<Produto> searchFiltered(String term, Double min, Double max, String sort) {
        StringBuilder query = new StringBuilder("1=1");
        java.util.Map<String, Object> params = new java.util.HashMap<>();

        if (term != null && !term.isBlank()) {
            query.append(" and lower(name) like :term");
            params.put("term", term.toLowerCase() + "%");
        }

        if (min != null) {
            query.append(" and price >= :min");
            params.put("min", min);
        }

        if (max != null) {
            query.append(" and price <= :max");
            params.put("max", max);
        }

        String orderString = "";
        if ("precoAsc".equals(sort)) {
            orderString = " order by price asc";
        } else if ("precoDesc".equals(sort)) {
            orderString = " order by price desc";
        } else if ("maisVendidos".equals(sort)) {
            orderString = " order by id desc"; // Fallback para id desc ou qualquer outra lógica
        } else if ("lancamentos".equals(sort)) {
            orderString = " order by id desc";
        }

        return find(query.toString() + orderString, params);
    }

}
