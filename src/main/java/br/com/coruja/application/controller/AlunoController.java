package br.com.coruja.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Aluno find(@PathVariable int id) {
        return alunoRepository.getById(id);
    }

    @GetMapping
    public List<Aluno> list() {
        return alunoRepository.findAll();
    }

    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable int id, @RequestBody Aluno aluno) {
        Aluno alunoExiste = alunoRepository.getById(id);
        if (alunoExiste == null)
            throw new Error("Aluno not found");
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        alunoRepository.deleteById(id);
    }

}
