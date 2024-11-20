package com.estsoft.springproject.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.estsoft.springproject.user.domain.Users;
import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@InjectMocks
	UserService service;

	@Mock
	UserRepository userRepository;

	@Spy
	BCryptPasswordEncoder encoder;

	@Test
	public void testSave() {
		// given
		String email = "test@test.com";
		String rawpassword = "test";
		String encodedPassword = "encodedPassword";

		AddUserRequest request = new AddUserRequest();
		request.setEmail(email);
		request.setPassword(rawpassword);

		doReturn(encodedPassword).when(encoder).encode(rawpassword);
		Mockito.when(userRepository.save(any(Users.class)))
			.thenReturn(new Users(email, encodedPassword));

		// when
		Users returnUser = service.save(request);

		// then
		assertEquals(email, returnUser.getEmail());
		assertEquals(encodedPassword, returnUser.getPassword());

		verify(userRepository, times(1)).save(any(Users.class));
		verify(encoder, times(1)).encode(rawpassword);
	}
}
