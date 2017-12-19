package org.wso2.androidtv.agent.h2cache;

/**
 * Created by lakshika on 12/13/17.
 */

import android.content.ContextWrapper;
import android.provider.ContactsContract;
import android.util.Log;

import org.wso2.androidtv.agent.cache.CacheEntry;
import org.wso2.androidtv.agent.cache.CacheManager;
import org.wso2.androidtv.agent.siddhiSinks.EdgeGatewaySink;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class H2Connection{
    private ContextWrapper contextWrapper;
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    String url = "jdbc:h2:/data/data/" +
            "org.wso2.androidtv.agent" +
            "/data/edgeTVGateway" +
            ";FILE_LOCK=FS" +
            ";PAGE_SIZE=1024" +
            ";CACHE_SIZE=8192";

    public H2Connection(ContextWrapper contextWrapper){
        this.contextWrapper =contextWrapper;
    }

    public H2Connection() {

    }


    public void initializeConnection() throws SQLException, ClassNotFoundException {

        File directory = contextWrapper.getFilesDir();
        System.out.println("h2 db direcotry :"+directory);



    }


    public void CreateQuery () throws SQLException {

        String create_query = "CREATE TABLE ACtable(Id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, ACvalue INT ) ";

        try{
            //connection = DataSource.getConnection();
            connection = DriverManager.getConnection(url,"admin","admin");
            ps = connection.prepareStatement(create_query);
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {

            if(connection != null) connection.close();

            if(ps!=null) ps.close();
        }

    }

    public void InsertQuery (String Data_to_insert){
        //PreparedStatement ps = null;
        try{
            //connection = DataSource.getConnection();
            connection = DriverManager.getConnection(url,"admin","admin");
            ps = connection.prepareStatement(Data_to_insert);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    public void checkIfTableExists() throws SQLException {
        boolean tableExists=false;
        String select_query= "SELECT * FROM ACtable";
        System.out.println("aaaaaaaa");

        try {
            this.initializeConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // Statement stat = conn.createStatement();

        //connection = DataSource.getConnection();
        connection = DriverManager.getConnection(url,"admin","admin");
        ps = connection.prepareStatement(select_query);
        rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println("tableIterate :"+rs.getString("AC"));
        }
        System.out.println("tableExists :"+tableExists);


    }


}
