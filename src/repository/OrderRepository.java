package repository;

import domain.Order;

import java.util.List;

public class OrderRepository {
    private final List<Order> orders;

    public OrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    public void save(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order is null");
        }
        if (orders.stream().anyMatch(orderItem -> orderItem.getId() == order.getId())) {
            throw new IllegalArgumentException("Order already exists");
        }
        this.orders.add(order);
    }

    public List<Order> findAll() {
        return List.copyOf(this.orders);
    }

    public Order findById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElse(null);
    }

    public int nextId() {
        return orders.stream().map(Order::getId).max(Integer::compareTo).orElse(0) + 1;
    }
}
