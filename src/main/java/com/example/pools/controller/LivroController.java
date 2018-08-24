package com.example.pools.controller;

import com.example.pools.model.Livro;
import com.example.pools.payload.PagedResponse;
import com.example.pools.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Page<Livro> getLivros() {
        Pageable pageable = PageRequest.of(1, 10, Sort.Direction.ASC, "nome");

        return livroRepository.findAll(pageable);
    }
    

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<Livro> getLivro(@PathVariable(name = "id") Long id ) {
        return livroRepository.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Livro getLivros(@Valid @RequestBody Livro livro) {
        return livroRepository.save(livro);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public Livro updateLivro(@Valid @RequestBody Livro livro) {
        return livroRepository.save(livro);
    }
}
