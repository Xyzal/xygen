package org.xyzal.xygen;

import com.google.code.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * User: xyzal
 * Date: 2014/4/14
 * Time: 14:13
 */
@Repository
public class UserDao{

    @Autowired(required = true)
    protected UserDao(@Qualifier("datastore")Datastore ds) {
    }
}
