package ru.education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.education.entity.Product;
import ru.education.entity.SalesJpa;
import ru.education.exceptions.EntityAlreadyExistsException;
import ru.education.exceptions.EntityHasDetailsException;
import ru.education.exceptions.EntityIllegalArgumentException;
import ru.education.exceptions.EntityNotFoundException;
import ru.education.jpa.ProductRepository;
import ru.education.jpa.SalesJpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final SalesJpaRepository salesJpaRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SalesJpaRepository salesJpaRepository) {
        this.productRepository = productRepository;
        this.salesJpaRepository = salesJpaRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Object id) {
        Product product;

        if (id == null) {
            throw new EntityIllegalArgumentException("Идентификатор объекта не может быть null");
        }

        int parsedId;
        try {
            parsedId = Integer.valueOf(id.toString());
        } catch (NumberFormatException ex) {
            throw new EntityIllegalArgumentException(String.format("Не удалось преобразовать идентификатор " +
                    "к нужному типу, текст ошибки: %s", ex));
        }

        try {
            product = productRepository.findById(parsedId).get();
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException(Product.TYPE_NAME, parsedId);
        }

        return product;
    }

    public Product create(Product product) {
        if (product == null) {
            throw new EntityAlreadyExistsException("Создаваемый объект не может быть null");
        }

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new EntityAlreadyExistsException("Имя продукта не может быть пустым");
        }

        return productRepository.save(product);
    }

    public void delete(Object id) {
        Product product = findById(id);
        List<SalesJpa> salesJpaList = salesJpaRepository.findByProduct(product);

        if (salesJpaList.size() > 0) {
            throw new EntityHasDetailsException(SalesJpa.TYPE_NAME, product.getId());
        }

        productRepository.delete(product);
    }
}
