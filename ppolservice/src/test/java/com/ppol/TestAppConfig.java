/**
 * 
 */
package com.ppol;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ppol.AppConfig;

/**
 * @author rhogue
 *
 */
@Configuration
@EnableAutoConfiguration
//@EnableTransactionManagement
public class TestAppConfig extends AppConfig
{
//    @Override
//    public DataSource dataSource()
//    {
//        BasicDataSource dataSource = new BasicDataSource();
//        
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/gametest");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//        
//        return dataSource;
//    }
}
