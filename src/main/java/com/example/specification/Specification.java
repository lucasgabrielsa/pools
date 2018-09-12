package com.example.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.cglib.core.Predicate;

public interface Specification<T> {
    Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
           CriteriaBuilder builder);
}