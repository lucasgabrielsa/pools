package com.example.specification;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.data.jpa.domain.Specification;

import com.example.pools.model.Livro;

public class LivroSpecifications {
	
	public static Specification<Livro> buscaNormal() {
        return new Specification<Livro>() {
        	 public Predicate toPredicate(
                     Root<Livro> root, CriteriaQuery<?> query,
                     CriteriaBuilder builder) {                     
                     return builder.equal(root.get("id"), 2);
                 }
        };
    }
	
	public static Specification<Livro> buscaNome(String livro) {
        return new Specification<Livro>() {
        	 public Predicate toPredicate(
                     Root<Livro> root, CriteriaQuery<?> query,
                     CriteriaBuilder builder) {                     
                     return builder.like(root.<String>get("nome"), "%".concat(livro).concat("%"));
                 }
        };
    }
	
	public static Specification<Livro> buscaPadrao(Livro livro) {
        return new Specification<Livro>() {
        	 public Predicate toPredicate(
                     Root<Livro> root, CriteriaQuery<?> query,
                     CriteriaBuilder builder) {                     
                     
        		 	List<Predicate> predicates = new ArrayList<Predicate>();
        		 	
        		 	if(livro.getId() != null) {
        		 		predicates.add(builder.equal(root.get("id"), livro.getId()));
        		 	}
        		 	
        		 	if(livro.getNome() != null && !livro.getNome().isEmpty()) {
        		 		predicates.add(builder.like(root.<String>get("nome"), "%".concat(livro.getNome()).concat("%")));
        		 	}
        		 	
        		 	return builder.and(predicates.toArray(new Predicate[] {}));
        		 
                 }
        };
    }


}
