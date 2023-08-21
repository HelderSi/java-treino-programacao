package br.com.bank.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Banco {

    private String nome;

    private static final int VALOR_CONTA_ALTA_RENDA = 10000; // Evitar magic number

    public Banco(String nome) {
        this.nome = nome;
    }

    private Map<String, Conta> contas = new HashMap<>();

    public void adicionarConta(Conta conta) {
        contas.put(conta.getCpf(), conta);
    }

    public Optional<Conta> pesquisarContaDoCliente(String cpf) {
        // Conta conta = null;
        // for (int i = 0; i < contas.size(); i++) {
        // if (contas.get(i).getCpf().equals(cpf)) {
        // conta = contas.get(i);
        // break; // evita percorrer toda a lista: interrompe a busca assim que encontra
        // a conta
        // }
        // }

        // return Optional.of(conta);
        return Optional.ofNullable(contas.get(cpf));

    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= VALOR_CONTA_ALTA_RENDA); // evitar magic number
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.values().stream().filter(filtro).collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((contas == null) ? 0 : contas.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Banco other = (Banco) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (contas == null) {
            if (other.contas != null)
                return false;
        } else if (!contas.equals(other.contas))
            return false;
        return true;
    }

}
