package org.crochetdata;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

@Component
public class MySQLConnectionTest {
    
    private final DataSource dataSource;
    
    @Autowired
    public MySQLConnectionTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}