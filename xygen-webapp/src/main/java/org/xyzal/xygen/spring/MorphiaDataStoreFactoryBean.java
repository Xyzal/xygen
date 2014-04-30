package org.xyzal.xygen.spring;

/**
 * User: xyzal
 * Date: 2014/4/30
 * Time: 15:21
 */
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component("datastore")
public class MorphiaDataStoreFactoryBean implements FactoryBean<Datastore>,
        InitializingBean, DisposableBean {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MorphiaDataStoreFactoryBean.class);

    @Value("${gen.mongo.host}")
    private String host = "localhost";
    @Value("${gen.mongo.port}")
    private Integer port = 27017;;
    @Value("${gen.mongo.database}")
    private String dbName;
    @Value("${gen.mongo.user}")
    private String username;
    @Value("${gen.mongo.password}")
    private String password;

    private Datastore ds;
    private Mongo mongo;
    @Override
    public void afterPropertiesSet() throws Exception {
        mongo = new MongoClient(host, port);
        ds = new Morphia().createDatastore(mongo, dbName,username,password.toCharArray());
        LOGGER.debug("connect mongo {}", mongo);
        LOGGER.debug("create datastore {}", ds);
    }

    @Override
    public Datastore getObject() throws Exception {
        return ds;
    }

    @Override
    public Class<?> getObjectType() {
        return Datastore.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void destroy() throws Exception {
        LOGGER.debug("close mongo {}", mongo);
        mongo.close();
    }
}