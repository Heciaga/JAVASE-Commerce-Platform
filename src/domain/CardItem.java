package domain;

public class CardItem {
    public Product product;
    public double quantity;
    public CardItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
