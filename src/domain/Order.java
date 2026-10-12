package domain;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Order {
    private final int id;
    private final Customer customer;
    private final List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private final LocalDateTime orderDate;

    public Order(int id, Customer customer, List<OrderItem> orderItems, OrderStatus orderStatus) {
        this.id = id;
        this.customer = customer;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.orderDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void changeStatus(OrderStatus newOrderStatus) {
        if (newOrderStatus == null) {
            throw new IllegalArgumentException("Order status cannot be null");
        }
        if (this.orderStatus == newOrderStatus) {
            return;
        }
        if (this.orderStatus.ordinal() + 1 == newOrderStatus.ordinal()) {
            this.orderStatus = newOrderStatus;
        } else throw new IllegalArgumentException("Status deyisikliyi ardicilligi pozulub");
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotal() {
        return orderItems.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }
}
