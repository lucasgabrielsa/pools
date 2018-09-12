package com.example.pools.repository;

import com.example.pools.model.Livro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LivroRepository extends JpaRepository<Livro, Long>, JpaSpecificationExecutor   {
	
	@Override
	List<Livro> findAll();
	
    Page<Livro> findAll(Pageable pagina);
        
    Page<Livro> findAll(Specification spec, Pageable pagina);
}
