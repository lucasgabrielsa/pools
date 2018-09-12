package com.example.pools.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pools.model.Livro;
import com.example.pools.repository.LivroRepository;
import com.example.pools.security.CurrentUser;
import com.example.pools.security.UserPrincipal;
import com.example.specification.LivroSpecifications;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(PollController.class);

//    @GetMapping("/{pageNumber}/{numberElements}") 
//    public Page<Livro> getLivros(@PathVariable(name="pageNumber") int pageNumber, @PathVariable(name="numberElements") int numberElements) {
//        Pageable pageable = PageRequest.of(pageNumber, numberElements, Sort.Direction.ASC, "nome");
//        return livroRepository.findAll(pageable);
//    }
    
    @GetMapping   
    public Page<Livro> getLivros(@RequestParam(name="pageNumber") int pageNumber, @RequestParam(name="numberElements") int numberElements, @RequestParam(name="order") String order, @RequestParam(name="sort") String sort) {  
    	Sort.Direction directionSort = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    	Pageable pageable = PageRequest.of(pageNumber, numberElements, directionSort, sort);
    	//logger.info(currentUser.toString());    	
    	//String nomeLivro = "Qualquer coi";
        //return livroRepository.findAll(LivroSpecifications.buscaNormal().and(LivroSpecifications.buscaNome(nomeLivro)), pageable);
    	Livro livro = new Livro();
    	//livro.setId(3l);
    	livro.setNome("Guia do");
    	return livroRepository.findAll(LivroSpecifications.buscaPadrao(livro), pageable);
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
