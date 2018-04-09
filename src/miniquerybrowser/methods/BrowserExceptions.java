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
 class WrongQueryException extends Exception{
    public WrongQueryException(){}
    public WrongQueryException(String message){
        super(message);
    }
    
     public WrongQueryException (String message, Throwable cause) {
        super (message, cause);
    }
};

class InvalidOperationException extends Exception{
    public InvalidOperationException(){}
    
     public InvalidOperationException(String message){
         super(message);
     }
     
     public InvalidOperationException(String message, Throwable cause){
         super(message, cause);
     }
};

class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(){};
    
    public InvalidCredentialsException(String message){
        super(message);
    };
    
    public InvalidCredentialsException(String message, Throwable cause){
        super(message, cause);
    }
};

class ServerNotFoundException extends Exception{
    public ServerNotFoundException(){};
    public ServerNotFoundException(String message){
        super(message);
    }
    
    public ServerNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}


