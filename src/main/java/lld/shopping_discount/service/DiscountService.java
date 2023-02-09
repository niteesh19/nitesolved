package lld.shopping_discount.service;

import lld.shopping_discount.exceptions.InvalidDiscountCouponException;
import lld.shopping_discount.model.DiscountCoupon;

public interface DiscountService {

	DiscountCoupon find(String couponCode) throws InvalidDiscountCouponException;

	String create(DiscountCoupon discountCoupon);


}
