package com.mall.user.api.config.base;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;

@SpringBootTest
public class DatabaseConfigTests {
    
    @Test
    public void init() {
        ConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:h2:mem:///malluser?options=DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        DatabaseClient client = DatabaseClient.create(connectionFactory);
        DatabaseClient.GenericExecuteSpec sql = client.sql("select 1 from dual");
        System.out.println("==>" + sql.fetch().toString());
    }

    
}
