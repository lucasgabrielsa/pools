package com.example.pools.repository;

import com.example.pools.model.Livro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	@Override
	List<Livro> findAll();
	
    Page<Livro> findAll(Pageable pagina);
}
