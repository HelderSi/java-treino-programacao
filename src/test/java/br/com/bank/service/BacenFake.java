package br.com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenFake extends Bacen {

    List<Banco> bancos = new ArrayList<>();

    public long cadastrarBanco(Banco banco) {
        long numeroRegistro = new Random().nextLong();
        bancos.add(banco);
        return numeroRegistro;
    }
}
