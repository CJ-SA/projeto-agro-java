
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double[] precipitacao = new double[7];

        String[] nomesDias = {
                "Segunda-feira", "Terça-feira", "Quarta-feira",
                "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"
        };

        double[][] umidadeCampo = new double[4][4];

        boolean dadosSalvos = false;
        int escolha;

        do {
            System.out.println("AGROJAVA - Sistema Integrado de Agronegócio");
            System.out.println();
            System.out.print("Seja bem-vindo(a) ao AGROJAVA, leia as opções e em seguida digite a opção: ");
            System.out.println("Digite 1 - Para cadastrar dados (Chuvas e Umidade)");
            System.out.println("Digite 2 - Para exibir mapa do campo e relatório de chuvas");
            System.out.println("Digite 3 - Para relatório de alertas de irrigação");
            System.out.println("Digite 4 - Para sair");
            System.out.println("Escolha uma opção: ");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("CADASTRO DE PLUVIOSIDADE (7 dias)");

                    for (int i = 0; i < 7; i++) {
                        System.out.print("Volume de chuva em " + nomesDias[i] + " (mm): ");
                        precipitacao[i] = scanner.nextDouble();
                    }

                    System.out.println();
                    System.out.println("CADASTRO DE UMIDADE DOS TALHÕES");

                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            System.out.print("Informe a umidade do talhão [" + (i + 1) + "][" + (j + 1) + "] (%): ");
                            umidadeCampo[i][j] = scanner.nextDouble();
                        }
                    }

                    dadosSalvos = true;
                    System.out.println("Dados cadastrados com sucesso!");
                    System.out.println();
                    break;

                case 2:
                    if (!dadosSalvos) {
                        System.out.println("Cadastre os dados primeiro selecionando a opção 1.");
                        break;
                    }

                    double totalChuva = 0;
                    double maiorPrecipitacao = precipitacao[0];
                    int indiceMaiorChuva = 0;

                    for (int i = 0; i < 7; i++) {
                        totalChuva += precipitacao[i];

                        if (precipitacao[i] > maiorPrecipitacao) {
                            maiorPrecipitacao = precipitacao[i];
                            indiceMaiorChuva = i;
                        }
                    }

                    double mediaPrecipitacao = totalChuva / 7;

                    System.out.println("RELATÓRIO DE PLUVIOSIDADE");
                    System.out.printf("Média semanal de precipitação: %.2f mm ", mediaPrecipitacao);
                    System.out.println("Dia com maior índice de chuva: "
                            + nomesDias[indiceMaiorChuva] + " (" + maiorPrecipitacao + " mm)");

                    System.out.println("MAPA DE UMIDADE DO CAMPO");
                    System.out.println("            Col 1 Col 2 Col 3 Col 4");

                    for (int i = 0; i < 4; i++) {
                        System.out.print("Talhão Linha " + (i + 1));

                        for (int j = 0; j < 4; j++) {
                            System.out.printf(" %.1f", umidadeCampo[i][j]);
                        }

                        System.out.println();
                    }
                    break;

                case 3:
                    if (!dadosSalvos) {
                        System.out.println("Cadastre os dados primeiro selecionando a opção 1.");
                        break;
                    }

                    System.out.println("RELATÓRIO DE ALERTAS DE IRRIGAÇÃO");
                    boolean temAlerta = false;

                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {

                            if (umidadeCampo[i][j] < 30.0) {
                                System.out.printf(
                                        "ALERTA: O talhão [%d][%d] está com %.1f%% de umidade e necessita de irrigação imediata!\n",
                                        (i + 1), (j + 1), umidadeCampo[i][j]
                                );

                                temAlerta = true;
                            }
                        }
                    }

                    if (!temAlerta) {
                        System.out.println(
                                "Todos os talhões estão com umidade adequada (>= 30%). "
                                        + "Nenhuma irrigação necessária no momento."
                        );
                    }
                    break;

                case 4:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha um número entre 1 e 4.");
            }

        } while (escolha != 4);

        scanner.close();
    }
}

