package com.chenyan.utils;

import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;

/**
* <p>Title: JdbcTemplateUtils</p>  
* @author chenyan  
* @date 2019年7月23日
 */
public class JdbcTemplateUtils {

	private static JdbcTemplate jdbcTemplate ;
	
	public static JdbcTemplate jdbcTemplate() {
		if(jdbcTemplate == null) {
			jdbcTemplate = createJdbcTemplate() ;
		}
		return jdbcTemplate ;
	}

	private static JdbcTemplate createJdbcTemplate() {

		DruidDataSource ds = new DruidDataSource() ;
		ds.setDriverClassName("com.mysql.jdbc.Driver") ;
		ds.setUrl("jdbc:mysql://localhost:3306/shirodemo") ;
		ds.setUsername("root") ;
		ds.setPassword("root") ;
		
		return new JdbcTemplate(ds) ;
	}
}
