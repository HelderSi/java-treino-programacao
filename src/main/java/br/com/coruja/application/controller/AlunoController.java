package br.com.coruja.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> find(@PathVariable int id) {
        Aluno aluno = alunoRepository.findById(id).orElse(null);
        if (aluno == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(aluno);
    }

    @GetMapping
    public List<Aluno> list() {
        return (List<Aluno>) alunoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        Aluno alunoCreated = alunoRepository.save(aluno);
        return new ResponseEntity<Aluno>(alunoCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable int id, @RequestBody Aluno aluno) {
        Aluno alunoExiste = alunoRepository.findById(id).orElse(null);
        if (alunoExiste == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        alunoRepository.deleteById(id);
    }

}
