package concept1.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import concept1.web.domain.BeanA;
import concept1.web.security.BaseConfiguration;

@SpringBootApplication
public class Concept1Application extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	public static void main(String[] args) {
        SpringApplication.run(Concept1Application.class, args);
        
        ApplicationContext context = new AnnotationConfigApplicationContext(BaseConfiguration.class);
        BeanA beanA = context.getBean("beanA", BeanA.class);
        beanA.execute();
    }
}
