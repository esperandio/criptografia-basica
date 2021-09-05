import java.util.Scanner;

public class Main {
    static final String OPCAO_CRIPTOGRAFAR = "1";
    static final String OPCAO_DESCRIPTOGRAFAR = "2";
    static final String OPCAO_SAIR = "3";

    private static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in).useDelimiter("\n");

        mostrarMenuOpcoes();
    }

    private static void mostrarMenuOpcoes() {
        String opcao = "";

        while (!opcao.equals(OPCAO_SAIR)) {
            System.out.println("1 - Criptografar");
            System.out.println("2 - Descriptografar");
            System.out.println("3 - Sair");
            System.out.println("");
            System.out.print("Opção: ");
            opcao = input.next();
    
            switch (opcao) {
                case OPCAO_CRIPTOGRAFAR:
                    mostrarCriptografar();
                    break;
                case OPCAO_DESCRIPTOGRAFAR:
                    mostrarDescriptografar();
                    break;
                case OPCAO_SAIR:
                    System.out.println("\nSaindo...\n");
                    break;
                default:
                    System.out.println("\nOpção inválida!\n");
                    break;
            }   
        }
    }

    private static void mostrarCriptografar() {
        System.out.print("\nDigite a frase que deve ser criptografada: ");
        String fraseOriginal = input.next();

        Criptografia c = new Criptografia();

        try {
            String retorno = c.criptografar(fraseOriginal);

            System.out.printf(
                "\n%s\n\n",
                retorno
            );
        } catch (Exception e) {
            System.out.printf(
                "\nO seguinte erro foi detectado: %s.\n\n",
                e.getMessage()
            );
        }
    }

    private static void mostrarDescriptografar() {
        System.out.print("\nDigite a frase criptografada: ");
        String fraseCriptografada = input.next();

        System.out.print("Digite a chave de criptografia: ");
        String chaveCriptografia = input.next();

        Criptografia c = new Criptografia();

        try {
            String retorno = c.descriptografar(fraseCriptografada, chaveCriptografia);

            System.out.printf(
                "\n%s\n\n",
                retorno
            );
        } catch (Exception e) {
            System.out.printf(
                "\nO seguinte erro foi detectado: %s.\n\n",
                e.getMessage()
            );
        }
    }
}