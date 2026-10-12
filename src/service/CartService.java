package service;

import domain.CartItem;
import domain.Customer;
import domain.Product;

public class CartService {
    public void addProductToCart(Customer customer, Product product, double quantity) {
        customer.getCart().addItem(new CartItem(product, quantity));
    }

    public void removeProductFromCart(Customer customer, Product product) {
        customer.getCart().removeItem(product);
    }

    public void decreaseQuantity(Customer customer, Product product, double quantity) {
        customer.getCart().decreaseQuantity(product, quantity);
    }

    public double getCartTotal(Customer customer) {
        return customer.getCart().getTotal();
    }

}
