package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.exceptions.MissingContractException;
import model.interfaces.OnlinePaymentInterface;
import model.services.ContractService;
import model.services.PaypalService;

public class App {
    public static void main(String[] args) throws Exception {

        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Scanner sc = new Scanner(System.in);

            System.out.println("Entre os dados do contrato: ");

            System.out.print("Número: ");
            int number = sc.nextInt();

            System.out.print("Data (dd/MM/yyyy): ");
            sc.nextLine();
            LocalDate date = LocalDate.parse(sc.nextLine(), format);

            System.out.print("Valor do contrato: ");
            double totalValue = sc.nextDouble();

            System.out.print("Entre com o numero de parcelas: ");
            int installmentNumber = sc.nextInt();

            Contract contract = new Contract(number, date, totalValue);

            OnlinePaymentInterface paypalService = new PaypalService();

            ContractService contractService = new ContractService(paypalService);

            contractService.processContract(contract, installmentNumber);
            // installmentNumber seria os meses totais, o numero de parcelas.

            System.out.println();
            System.out.println("Parcelas: ");

            for (Installment installment : contract.getInstallments()) {
                System.out.println(installment);
            }
            sc.close();

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid data type. Enter the expected types");
        } catch (MissingContractException e) {
            System.out.println(e.getMessage());
        }

    }
}
