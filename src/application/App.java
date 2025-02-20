package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre os dados do contrato: ");

        System.out.print("Número: ");
        int number = sc.nextInt();

        System.out.print("Data (dd/MM/yyyy): ");
        LocalDate date = LocalDate.parse(sc.nextLine(), format);

        System.out.print("Valor do contrato: ");
        double value = sc.nextDouble();

        System.out.print("Entre com o numero de parcelas: ");
        int installmentNumber = sc.nextInt();

        sc.close();
    }
}
