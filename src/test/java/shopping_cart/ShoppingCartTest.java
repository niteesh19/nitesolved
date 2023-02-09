package shopping_cart;

import lld.shopping_cart.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartTest {

  private Inventory inventory;
  private ShoppingCart cart;

  @BeforeEach
  public void setUp() {
    //Encapsulated Item inside inventory
    Item item1 = new Item("item-1", "Effective Java", ItemType.BOOK, BigDecimal.valueOf(2000));
    Item item2 = new Item("item-2", "Ball Point Pen", ItemType.PEN, BigDecimal.valueOf(20));
    inventory = new Inventory();
    inventory.add(item1);
    inventory.add(item2);
    //Dependency injection; injecting inventory inside shopping cart
    cart = new ShoppingCart(inventory);
  }

  /**
   * Behaviour driven test cases
   * not unit tests
   */
  @Test
  public void should_add_an_item_to_a_cart() {
    cart.addItem(new LineItem("item-1"));
    assertEquals(1, cart.totalNumberOfItems());
  }

  @Test
  public void should_add_multiple_items_to_the_cart() {
    cart.addItem(new LineItem("item-1"));
    cart.addItem(new LineItem("item-2"));
    assertEquals(2, cart.totalNumberOfItems());
  }

  @Test
  public void should_add_multiple_quantity_of_same_item_to_the_cart() {
    cart.addItem(new LineItem("item-1", 3));
    assertEquals(3, cart.totalNumberOfItems());
  }

  @Test
  public void should_remove_an_item_from_cart() {
    cart.addItem(new LineItem("item-1"));
    cart.addItem(new LineItem("item-2"));

    cart.remove(new LineItem("item-1"));
    assertEquals(1, cart.totalNumberOfItems());
  }

  @Test
  public void should_remove_specific_quantity_of_item_from_cart() {
    cart.addItem(new LineItem("item-1", 4));
    cart.addItem(new LineItem("item-2", 3));

    cart.remove(new LineItem("item-1", 2));
    cart.remove(new LineItem("item-2", 1));
    assertEquals(4, cart.totalNumberOfItems());
  }

  @Test
  public void should_view_list_of_item_in_cart() {
    cart.addItem(new LineItem("item-1", 4));
    cart.addItem(new LineItem("item-2", 3));

    List<LineItem> lineItems =  cart.listItemsInCart();
    assertEquals(lineItems.get(0).totalPrice(), BigDecimal.valueOf(8000));
    assertEquals(lineItems.get(1).totalPrice(), BigDecimal.valueOf(60));
    assertEquals(7, cart.totalNumberOfItems());
  }

  @Test
  public void should_inc_quantity_of_item_when_same_added_in_cart() {
    cart.addItem(new LineItem("item-1", 4));
    cart.addItem(new LineItem("item-1", 3));

    cart.remove(new LineItem("item-1", 6));
    assertEquals(1, cart.totalNumberOfItems());
  }

}
