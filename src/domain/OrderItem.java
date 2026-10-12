package domain;

public class OrderItem {
    private final Product product;
    private final double quantity;
    private final double unitPricePurchase;

    public OrderItem(Product product, double quantity, double unitPricePurchase) {
        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity cannot be <= 0");
        }
        if (unitPricePurchase < 0) {
            throw new IllegalArgumentException("unitPricePurchase cannot be < 0");
        }
        this.unitPricePurchase = unitPricePurchase;
        this.quantity = quantity;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitPricePurchase() {
        return unitPricePurchase;
    }

    public double getSubtotal() {
        return unitPricePurchase * quantity;
    }
}
