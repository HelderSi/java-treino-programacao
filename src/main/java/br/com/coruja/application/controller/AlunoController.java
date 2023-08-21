package br.com.coruja.application.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coruja.application.model.entities.Aluno;
import br.com.coruja.application.model.repositories.AlunoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> find(@PathVariable int id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent())
            return new ResponseEntity<>(aluno.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Aluno>> list() {
        return new ResponseEntity<>(alunoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        Aluno alunoCreated = alunoRepository.save(aluno);
        return new ResponseEntity<>(alunoCreated, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> update(@PathVariable int id, @RequestBody Aluno aluno) {
        Optional<Aluno> alunoExiste = alunoRepository.findById(id);
        if (alunoExiste.isPresent()) {
            alunoRepository.save(aluno);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Aluno> delete(@PathVariable int id) {
        alunoRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
