/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniquerybrowser.methods;

/**
 *
 * @author RAFSANJANI
 */
public class Credentials {
    private String username, password, database, port, url;
    
    public void setUrl(String url){
        this.url = url;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public String getPort() {
        return port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Credentials(String username, String password, String database, String port) {
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;
    }
    
    //user will choose database later
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    //assuming port 3306 is the default port for mysql connections
    public Credentials(String username, String password, String database) {
        this.username = username;
        this.password = password;
        this.database = database;
    }
    
    //user will set everything later
    Credentials(){
        
    }
    
    
}
