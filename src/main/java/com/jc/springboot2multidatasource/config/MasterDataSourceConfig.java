package com.jc.springboot2multidatasource.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: 主数据源配置类
 * @author: 江灿
 * @date: 2023年04月24日 14:55
 */
@Configuration
@MapperScan(
        basePackages = "com.jc.springboot2multidatasource.mapper.MasterMapper",
        sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    //mapper.xml所在位置
    private static final String Mapper_location = "classpath*:mapper/master/*.xml";


    /**
     * 配置数据源
     * @return
     */
    @Primary //表示该数据源为默认数据源
    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master") // 读取spring.datasource.master前缀的配置文件映射成对应的配置对象
    public DataSource dataSource(){
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }

    /**
     * 事务管理器
     */
    @Primary
    @Bean(name = "masterTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("masterDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * session工厂
     */
    @Primary
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean  = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(Mapper_location));
        return sqlSessionFactoryBean.getObject();
    }


}
