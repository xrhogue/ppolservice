package com.ppol;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import com.ppol.common.bus.CommonService;
import com.ppol.common.bus.impl.CommonServiceImpl;
import com.ppol.common.dao.GenericDao;
import com.ppol.common.dao.impl.GenericDaoImpl;
import com.ppol.common.lib.model.Contact;
import com.ppol.controller.PPOLController;
import com.ppol.controller.ContactController;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig extends WebMvcAutoConfigurationAdapter implements ServletContextInitializer
{
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException
	{
		if (servletContext.getFilterRegistration("corsFilter") == null)
		{
			FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", SimpleCORSFilter.class);
		
			corsFilter.addMappingForUrlPatterns(null, false, "/*");
		}
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		 registry.addInterceptor(new CORSInterceptor());
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/gadget/**").addResourceLocations("classpath:/gadget/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
    }

    @Bean
    public PPOLController gameController()
    {
        return new PPOLController();
    }
    
    @Bean
    public ContactController contactController()
    {
        return new ContactController();
    }

    @Bean
    public CommonService commonService()
    {
        return new CommonServiceImpl();
    }

    @Bean
    public GenericDao<Long, Contact> contactDao()
    {
        return new GenericDaoImpl<Long, Contact>();
    }
    
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory entityManagerFactory)
    {
         HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
         
         factory.setEntityManagerFactory(entityManagerFactory);
         
         Properties props = new Properties();
         props.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
         props.put("hibernate.hbm2ddl.auto", "create");
         props.put("spring.jpa.database-platform", "org.hibernate.dialect.MySQLDialect");

         factory.setJpaProperties(props);
         factory.setPersistenceUnitName("test");
         
         return factory;
    }

    @Bean
    public DataSource dataSource()
    {
        BasicDataSource dataSource = new BasicDataSource();
        
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/ppol");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://107.20.211.48:3306/ppol");
        dataSource.setUsername("root");
        dataSource.setPassword("dlc37srv");
        
        return dataSource;
    }
}