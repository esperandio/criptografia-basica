import java.util.Arrays;
import java.util.Random;

public class Criptografia {
    static final String TABELA_CODIFICACAO = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final int TAMANHO_CHAVE_CODIFICACAO = 5;

    public String criptografar(String fraseDescriptografada) throws Exception {
        int[] arrCodificacao = this.getArrCodificacaoFromFrase(fraseDescriptografada);
        int[] arrChaveCriptografia = this.gerarChaveCriptografia(arrCodificacao);

        return String.format(
            "Frase criptografada: [%s], Chave: %s",
            this.gerarFraseCriptografada(arrCodificacao, arrChaveCriptografia),
            Arrays.toString(arrChaveCriptografia).replace(",", "")
        );
    }

    private int[] getArrCodificacaoFromFrase(String frase) throws Exception {
        int tamanhoFraseCriptografada = this.getTamanhoFraseCriptografada(frase);

        int[] arrCodificacaoFromFrase = new int[tamanhoFraseCriptografada];

        frase = frase.toUpperCase();

        for (int i = 0; i < frase.length(); i++) {
            int codificacao = TABELA_CODIFICACAO.indexOf(frase.charAt(i));

            if (codificacao == -1) {
                throw new Exception(
                    String.format(
                        "Caractere inválido na mensagem (%s)",
                        frase.charAt(i)
                    )
                );
            }

            arrCodificacaoFromFrase[i] = codificacao;
        }

        return arrCodificacaoFromFrase;
    }

    private int getTamanhoFraseCriptografada(String frase) {
        int tamanhoFrase = frase.length();

        // Valida se o tamanho da frase não é multiplo do tamanho da chave de codificação
        // Se verdadeiro, contabiliza os caracteres adicionais necessários 
        // para que o número se torne múltiplo da chave de codificação
        int caracteresAdicionais = (tamanhoFrase % TAMANHO_CHAVE_CODIFICACAO) > 0
            ? TAMANHO_CHAVE_CODIFICACAO - (tamanhoFrase % TAMANHO_CHAVE_CODIFICACAO)
            : 0;

        int tamanhoFraseCriptografada = tamanhoFrase + caracteresAdicionais;

        return tamanhoFraseCriptografada;
    }

    private int[] gerarChaveCriptografia(int[] arrCodificacao) {
        int[] arrNumerosMaximosParaChave = this.getArrNumerosMaximosParaChave(arrCodificacao);

        Random random = new Random();

        int[] arrChaveCriptografia = new int[TAMANHO_CHAVE_CODIFICACAO];

        // Gerar número randômico com base no máximo de cada posição da chave
        for (int i = 0; i < TAMANHO_CHAVE_CODIFICACAO; i++) {
            int numeroMaximoChave = TABELA_CODIFICACAO.length() - arrNumerosMaximosParaChave[i];

            arrChaveCriptografia[i] = random.nextInt(numeroMaximoChave);
        }

        return arrChaveCriptografia;
    }

    private int [] getArrNumerosMaximosParaChave(int[] arrCodificacao) {
        int[] arrNumerosMaximosParaChave = new int[TAMANHO_CHAVE_CODIFICACAO];

        // Calcular valor máximo para cada posição da chave
        for (int i = 0; i < arrCodificacao.length; i++) {
            int posicaoChaveCriptografia = i % TAMANHO_CHAVE_CODIFICACAO;

            if (arrCodificacao[i] > arrNumerosMaximosParaChave[posicaoChaveCriptografia]) {
                arrNumerosMaximosParaChave[posicaoChaveCriptografia] = arrCodificacao[i];
            }
        }

        return arrNumerosMaximosParaChave;
    }

    private String gerarFraseCriptografada(int[] arrCodificacao, int[] arrChaveCriptografia) {
        String fraseCriptografada = "";
        
        for (int i = 0; i < arrCodificacao.length; i++) {
            int posicaoChaveCriptografia = i % TAMANHO_CHAVE_CODIFICACAO;
            int codificacao = arrCodificacao[i] + arrChaveCriptografia[posicaoChaveCriptografia];

            fraseCriptografada += TABELA_CODIFICACAO.charAt(codificacao);
        }

        return fraseCriptografada;
    }

    public String descriptografar(String fraseCriptografada, String chaveCriptografia) throws Exception {
        int[] arrCodificacao = this.getArrCodificacaoFromFrase(fraseCriptografada);
        
        // Converte String para array e transforma os elementos em int
        int[] arrChaveCriptografia = Arrays.stream(chaveCriptografia.split(" ")).mapToInt(Integer::parseInt).toArray();

        if (arrChaveCriptografia.length != TAMANHO_CHAVE_CODIFICACAO) {
            throw new Exception("Chave de criptografia inválida");
        }

        return String.format(
            "Frase descriptografada: [%s]",
            this.gerarFraseDescriptografada(arrCodificacao, arrChaveCriptografia).trim()
        );
    }

    private String gerarFraseDescriptografada(int[] arrCodificacao, int[] arrChaveCriptografia) throws Exception {
        String fraseDescriptografada = "";
        
        for (int i = 0; i < arrCodificacao.length; i++) {
            int posicaoChaveCriptografia = i % TAMANHO_CHAVE_CODIFICACAO;
            int codificacao = arrCodificacao[i] - arrChaveCriptografia[posicaoChaveCriptografia];

            if (codificacao < 0) {
                throw new Exception("Chave de criptografia inválida");
            }

            fraseDescriptografada += TABELA_CODIFICACAO.charAt(codificacao);
        }

        return fraseDescriptografada;
    }
}
