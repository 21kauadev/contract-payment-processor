package model.interfaces;

public interface OnlinePaymentInterface {

    Double paymentFee(Double amount);

    Double interest(Double amount, Integer months);
}
