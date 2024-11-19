package com.estsoft.springproject.coupon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.estsoft.springproject.user.coupon.DummyCoupon;
import com.estsoft.springproject.user.coupon.ICoupon;
import com.estsoft.springproject.user.coupon.User;

public class UserCouponTest {
	@Test
	public void testAddCoupon() {
		User user = new User("area00");
		assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

		ICoupon coupon = new DummyCoupon();    // Dummy 쿠폰을 만들어서 사용

		user.addCoupon(coupon);
		assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
	}

	@Test
	public void 쿠폰이_유효할_경우에만_발급한다() {
		User user = new User("area00");
		assertEquals(0, user.getTotalCouponCount()); // 쿠폰 수령 전

		ICoupon coupon = Mockito.mock(ICoupon.class); 	// Mock 객체 - 행위 정의 가능
		// Mockito.when(coupon.isValid()).thenReturn(true); // stud
		Mockito.doReturn(true).when(coupon).isValid(); // stud
		// BDDMockito.given(coupon.isValid())
		// 		.willReturn(true);

		user.addCoupon(coupon);
		assertEquals(1, user.getTotalCouponCount()); // 쿠폰 수령 후 쿠폰수 검증
	}

	@Test
	public void 쿠폰이_유효하지_않을경우_발급안된다() {
		User user = new User("area00");
		assertEquals(0, user.getTotalCouponCount());

		ICoupon coupon = Mockito.mock(ICoupon.class);
		Mockito.when(coupon.isValid()).thenReturn(false);

		user.addCoupon(coupon);
		assertEquals(0, user.getTotalCouponCount());
	}
}
