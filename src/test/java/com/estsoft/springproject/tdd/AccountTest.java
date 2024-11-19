package com.estsoft.springproject.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

// TDD
// 1. 계좌 생성 (v)
// 2. 잔금 조회 (v)
// 3. 입/출금 (v)
public class AccountTest {
	@Test
	public void testAccount() {
		Account account = new Account(10000);
		assertThat(account.getBalance(), is(10000)); // hamcrest로 검증

		Account account2 = new Account(20000);
		assertThat(account2.getBalance(), is(20000));

		Account account3 = new Account(30000);
		assertThat(account3.getBalance(), is(30000));
	}

	@Test
	public void testDeposit() {
		Account account = new Account(10000);
		account.deposit(100000);
		assertThat(account.getBalance(), is(110000)); // 입금 기능 검증 완료
	}

	@Test
	public void testWithdraw() {
		Account account = new Account(10000);
		account.withdraw(10000);
		assertThat(account.getBalance(), is(0));

	}
}
