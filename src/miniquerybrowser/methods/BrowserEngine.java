/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniquerybrowser.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * @author RAFSANJANI
 */
public class BrowserEngine {

    Connection con;
    Credentials credentials;

    public BrowserEngine(Credentials defaultUser) {
        this.credentials = defaultUser;
    }

    public BrowserEngine() {

    }

    public void connect() throws ServerNotFoundException, InvalidCredentialsException, Exception {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", credentials.getUsername(), credentials.getPassword());

        } catch (SQLException ex) {

            if (ex.getMessage().contains("packet")) { //server is offline
                throw new ServerNotFoundException("The specified server is offline or unavailable");
            } else if (ex.getMessage().contains("denied")) {
                throw new InvalidCredentialsException("Access denied for user: " + credentials.getUsername() + " using password: " + credentials.getPassword());
            } else {
                throw new Exception(ex.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return con;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setConnection(Connection con) {
        this.con = con;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void disconnect() throws InvalidOperationException, SQLException {
        if (con == null) {
            throw new InvalidOperationException("Error occurred");
        }

        if (con.isClosed()) {
            throw new InvalidOperationException("The database is Closed");
        }

        con.close();
    }

    public boolean connected() throws InvalidOperationException {
        if (con == null) {
            return false;
        }

        try {
            return !con.isClosed();
        } catch (SQLException ex) {
            throw new InvalidOperationException("Invalid operation attempted");
        }

    }

    ;
   
    /*
     *
     * @param query
     * @param type
     * @return boolean if the command is a command query, resultSet if a select and an integer representing the affected rows if an UPDATE statement
     * @throws InvalidOperationException
     * @throws WrongQueryException
     * TODO: run all calls to this method on a separate thread
     */
    public Object runQuery(String query, QueryType type) throws InvalidOperationException, WrongQueryException {
        ResultSet results;
        Statement stmt;
        if (!connected()) {
            throw new InvalidOperationException("Attempting to run query whilst connection is closed");
        }

        switch (type) {
            case SELECT:
                try {
                    stmt = con.createStatement();
                    results = stmt.executeQuery(query);

                    return results;
                } catch (Exception ex) {
                    throw new WrongQueryException(ex.getMessage());
                }

            case UPDATE:
                try {
                    stmt = con.createStatement();
                    return stmt.executeUpdate(query);

                } catch (Exception ex) {
                    throw new WrongQueryException(ex.getMessage());
                }
            case COMMAND:
                try {
                    stmt = con.createStatement();
                    stmt.execute(query);
                    return true;
                } catch (SQLException ex) {
                    throw new WrongQueryException(ex.getMessage());
                }
            default:
                return false;

        }

    }

}

enum QueryType {

    UPDATE, SELECT, COMMAND;
}
