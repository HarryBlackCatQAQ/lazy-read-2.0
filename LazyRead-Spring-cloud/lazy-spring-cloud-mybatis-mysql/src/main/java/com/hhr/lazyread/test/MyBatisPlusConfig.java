package com.hhr.lazyread.test;

/**
 * @Author: Harry
 * @Date: 2019-12-28 02:48
 * @Version 1.0
 */
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lulu
 * @Date 2019/6/8 16:12
 */
@Configuration // 该注解类似于spring配置文件
public class MyBatisPlusConfig {
    /**
     * 配置数据源
     * @return
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource master() {
        DataSource dataSource = DataSourceBuilder.create().build();
        //System.err.println(dataSource);
        return dataSource;
    }
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public DataSource slave() {
        DataSource dataSource = DataSourceBuilder.create().build();
       // System.err.println(dataSource);
        return dataSource;
    }


    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("master") DataSource master,
                                        @Qualifier("slave") DataSource slave) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSourceHolder.DB_MASTER, master);
        targetDataSource.put(DynamicDataSourceHolder.DB_SLAVE, slave);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        return dataSource;
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor(){
        return new DynamicDataSourceInterceptor();
    }
    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory()
            throws Exception {
        //配置mybatis,对应mybatis-config.xml
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        //懒加载
        LazyConnectionDataSourceProxy p=new LazyConnectionDataSourceProxy();
        p.setTargetDataSource(dataSource(master(),slave()));
        sqlSessionFactory.setDataSource(p);
        //需要mapper文件时加入扫描，
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
//加入上面的两个拦截器
        Interceptor interceptor[]={paginationInterceptor(),dynamicDataSourceInterceptor()};
        sqlSessionFactory.setPlugins(interceptor);
        return sqlSessionFactory.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}