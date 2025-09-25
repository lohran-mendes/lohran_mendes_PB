package edu.infnet.model.order;

import edu.infnet.model.address.Address;
import edu.infnet.model.payment.Payment;
import edu.infnet.model.payment.PaymentStatus;

import java.time.LocalDateTime;

public class Order {
    private final String ORDER_ID;
    private LocalDateTime orderDate;
    private Address deliveryAddress;
    private OrderDetails orderDetails;
    // devo alterar para que só tenho a classe de pagamento e tire o status de pagamento daqui
    private PaymentStatus paymentStatus;
    private Payment payment;

    public Order() {
        this.ORDER_ID = java.util.UUID.randomUUID().toString();
    }

    public String getORDER_ID() {
        return ORDER_ID;
    }

    public void consultOrderStatus() {
        // Lógica para consultar o status do pedido
        System.out.println("Status do pedido " + ORDER_ID + ": " + paymentStatus);
    }

    public void updateDeliveryAddress(Address newAddress) {
        // Lógica para atualizar o endereço de entrega
        this.deliveryAddress = newAddress;
        System.out.println("Endereço de entrega do pedido " + ORDER_ID + " atualizado com sucesso!");
    }

    public void payOrder() {
        // Lógica para pagar o pedido
        if (paymentStatus.equals(PaymentStatus.UNPAID)) {
            paymentStatus = PaymentStatus.PAID;
            System.out.println("Pedido " + ORDER_ID + " pago com sucesso!");
        } else {
            System.out.println("Pedido " + ORDER_ID + " não pode ser pago. Status atual: " + paymentStatus);
        }
    }
}
