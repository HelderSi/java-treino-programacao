package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {

    private final static int QUANTIDADE_ARQUIVOS_POR_DIRETORIO = 1000; // evitar magic numbers
    private final static String DIRETORIO_BASE = "/tmp/";

    private Path diretorio;

    private Path arquivo;

    private CaminhoArquivo(Path diretorio, Path arquivo) {
        super();
        this.diretorio = diretorio;
        this.arquivo = arquivo;
    }

    public Path getDiretorio() {
        return diretorio;
    }

    public Path getArquivo() {
        return arquivo;
    }

    public static CaminhoArquivo getInstance(Integer id) throws RuntimeException {
        if (id == null)
            throw new RuntimeException("Id não informado");
        // Renomeacao: variáveis devem ter nomes significativos(Clean Code)
        String caminhoDiretorio = null;
        int incremento = id % QUANTIDADE_ARQUIVOS_POR_DIRETORIO == 0 ? 0 : 1;
        int numeroSubDiretorio = (id / QUANTIDADE_ARQUIVOS_POR_DIRETORIO) + incremento; // evitar magic numbers
        caminhoDiretorio = DIRETORIO_BASE + Integer.toString(numeroSubDiretorio);
        String caminhoArquivo = caminhoDiretorio + "/" + id;

        // Código removido
        // remocao de blocos aninhados em mais de 2 níveis(Clean Code)
        // reducão da complexidade ciclomática do algoritmo

        return new CaminhoArquivo(Paths.get(caminhoDiretorio), Paths.get(caminhoArquivo));
    }

}
