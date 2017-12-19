package org.wso2.androidtv.agent.h2cache;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.h2.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by lakshika on 12/12/17.
 */

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource Hikarids;

   /* @Override
    public void onEnable(){
        connectToDatabase();
    }*/

    static {
        config.setJdbcUrl( "jdbc:h2:/data/data/agent.androidtv.wso2.org.agent2/data/edgeTVGateway;FILE_LOCK=FS;PAGE_SIZE=1024;CACHE_SIZE=8192" );
        //config.setJdbcUrl( "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from 'classpath:/db.sql'" );

        config.setMaximumPoolSize(10);
        config.setUsername( "admin" );
        config.setPassword( "admin" );
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //config.setDataSourceClassName("org.h2.jdbcx.JdbcDataSource");

        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        Hikarids = new HikariDataSource( config );
    }

    private DataSource() {
        JdbcConnection jd;
    }

    public static Connection getConnection() throws SQLException {
        return Hikarids.getConnection();
    }


}
