package br.com.bank.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;

    public Banco(String nome) {
        this.nome = nome;
    }

    private List<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public Conta pesquisarContaDoCliente(String cpf) {
        Conta c = null;
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getCpf().equals(cpf)) {
                c = contas.get(i);
                break; // evita percorrer toda a lista: interrompe a busca assim que encontra a conta
            }
        }
        return c;
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.stream().filter(filtro).collect(Collectors.toList());
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
