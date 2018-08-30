package com.example.pools.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pools.model.Livro;
import com.example.pools.repository.LivroRepository;
import com.example.pools.security.CurrentUser;
import com.example.pools.security.UserPrincipal;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(PollController.class);

    @GetMapping("/{pageNumber}/{numberElements}")
    @PreAuthorize("hasRole('USER')")
    public Page<Livro> getLivros(@PathVariable(name="pageNumber") int pageNumber, @PathVariable(name="numberElements") int numberElements) {
        Pageable pageable = PageRequest.of(pageNumber, numberElements, Sort.Direction.ASC, "nome");
        return livroRepository.findAll(pageable);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Livro> getLivros(@CurrentUser UserPrincipal currentUser) {     
    	logger.info(currentUser.toString());
        return livroRepository.findAll();
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
