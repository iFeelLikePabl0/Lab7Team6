/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import model.User;

/**
 * REST Web Service
 *
 * @author colli
 */
@Path("Account")
public class AccountResource {

    @Context
    private UriInfo context;

    public AccountResource() {
        
    }

    @GET
    @Produces("text/html")
    public String getUsers() throws FileNotFoundException, IOException, ClassNotFoundException {
        
        String userString = "";
        ArrayList<User> users = new ArrayList<User>();
        users = getUserList();
        for(int i = 0; i < users.size(); i ++)
        {
            userString = userString + users.get(i).getUserName();
            if(i < users.size())
            {
                userString = userString + ", ";
            }
        }
        return userString;
    }
    
    @DELETE
    @Path("deleteUser")
    @Consumes("text/html")
    public void deleteUser(String userNameAndPassword) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        String[] parts = userNameAndPassword.split(" ");
        String userName = parts[0];
        String password = parts[1];
        
        ArrayList<User> users = getUserList();
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                users.remove(user);
            }
        }
        
    }
    
    @PUT
    @Path("createUser")
    @Consumes("text/html")
    public void createUser(String userNameAndPassword) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        ArrayList<User> users = getUserList();

        String[] parts = userNameAndPassword.split(" ");
        String part1 = parts[0]; // user
        String part2 = parts[1]; // password
        System.out.println(part1 + " " + part2);

        User user = new User(part1, part2);
        users.add(user);

        File f = new File("users.ser");
        System.out.println(f.getAbsolutePath());

        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        
        for(int i = 0; i < users.size(); i++) {
            oos.writeObject(users.get(i));
        }

        fout.close();
        oos.close();

    }

    @POST
    @Path("updateUser")
    @Consumes("text/html")
    public void putUser(String content) throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList<User> users = getUserList();
        
        
    }
    
    private ArrayList<User> getUserList() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        try {
            FileInputStream fis = new FileInputStream("users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<User> users = (ArrayList<User>) ois.readObject();
            ois.close();

            return users;
        } catch (FileNotFoundException fnfe) {
            File f = new File("users.ser");
            
            ArrayList<User> users = new ArrayList<User>();
            return users;
        }

    }  
}
