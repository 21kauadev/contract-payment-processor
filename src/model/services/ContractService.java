package model.services;

import model.entities.Contract;
import model.entities.Installment;
import model.interfaces.OnlinePaymentInterface;

public class ContractService {

    private OnlinePaymentInterface paymentService;

    // construtor
    public ContractService(OnlinePaymentInterface paymentService) {
        this.paymentService = paymentService;
    }

    public void processContract(Contract contract, Integer months) {

        // meses que serão adicionados a parcela.
        // começa com 1 e vou incrementando a cada parcela, já que os meses entram no
        // cálculo dos juros.
        int monthsToBeAdded = 1;

        // months -> meses totais que serão parcelados
        // aqui calculo o preço sem os juros aplicados, só o preço puro
        // ex: 600 (total) parcelado por 3 meses totais = 600 / 3 = 200.
        double contractPricePerInstallment = contract.getTotalValue() / months;

        for (int i = 0; i < months; i++) {

            // retorna o valor puro dos juros
            double interest = this.paymentService.interest(contractPricePerInstallment, monthsToBeAdded);

            // soma ao valor total
            double priceWithInterest = contractPricePerInstallment + interest;

            // retorna a taxa de pagamento para o valor com juros já aplicados
            double paymentFee = this.paymentService.paymentFee(priceWithInterest);

            // soma a taxa de pagamento com o preço com juros já aplicados, retornando o
            // preço com todos os juros aplicados
            double priceWithInterestApplied = contractPricePerInstallment + paymentFee;

            // instancia uma nova parcela, passando o mês em que será pago a parcela e o seu
            // valor.
            new Installment(contract.getDate().plusMonths(monthsToBeAdded), priceWithInterestApplied);
        }

    }
}
