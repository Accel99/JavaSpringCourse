package ru.education.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.education.entity.Product;
import ru.education.entity.SalesJpa;

import java.util.List;

@Repository
public interface SalesJpaRepository extends JpaRepository<SalesJpa, Integer> {

    List<SalesJpa> findByProduct(Product product);
}
