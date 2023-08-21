package br.com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.bank.errors.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

// Implementacao estática (nao funcional)
public class BacenStub extends Bacen {

    public long cadastrarBanco(Banco banco) {
        return 1;
    }
}
