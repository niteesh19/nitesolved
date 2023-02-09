package lld.shopping_discount.cart;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lld.shopping_discount.exceptions.BookNotInInventoryException;
import lld.shopping_discount.exceptions.EmptyShoppingCartException;
import lld.shopping_discount.exceptions.ExpiredDisountCouponException;
import lld.shopping_discount.exceptions.NotEnoughBooksInInventoryException;
import lld.shopping_discount.model.DiscountCoupon;
import lld.shopping_discount.model.EmptyDiscountCoupon;
import lld.shopping_discount.service.DiscountService;
import lld.shopping_discount.service.Inventory;

public class ShoppingCart {

	private final Inventory inventory;
	private final DiscountService discountService;

	private Map<String, Integer> itemsInCart = new LinkedHashMap<>();

	public ShoppingCart(Inventory inventory, DiscountService discountService) {
		this.inventory = inventory;
		this.discountService = discountService;
	}

	public void add(String... books) throws BookNotInInventoryException {
		Arrays.stream(books).forEach(book -> add(book, 1));
	}

	public void add(String title, int quantity) throws BookNotInInventoryException, NotEnoughBooksInInventoryException {
		if (!inventory.exists(title)) {
			throw new BookNotInInventoryException(title);
		}
		if (!inventory.hasEnoughCopies(title, quantity)) {
			throw new NotEnoughBooksInInventoryException(title);
		}
		itemsInCart.put(title, itemsInCart.compute(title, (k, v) -> (k == null) ? quantity : (v == null ? 0 : v) + quantity));
	}

	public int size() {
		return itemsInCart.values().stream().reduce(0, (total, quantity) -> total + quantity);
	}

	public List<ShoppingCartItem> items() {
		List<ShoppingCartItem> items = itemsInCart.entrySet().stream().map(entry -> new ShoppingCartItem(inventory.find(entry.getKey()),entry.getValue())).collect(toList());
		return Collections.unmodifiableList(items);
	}

	public int checkout() throws EmptyShoppingCartException {
		return _checkout(new EmptyDiscountCoupon());
	}

	public int checkout(final String couponCode) throws EmptyShoppingCartException, ExpiredDisountCouponException {
		DiscountCoupon discountCoupon = discountService.find(couponCode);
		if (discountCoupon.isExpired()) {
			throw new ExpiredDisountCouponException();
		}
		return _checkout(discountCoupon);
	}

	private int _checkout(DiscountCoupon discountCoupon) throws EmptyShoppingCartException {
		if (itemsInCart.isEmpty()) {
			throw new EmptyShoppingCartException();
		}
		return new CheckoutAmountCalculator(items(), discountCoupon).checkoutAmount();
	}

	@Override
	public String toString() {
		return itemsInCart.toString();
	}

}
