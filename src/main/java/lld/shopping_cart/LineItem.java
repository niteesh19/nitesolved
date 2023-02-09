package lld.shopping_cart;

import java.math.BigDecimal;
import java.util.Objects;

public class LineItem {
  private final String itemId;
  private int quantity;
  private BigDecimal price;
  private String name;

  public LineItem(String itemId, int quantity) {
    this.itemId = itemId;
    this.quantity = quantity;
  }

  public LineItem(String itemId) {
    this(itemId, 1);
  }

  public String getItemId() {
    return itemId;
  }

  public int getQuantity() {
    return quantity;
  }

  /* Mutating Line Item */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public void setName(String name) {
    this.name = name;
  }


  /**
   * Required for removing item by equals method
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LineItem lineItem = (LineItem) o;
    return quantity == lineItem.quantity &&
        Objects.equals(itemId, lineItem.itemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, quantity);
  }

  /* Object manipulating its own state rather than other objects try to do the same */
  public void reduceByQuantity(int quantityToReduce) {
    this.quantity = this.quantity - quantityToReduce;
  }

  public BigDecimal totalPrice() {
    return price.multiply(BigDecimal.valueOf(quantity));
  }

  public void increaseQtyBy(int quantityToAdd) {
    this.quantity = this.quantity + quantityToAdd;
  }
}
