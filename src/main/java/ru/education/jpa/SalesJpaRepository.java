package ru.education.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.education.entity.SalesJpa;

@Repository
public interface SalesJpaRepository extends JpaRepository<SalesJpa, Integer> {

    SalesJpa getById(int id);
}
