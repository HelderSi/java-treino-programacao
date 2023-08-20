package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

class SistemaBancarioTest {

    // @InjectMocks
    // private SistemaBancario sistemaBancario;
    // @Mock
    // private Bacen bacen;
    @Test
    public void deve_adicionar_conta() {
        Banco banco = new Banco("SerproBank");
        String cpf = "123.456.789-00";
        Conta conta = new Conta(cpf);
        banco.adicionarConta(conta);
        assertEquals(cpf, banco.pesquisarContaDoCliente(cpf).getCpf());
    }

    @Test
    public void deve_pesquisar_conta_do_cliente_com_performance() {
        Banco banco = new Banco("SerproBank");
        for (int i = 0; i < 1000; i++) {
            banco.adicionarConta(new Conta("123.456.789-" + i));
        }
        assertEquals("123.456.789-1", banco.pesquisarContaDoCliente("123.456.789-1").getCpf());
        assertEquals("123.456.789-500", banco.pesquisarContaDoCliente("123.456.789-500").getCpf());
        assertEquals("123.456.789-999", banco.pesquisarContaDoCliente("123.456.789-999").getCpf());

    }

    @Test
    public void deve_retornar_null_ao_pesquisar_inexistente() {
        Banco banco = new Banco("SerproBank");
        String cpf = "123.456.789-00";
        assertEquals(null, banco.pesquisarContaDoCliente(cpf));
    }

    @Test
    public void deve_listar_contas_de_alta_renda() {
        Banco banco = new Banco("SerproBank");
        banco.adicionarConta(new Conta("123.456.789-00", 10_000));
        banco.adicionarConta(new Conta("123.456.789-01", 10_001));
        banco.adicionarConta(new Conta("123.456.789-02", 999));
        banco.adicionarConta(new Conta("123.456.789-03", 0));
        assertEquals(banco.listarContasAltaRenda().size(), 2);
    }

    @Test
    public void deve_registrar_banco_no_sistema_bancario() {
        SistemaBancario sistemaBancario = new SistemaBancario(new BacenFake());
        long numeroRegistro = sistemaBancario.registrarBanco(new Banco("SerproBank"));
        assertTrue(numeroRegistro > 0);
    }

}