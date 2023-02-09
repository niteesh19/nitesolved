package lld.shopping_discount.exceptions;

public class ExpiredDisountCouponException extends RuntimeException {

	private static final long serialVersionUID = 7550506456754210191L;

	public ExpiredDisountCouponException() {
		super("Sorry, the coupon code has expired.");
	}

}
