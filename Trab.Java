


package com.mycompany.estudos;

/**
 *
 * @author gabriel
 */
import java.util.Scanner;

public class SistemaContaBancaria {
    private static final int tamanhoVetor = 5;
    private static int totalContas = 0;
    private static final int[] numerosConta = new int[tamanhoVetor];
    private static final String[] nomesTitular = new String[tamanhoVetor];
    private static final double[] saldos = new double[tamanhoVetor];

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String opcao;
            do {
                System.out.println("===== Sistema de Conta Bancária =====");
                System.out.println("1. Criar conta bancária");
                System.out.println("2. Exibir informações da conta");
                System.out.println("3. Realizar depósito");
                System.out.println("4. Realizar saque");
                System.out.println("5. Calcular saldo total do banco");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextLine();
                
                switch (opcao) {
                    case "1" -> criarConta(scanner);
                    case "2" -> exibirInformacoesConta(scanner);
                    case "3" -> realizarDeposito(scanner);
                    case "4" -> realizarSaque(scanner);
                    case "5" -> {
                        double saldoTotal = calcularSaldoTotal();
                        System.out.println("Saldo total do banco: " + saldoTotal);
                    }
                    case "0" -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
                
                System.out.println();
            } while (!opcao.equals("0"));
        }
    }

    public static void criarConta(Scanner scanner) {
        if (totalContas < tamanhoVetor) {
            System.out.print("Digite o número da conta: ");
            int numeroConta = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite o nome do titular: ");
            String nomeTitular = scanner.nextLine();

            System.out.print("Digite o saldo inicial: ");
            double saldo = scanner.nextDouble();
            scanner.nextLine();

            numerosConta[totalContas] = numeroConta;
            nomesTitular[totalContas] = nomeTitular;
            saldos[totalContas] = saldo;

            totalContas++;

            System.out.println("Conta bancária criada com sucesso.");
        } else {
            System.out.println("Não é possível criar mais contas. Limite atingido.");
        }
    }

    public static void exibirInformacoesConta(Scanner scanner) {
        if (totalContas > 0) {
            System.out.print("Digite o número da conta: ");
            int numeroConta = scanner.nextInt();
            scanner.nextLine();

            int indiceConta = encontrarConta(numeroConta);

            if (indiceConta != -1) {
                System.out.println("Número da conta: " + numerosConta[indiceConta]);
                System.out.println("Nome do titular: " + nomesTitular[indiceConta]);
                System.out.println("Saldo: " + saldos[indiceConta]);
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Não há contas cadastradas.");
        }
    }

    public static void realizarDeposito(Scanner scanner) {
        if (totalContas > 0) {
            System.out.print("Digite o número da conta: ");
            int numeroConta = scanner.nextInt();
            scanner.nextLine();

            int indiceConta = encontrarConta(numeroConta);

            if (indiceConta != -1) {
                System.out.print("Digite o valor do depósito: ");
                double valorDeposito = scanner.nextDouble();
                scanner.nextLine();

                saldos[indiceConta] += valorDeposito;

                System.out.println("Depósito realizado com sucesso.");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Não há contas cadastradas.");
        }
    }

    public static void realizarSaque(Scanner scanner) {
        if (totalContas > 0) {
            System.out.print("Digite o número da conta: ");
            int numeroConta = scanner.nextInt();
            scanner.nextLine();

            int indiceConta = encontrarConta(numeroConta);

            if (indiceConta != -1) {
                System.out.print("Digite o valor do saque: ");
                double valorSaque = scanner.nextDouble();
                scanner.nextLine();

                if (valorSaque <= saldos[indiceConta]) {
                    saldos[indiceConta] -= valorSaque;
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Não há contas cadastradas.");
        }
    }

    public static double calcularSaldoTotal() {
        return calcularSaldoTotalRecursivo(0, 0);
    }

    private static double calcularSaldoTotalRecursivo(double saldoTotal, int index) {
        if (index == totalContas) {
            return saldoTotal;
        } else {
            saldoTotal += saldos[index];
            return calcularSaldoTotalRecursivo(saldoTotal, index + 1);
        }
    }

    public static int encontrarConta(int numeroConta) {
        for (int i = 0; i < totalContas; i++) {
            if (numerosConta[i] == numeroConta) {
                return i;
            }
        }
        return -1;
    }
}
