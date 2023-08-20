package br.com.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CaminhoArquivo {

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

    public static CaminhoArquivo getInstance(Integer id) throws Error {
        if (id == null)
            throw new Error("Id não informado");
        // Renomeacao: variáveis devem ter nomes significativos(Clean Code)
        String caminhoBase = "/tmp/";
        String caminhoDiretorio = null;
        int incremento = id % 1000 == 0 ? 0 : 1;
        int numeroSubDiretorio = (id / 1000) + incremento;
        caminhoDiretorio = caminhoBase + Integer.toString(numeroSubDiretorio);
        String caminhoArquivo = caminhoDiretorio + "/" + id;

        // Código removido
        // remocao de blocos aninhados em mais de 2 níveis(Clean Code)
        // reducão da complexidade do algoritmo

        return new CaminhoArquivo(Paths.get(caminhoDiretorio), Paths.get(caminhoArquivo));
    }

}
