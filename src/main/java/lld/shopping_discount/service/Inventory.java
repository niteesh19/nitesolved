package lld.shopping_discount.service;

import lld.shopping_discount.model.Book;

public interface Inventory {

	boolean exists(String title);

	String[] add(Book... books);

	String add(Book book);

	boolean hasEnoughCopies(String title, int quantity);

	Book find(String title);

}
