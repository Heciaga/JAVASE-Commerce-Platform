package domain;

import java.util.ArrayList;

public class Customer extends User {
    private Cart cart;

    public Customer(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.cart = new Cart(new ArrayList<>());
    }

    @Override
    public Cart getCart() {
        return cart;
    }
}
