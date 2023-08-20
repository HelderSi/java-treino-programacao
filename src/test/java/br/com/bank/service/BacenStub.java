package br.com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.bank.errors.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenStub extends Bacen {
    List<Banco> bancos = new ArrayList<>();

    public long cadastrarBanco(Banco banco) {
        long numeroRegistro = new Random().nextLong();
        if (bancos.contains(banco)) {
            throw new BancoNaoCadastradoException();
        }
        bancos.add(banco);
        return numeroRegistro;
    }
}
