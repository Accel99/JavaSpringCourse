package ru.education.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import ru.education.entity.SalesJdbc;
import ru.education.entity.SalesJpa;
import ru.education.jdbc.SalesJdbcRepository;
import ru.education.jpa.Product;
import ru.education.jpa.ProductRepository;
import ru.education.jpa.SalesJpaRepository;
import ru.education.model.Formatter;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TestController {

    private final Formatter formatter;

    private final ProductRepository productRepository;

    private final SalesJdbcRepository salesJdbcRepository;

    private final SalesJpaRepository salesJpaRepository;

    @Autowired
    public TestController(@Qualifier("fooFormatter") Formatter formatter,
                          ProductRepository productRepository,
                          SalesJdbcRepository salesJdbcRepository,
                          SalesJpaRepository salesJpaRepository
    ) {
        this.formatter = formatter;
        this.productRepository = productRepository;
        this.salesJdbcRepository = salesJdbcRepository;
        this.salesJpaRepository = salesJpaRepository;
    }

    @GetMapping("/jpa/hello")
    public String getHello() {
        return "Hello World!";
    }

    @GetMapping("/jpa/format")
    public String getFormatter() {
        return formatter.format();
    }

    @GetMapping("/jpa/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/jdbs/sales")
    public List<SalesJdbc> getSalesJdbs() {
        return salesJdbcRepository.getSales();
    }

    @PostMapping("/jdbs/sales/add")
    public int addSalesJdbs(@RequestBody SalesJdbc salesJdbc) {
        return salesJdbcRepository.addSales(salesJdbc);
    }

    @GetMapping("/jpa/sales")
    public List<SalesJpa> getSalesJpa() {
        return salesJpaRepository.findAll();
    }

    @PostMapping("/jpa/sales/add")
    public SalesJpa addSalesJpa(@RequestBody SalesJpa salesJpa) {
        return salesJpaRepository.save(salesJpa);
    }

    @PostMapping("/jpa/sales/id")
    public SalesJpa getSalesById(@RequestBody SalesJpa salesJpa) {
        return salesJpaRepository.getById(salesJpa.getId());
    }
}
