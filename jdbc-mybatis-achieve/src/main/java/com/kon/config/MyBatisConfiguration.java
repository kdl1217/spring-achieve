package com.kon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jdbc.repository.config.MyBatisJdbcConfiguration;

/**
 * MyBatis Configuration
 *
 * @author kon, created on 2021/12/3T11:34.
 * @version 1.0.0-SNAPSHOT
 */
@Configuration
@EnableJdbcRepositories(basePackages = "com.kon.repository")
@Import(MyBatisJdbcConfiguration.class)
public class MyBatisConfiguration extends AbstractJdbcConfiguration {

//    @Autowired
//    private SqlSession sqlSession;
//
//    @Bean
//    @Override
//    public DataAccessStrategy dataAccessStrategyBean(NamedParameterJdbcOperations operations, JdbcConverter jdbcConverter,
//                                                     JdbcMappingContext context, Dialect dialect) {
//        return MyBatisDataAccessStrategy.createCombinedAccessStrategy(context,  jdbcConverter, operations, sqlSession,
//                new MybatisNamespaceStrategy(), dialect);
//    }
}
