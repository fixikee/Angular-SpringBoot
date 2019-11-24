package com.fixer.sampleproject.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("com.fixer.sampleproject.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
