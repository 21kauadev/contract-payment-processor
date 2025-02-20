package model.services;

import model.interfaces.OnlinePaymentInterface;

public class PaypalService implements OnlinePaymentInterface {

    // retorna o valor da taxa de pagamento
    @Override
    public Double paymentFee(Double amount) {
        return amount * 0.02;
    }

    // retorna o valor dos juros
    @Override
    public Double interest(Double amount, Integer months) {
        return amount + (0.1 * months * amount);
    }
}
