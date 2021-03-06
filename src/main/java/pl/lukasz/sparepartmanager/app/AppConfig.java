package pl.lukasz.sparepartmanager.app;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import pl.lukasz.sparepartmanager.converter.LocationConverter;
import pl.lukasz.sparepartmanager.converter.ManufacturerConverter;
import pl.lukasz.sparepartmanager.converter.PartCatalogConverter;

@Configuration
@ComponentScan(basePackages = { "pl.lukasz.sparepartmanager.controller",
								"pl.lukasz.sparepartmanager.entity",
								"pl.lukasz.sparepartmanager.bean"})
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"pl.lukasz.sparepartmanager.repository"})
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/spare_part_manager");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("coderslab");
	    return driverManagerDataSource;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("spare_part_manager");
		return emfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}
	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(locationConverter());
		registry.addConverter(manufacturerConverter());
		registry.addConverter(partCatalogConverter());
	}

	@Bean
	public ManufacturerConverter manufacturerConverter() {
		return new ManufacturerConverter();
	}
	
	@Bean
	public LocationConverter locationConverter() {
		return new LocationConverter();
	}
	
	@Bean
	public PartCatalogConverter partCatalogConverter() {
		return new PartCatalogConverter();
	}

}
