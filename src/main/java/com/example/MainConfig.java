package com.example;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

	@Bean
	public BasicDataSource dataSource() throws URISyntaxException {

		URI dbUri = null;
		BasicDataSource basicDataSource = new BasicDataSource();

		if (System.getenv("DATABASE_URL") != null) {
			dbUri = new URI(System.getenv("DATABASE_URL"));
			String username = dbUri.getUserInfo().split(":")[0];
			String password = dbUri.getUserInfo().split(":")[1];
			String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			basicDataSource.setUrl(dbUrl);
			basicDataSource.setUsername(username);
			basicDataSource.setPassword(password);
		} else {
			basicDataSource.setUrl("jdbc:h2:mem:test");
		}

		return basicDataSource;
	}
}
