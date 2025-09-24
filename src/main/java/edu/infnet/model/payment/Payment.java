package edu.infnet.model.payment;

public class Payment {
    private PaymentStatus paymentStatus;
    private String paymentMethod;
    private double amount;

    public void payOrder(){
        // Lógica para pagar o pedido
        if (paymentStatus.equals(PaymentStatus.UNPAID)) {
            paymentStatus = PaymentStatus.PAID;
            System.out.println("Pedido pago com sucesso!");
        } else {
            System.out.println("Pedido não pode ser pago. Status atual: " + paymentStatus);
        }

        System.out.println("Pagamento de " + amount + " via " + paymentMethod + " realizado com sucesso!");
    }
}
