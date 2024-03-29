package authnetication;

import exceptions.SqlQueryException;
import factory.OperationFactory;
import factory.IFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import users.IUser;
import readDB.ExistenceChecking;
import users.UserInformation;
import users.UserTypes;
import variables.Variables;
import writeDB.UserAddition;

import java.sql.Connection;
import java.util.Scanner;

public class Signup implements IAuthentication{
    private static final Logger logger = LogManager.getLogger(Signup.class);
    public IUser authUser(Connection connection) {
        logger.debug(" Call the authUser function in class Signup");
        IFactory factory = new OperationFactory();
        UserInformation reader = new UserInformation();
        String username="";
        String password="";
        Scanner sc = new Scanner(System.in);
        boolean usernameIsValid = false;
        try {
        while (!usernameIsValid){
        System.out.print("Enter username: ");
        username = sc.next();
        logger.debug("User Enter his name" + username);
        if(ExistenceChecking.isExists(connection, Variables.USER_TABLE,username)){
           System.out.println("The username is exists, please choose another one: ");
        }else {
            usernameIsValid = true;
        }
        }
        System.out.print("Enter you password: ");
        password = sc.next();
        logger.debug("User Enter his password");
        reader.setName(username);
        reader.setPassword(password);
        UserAddition.addNewUser(connection,reader);

        } catch (SqlQueryException e) {
        System.out.println(e.getMessage());
        }
        logger.debug("Close the authUser function in Signup");
        return  factory.create(UserTypes.Reader);
    }

}

