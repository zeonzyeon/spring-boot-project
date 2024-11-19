package com.estsoft.springproject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ComponentScanTest {
	// Test Code
	@Test
	@Disabled
	public void test() {
		// 스프링 컨테이너에 등록된 빈 확인을 위한 ApplicationContext 객체 생성
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		String[] beanNames = context.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}
