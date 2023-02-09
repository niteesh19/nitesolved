package lld.shopping_discount.exceptions;

public class InvalidDiscountCouponException extends RuntimeException{

	private static final long serialVersionUID = 5396109177206576846L;
	
	public InvalidDiscountCouponException(String message) {
		super(message);
	}

}
