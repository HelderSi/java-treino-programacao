package br.com.bank.service;

import br.com.bank.errors.BancoNaoCadastradoException;
import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import br.com.bank.model.Conta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)

class SistemaBancarioTest {

    @InjectMocks
    private SistemaBancario sistemaBancario;
    @Mock
    private Bacen bacen;

    @BeforeEach
    public void setup() {
        sistemaBancario = new SistemaBancario(new BacenFake());
    }

    @Test
    public void deve_registrar_banco_no_sistema_bancario() {
        long numeroRegistro = sistemaBancario.registrarBanco(new Banco("SerproBank"));
        assertTrue(numeroRegistro > 0);
    }

    @Test
    public void deve_lancar_excecao_para_banco_ja_cadastrado() {
        sistemaBancario.registrarBanco(new Banco("SerproBank"));
        assertThrows(BancoNaoCadastradoException.class, () -> {
            sistemaBancario.registrarBanco(new Banco("SerproBank"));
        });
    }

}