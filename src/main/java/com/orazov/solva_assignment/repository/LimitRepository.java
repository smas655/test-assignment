package com.orazov.solva_assignment.repository;

import com.orazov.solva_assignment.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findFirstByExpenseCategoryOrderByLimitDatetimeDesc(String expenseCategory);
}