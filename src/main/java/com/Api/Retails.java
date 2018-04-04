package com.Api;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Retails {
    public String GetUserData() {
        String UserData = "";
        try {
            Connection connection = getConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private Connection getConnection() throws URISyntaxException, SQLException {

        URI dbURI = new URI(System.getenv("DATABASE_URL"));

        String user = dbURI.getUserInfo().split(":")[0];
        String pass = dbURI.getUserInfo().split(":")[1];

        String dbUrl = "jdbc:postgresql://" + dbURI.getHost() + ":" + dbURI.getPort() + dbURI.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl,user,pass);
    }
}
