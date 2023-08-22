package br.com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import br.com.bank.errors.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

// Implementacao simples, mas dinâmica e funcional
public class BacenFake extends Bacen {

    List<Banco> bancos = new ArrayList<>();

    public long cadastrarBanco(Banco banco) {
        if (buscaBancoPorNome(banco.getNome()).isPresent())
            throw new BancoNaoCadastradoException();
        long numeroRegistro = Math.abs(new Random().nextLong());
        bancos.add(banco);
        return numeroRegistro;
    }

    public Optional<Banco> buscaBancoPorNome(String nome) {
        return bancos.stream().filter(banco -> banco.getNome() == nome).findFirst();
    }
}
