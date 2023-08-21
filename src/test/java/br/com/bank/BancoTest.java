package br.com.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

public class BancoTest {

    Banco banco;

    @BeforeEach
    public void setup() {
        banco = new Banco("SerproBank");
    }

    @Test
    public void deve_recuperar_conta_por_cliente_existente() {
        banco.adicionarConta(new Conta("123"));

        Optional<Conta> conta = banco.pesquisarContaDoCliente("123");
        assertTrue(conta.isPresent());
        assertEquals("123", conta.get().getCpf());
    }

    @Test
    public void deve_adicionar_conta() {
        String cpf = "123.456.789-00";
        Conta conta = new Conta(cpf);
        banco.adicionarConta(conta);
        assertEquals(cpf, banco.pesquisarContaDoCliente(cpf).get().getCpf());
    }

    @Test
    public void deve_pesquisar_conta_do_cliente_com_performance() {
        for (int i = 0; i < 1000; i++) {
            banco.adicionarConta(new Conta("123.456.789-" + i));
        }
        assertEquals("123.456.789-1", banco.pesquisarContaDoCliente("123.456.789-1").get().getCpf());
        assertEquals("123.456.789-500", banco.pesquisarContaDoCliente("123.456.789-500").get().getCpf());
        assertEquals("123.456.789-999", banco.pesquisarContaDoCliente("123.456.789-999").get().getCpf());
    }

    @Test
    public void deve_retornar_null_ao_pesquisar_inexistente() {
        String cpf = "123.456.789-00";
        assertFalse(banco.pesquisarContaDoCliente(cpf).isPresent());
    }

    @Test
    public void deve_listar_contas_de_alta_renda() {
        banco.adicionarConta(new Conta("123.456.789-00", 10_000));
        banco.adicionarConta(new Conta("123.456.789-01", 10_001));
        banco.adicionarConta(new Conta("123.456.789-02", 999));
        banco.adicionarConta(new Conta("123.456.789-03", 0));
        assertEquals(banco.listarContasAltaRenda().size(), 2);
    }

}
