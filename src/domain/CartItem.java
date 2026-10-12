package domain;

public class CartItem {
    private Product product;
    private double quantity;

    public CartItem(Product product, double quantity) {
        if (product == null) {
            throw new IllegalArgumentException("product is null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }


    public void addQuantity(double quantity) {
        if (quantity == 0) throw new IllegalArgumentException("Quantity must be greater than zero");
        this.quantity += quantity;
    }

    public void subtractQuantity(double quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than zero");
        if (quantity > this.quantity) throw new IllegalArgumentException("Not enough quantity");
        this.quantity -= quantity;
    }

    public double getSubtotal() {
        return this.product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    //exception lardaki mesajlari bir constant classda saxla
}
