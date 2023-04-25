package com.solovev;

import com.solovev.repository.UsersRepository;
import com.solovev.util.FromURLtoFile;
import com.solovev.util.Json2PojoGenerator;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    //TODO remove exceptions from main
    public static void main(String[] args) throws IOException {
        //Generate classes from the given Json File
        //creating file
        URL urlUsers = new URL("https://jsonplaceholder.typicode.com/users");
        FromURLtoFile users = new FromURLtoFile(urlUsers);
        String fileNameUsers = "Users.json";
        String dirName = "src/main/java/";
        users.readAndSave(fileNameUsers);

        //generate classes of User:
        Json2PojoGenerator generatorUsers = new Json2PojoGenerator(fileNameUsers,dirName);
        String classNameUsers = "Users";
        String packageNameUsers ="com.solovev.example.User"; //changed to example to not break toString equals and hashcode in generated classes
        generatorUsers.generate(classNameUsers,packageNameUsers);

        //generate classes of Post
        URL urlPosts = new URL("https://jsonplaceholder.typicode.com/posts");
        String fileNamePosts = "Posts.json";
        FromURLtoFile posts = new FromURLtoFile(urlPosts);
        posts.readAndSave(fileNamePosts);
        Json2PojoGenerator generatorPosts = new Json2PojoGenerator(fileNamePosts,dirName);
        String classNamePosts = "Posts";
        String packageNamePosts ="com.solovev.example.Post";
        generatorPosts.generate(classNamePosts,packageNamePosts);

        // Users repo test
        UsersRepository usersInRep = new UsersRepository(urlUsers);
        System.out.println(usersInRep);

        //corrupted users test
        File file = new File("CorruptedUsers.json");
        UsersRepository corruptedUsersInRep = new UsersRepository(file.toURL());
        System.out.println(corruptedUsersInRep);

        //find user by id Test
        int idToFind = 5;
        System.out.printf("Find id:%d expected: Optional<User>; Result: %s\n",idToFind,usersInRep.find(idToFind));
        System.out.printf("Find id:%d expected: Optional.Empty; Result: %s\n",idToFind,corruptedUsersInRep.find(idToFind));
    }
}