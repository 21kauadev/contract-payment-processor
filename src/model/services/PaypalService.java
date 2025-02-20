package model.services;

import model.interfaces.OnlinePaymentInterface;

public class PaypalService implements OnlinePaymentInterface {

    public PaypalService() {
    }

    // retorna o valor da taxa de pagamento
    @Override
    public Double paymentFee(Double amount) {
        return amount * 0.02;
    }

    // retorna o valor dos juros
    @Override
    public Double interest(Double amount, Integer months) {
        double result = amount + (0.01 * months * amount);

        return result - amount;
    }
}
