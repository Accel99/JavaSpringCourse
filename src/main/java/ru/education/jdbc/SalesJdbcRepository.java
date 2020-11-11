package ru.education.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.education.entity.SalesJdbc;

import java.util.List;

@Repository
public class SalesJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SalesJdbc> getSales() {
        return jdbcTemplate.query("select * from sales", (rs, rowNum) ->
                new SalesJdbc(
                        rs.getInt("id"),
                        rs.getInt("product_id"),
                        rs.getInt("count"),
                        rs.getInt("price")
                ));
    }

    public int addSales(SalesJdbc salesJdbc) {
        return jdbcTemplate.update("insert into sales(product_id, count, price) values (?, ? , ?)",
                salesJdbc.getProductId(),
                salesJdbc.getCount(),
                salesJdbc.getPrice()
        );
    }
}
