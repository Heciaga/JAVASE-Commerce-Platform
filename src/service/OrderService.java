package service;

import domain.*;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void checkout(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null");
        }
        if (customer.getCart().getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is null");
        }
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : customer.getCart().getItems()) {
            orderItems.add(new OrderItem(cartItem.getProduct()
                    , cartItem.getQuantity()
                    , cartItem.getProduct().getPrice()));
        }

        orderRepository.save(new Order(orderRepository.nextId(), customer, orderItems, OrderStatus.PENDING));
        customer.getCart().clear();

    }
}
