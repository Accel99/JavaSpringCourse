package ru.education.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.education.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
