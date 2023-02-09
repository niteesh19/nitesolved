package lld.shopping_cart;

import java.util.*;

public class ShoppingCart {
  private final Inventory inventory;
  /* Preserving the insertion order of items */
  private final Map<String, LineItem> itemsInCart = new LinkedHashMap<>();

  public ShoppingCart(Inventory inventory) {
    this.inventory = inventory;
  }

  public void addItem(LineItem lineItem) {
    /* Mutating Line Item */
    if(itemsInCart.containsKey(lineItem.getItemId())){
      LineItem lineItemInCart = itemsInCart.get(lineItem.getItemId());
      lineItemInCart.increaseQtyBy(lineItem.getQuantity());
    } else {
      Item item = inventory.get(lineItem.getItemId());
      lineItem.setName(item.getName());
      lineItem.setPrice(item.getPrice());

      this.itemsInCart.put(lineItem.getItemId(), lineItem);
    }
  }

  public int totalNumberOfItems() {
    int totalItems = 0;
    for (LineItem lineItem: itemsInCart.values()) {
      totalItems += lineItem.getQuantity();
    }
    return totalItems;
  }

  public void remove(LineItem lineItemToRemove) {
    boolean completelyRemove = false;
    for (LineItem itemInCart: itemsInCart.values()) {
      if (itemInCart.getItemId().equals(lineItemToRemove.getItemId())) {
        if (lineItemToRemove.getQuantity() == itemInCart.getQuantity()) {
          completelyRemove = true;
        } else {
          itemInCart.reduceByQuantity(lineItemToRemove.getQuantity());
        }
      }
    }
    if (completelyRemove)
      this.itemsInCart.remove(lineItemToRemove.getItemId());
  }

  /* Return a list which cannot be modified by the consumer */
  public List<LineItem> listItemsInCart() {
    return Collections.unmodifiableList(new ArrayList<>(this.itemsInCart.values()));
  }
}
