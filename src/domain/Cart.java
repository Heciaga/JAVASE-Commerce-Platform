package domain;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Cart {
    private final List<CartItem> items;

    public Cart(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        for (CartItem currentItem : this.items) {
            if (currentItem.getProduct().getId() == item.getProduct().getId()) {
                currentItem.addQuantity(item.getQuantity());
                return;
            }
        }
        this.items.add(item);
    }

    public void removeItem(Product product) {
        Iterator<CartItem> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            CartItem currentItem = iterator.next();
            if (currentItem.getProduct().getId() == product.getId()) {
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }

    public void decreaseQuantity(Product product, double quantity) {
        Iterator<CartItem> iterator = this.items.iterator();
        while (iterator.hasNext()) {
            CartItem currentItem = iterator.next();
            if (currentItem.getProduct().getId() == product.getId()) {
                currentItem.subtractQuantity(quantity);
                if (currentItem.getQuantity() <= 0) {
                    iterator.remove();
                }
                return;
            }

        }
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public void clear() {
        this.items.clear();
    }
}
