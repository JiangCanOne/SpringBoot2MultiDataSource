package com.jc.springboot2multidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: 副数据源配置类
 * @author: 江灿
 * @date: 2023年04月24日 14:55
 */
@Configuration
@MapperScan(
        basePackages = "com.jc.springboot2multidatasource.mapper.SlaveMapper",
        sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

    // mapper.xml所在地址
    private static final String MAPPER_LOCATION = "classpath*:mapper/slave/*.xml";

    /**
     * 数据源
     */
    @Bean(name = "slaveDataSource")
    // 读取spring.datasource.slave前缀的配置文件映射成对应的配置对象
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource dataSource() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }


    /**
     * 事务管理器
     */
    @Bean(name = "slaveTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * session工厂
     */
    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sessionFactoryBean.getObject();
    }
}
