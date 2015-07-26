package concept1.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import concept1.web.domain.BeanA;
import concept1.web.domain.BeanB;
import concept1.web.domain.BeanC;

@Configuration
public class BaseConfiguration {

	@Bean
	public BeanA beanA() {
		BeanA beanA = new BeanA(beanB());
		
		BeanB beanB = beanB();
		BeanB beanB2 = beanB();
		System.out.println(beanB==beanB2 ? "Same":"Different");
		return beanA;
	}
	
	@Bean
	@Scope("prototype")
	public BeanB beanB() {
		BeanB beanB = new BeanB();
		beanB.setBeanC(beanC());
		return beanB;
	}

	@Bean
	public BeanC beanC() {
		BeanC beanC = new BeanC();
		beanC.setMessage("This is my message.");
		return beanC;
	}
}
