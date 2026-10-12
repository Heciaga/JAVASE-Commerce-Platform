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

    public Order checkout(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null");
        }
        if (customer.getCart() == null) {
            throw new IllegalArgumentException("Customer cart is null");
        }
        if (customer.getCart().getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : customer.getCart().getItems()) {
            orderItems.add(new OrderItem(cartItem.getProduct()
                    , cartItem.getQuantity()
                    , cartItem.getProduct().getPrice()));
        }
        Order order = new Order(orderRepository.nextId(), customer, orderItems, OrderStatus.PENDING);

        orderRepository.save(order);
        customer.getCart().clear();
        return order;
    }

    public List<Order> getCustomerOrders(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer is null");
        }

    }
}
