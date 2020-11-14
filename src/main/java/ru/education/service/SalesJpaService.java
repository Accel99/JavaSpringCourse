package ru.education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.education.entity.Product;
import ru.education.entity.SalesJpa;
import ru.education.exceptions.EntityAlreadyExistsException;
import ru.education.exceptions.EntityIllegalArgumentException;
import ru.education.exceptions.EntityNotFoundException;
import ru.education.jpa.ProductRepository;
import ru.education.jpa.SalesJpaRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SalesJpaService {

    private final ProductRepository productRepository;

    private final SalesJpaRepository salesJpaRepository;

    @Autowired
    public SalesJpaService(SalesJpaRepository salesJpaRepository, ProductRepository productRepository) {
        this.salesJpaRepository = salesJpaRepository;
        this.productRepository = productRepository;
    }

    public List<SalesJpa> findAll() {
        return salesJpaRepository.findAll();
    }

    public SalesJpa findById(Object id) {
        SalesJpa salesJpa;

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
            salesJpa = salesJpaRepository.findById(parsedId).get();
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException(SalesJpa.TYPE_NAME, parsedId);
        }

        return salesJpa;
    }

    public SalesJpa create(SalesJpa salesJpa) {
        if (salesJpa == null) {
            throw new EntityAlreadyExistsException("Создаваемый объект не может быть null");
        }

        if (salesJpa.getProduct() == null) {
            throw new EntityAlreadyExistsException("Продукт не может быть null");
        }

        if (salesJpa.getProduct().getId() == null) {
            throw new EntityAlreadyExistsException("Идентификатор продукта не может быть null");
        }

        try {
            productRepository.findById(salesJpa.getProduct().getId()).get();
        } catch (NoSuchElementException ex) {
            throw new EntityNotFoundException(Product.TYPE_NAME, salesJpa.getProduct().getId());
        }

        if (salesJpa.getCount() == null) {
            throw new EntityAlreadyExistsException("Количество проданного продукта не может быть null");
        }

        if (salesJpa.getPrice() == null) {
            throw new EntityAlreadyExistsException("Цена проданного продукта не может быть null");
        }

        return salesJpaRepository.save(salesJpa);
    }

    public void delete(Object id) {
        SalesJpa salesJpa = findById(id);
        salesJpaRepository.delete(salesJpa);
    }
}
