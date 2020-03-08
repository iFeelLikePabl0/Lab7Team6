/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
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

    /**
     * Creates a new instance of AccountResource
     */
    public AccountResource() {
    }

    @GET
    @Produces("text/html")
    public String getUsers() throws FileNotFoundException {
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
    public void deleteUser(String content)
    {
        
    }
    
    @PUT
    @Path("createUser")
    @Consumes("text/html")
    public void createUserJson(String userNameAndPassword) throws FileNotFoundException, IOException
    {
        String[] parts = userNameAndPassword.split(",");
        String part1 = parts[0]; // user
        String part2 = parts[1]; // password
        
        User user = new User(part1, part2);
        
        OutputStream os = new FileOutputStream("user.json");
        JsonWriter jsonWriter = Json.createWriter(os);
        jsonWriter.writeObject((JsonObject) Json.createObjectBuilder()
                .add("userName", user.getUserName())
                .add("password", user.getPassword()));
        
        os.close();
        jsonWriter.close();
        
        System.out.println(part1 + " " + part2);
        
//        OutputStream outputStream = null;
//        //get existing users
//        ArrayList<User> userList = getUserList();
//        //initialize new user
//        User user1 = new User(newUsername, "password");
//        //add user to list
//        userList.add(user1);
//        //initialize jsonArrayBuilder
//        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
//        for(int i = 0; i < userList.size(); i++)
//        {
//            jsonArrayBuilder.add(
//                    Json.createObjectBuilder()
//                        .add("userName", userList.get(i).getUserName())
//                        .add("password", userList.get(i).getPassword())
//            );
//        }
//        JsonArray userArray = jsonArrayBuilder.build();
//        outputStream = new FileOutputStream("users.json");
//        JsonWriter jsonWriter = Json.createWriter(outputStream);
//        jsonWriter.writeArray(userArray);
//        jsonWriter.close();
    }

    @PUT
    @Path("updateUser")
    @Consumes("text/html")
    public void putUser(String content) {
    }
    
    private ArrayList<User> getUserList() throws FileNotFoundException
    {
        ArrayList<User> users = new ArrayList<User>();
        InputStream inputStream = null;
        inputStream = new FileInputStream("users.json");
        JsonReader jsonReader = Json.createReader(inputStream);
            
        JsonArray usersArray = jsonReader.readArray();
        
        for(int i = 0; i < usersArray.size(); i++)
        {
            JsonObject userObj = usersArray.getJsonObject(i);
            User user = new User(userObj.getString("userName"),userObj.getString("password"));
            users.add(user);
        }
        return users;
    }  
}
