package com.solovev;

import com.solovev.model.Post;
import com.solovev.model.User;
import com.solovev.repository.PostsRepository;
import com.solovev.repository.UsersRepository;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)  {
        try {
            //Generate classes from the given Json File
            //creating file
            /*URL urlUsers = new URL("https://jsonplaceholder.typicode.com/users");
            FromURLtoFile users = new FromURLtoFile(urlUsers);
            String fileNameUsers = "Users.json";
            String dirName = "src/main/java/";
            users.readAndSave(fileNameUsers);

            //generate classes of user:
            Json2PojoGenerator generatorUsers = new Json2PojoGenerator(fileNameUsers, dirName);
            String classNameUsers = "ExampleUsers";
            String packageNameUsers = "com.solovev.example.user"; //changed to example to not break toString equals and hashcode in generated classes
            generatorUsers.generate(classNameUsers, packageNameUsers);*/

           /* //generate classes of post
            URL urlPosts = new URL("https://jsonplaceholder.typicode.com/posts");
            String fileNamePosts = "Posts.json";
            FromURLtoFile posts = new FromURLtoFile(urlPosts);
            posts.readAndSave(fileNamePosts);
            Json2PojoGenerator generatorPosts = new Json2PojoGenerator(fileNamePosts, dirName);
            String classNamePosts = "ExamplePosts";
            String packageNamePosts = "com.solovev.example.post";
            generatorPosts.generate(classNamePosts, packageNamePosts);*/

            URL urlUsers = new URL("https://jsonplaceholder.typicode.com/users");
            // Users repo test
            UsersRepository usersInRep = new UsersRepository(urlUsers);
            System.out.println(usersInRep);

            //corrupted users test
            File file = new File("CorruptedUsers.json");
            UsersRepository corruptedUsersInRep = new UsersRepository(file.toURL());
            System.out.println(corruptedUsersInRep);

            //find user by id Test
            int idToFind = 5;
            System.out.printf("Find id:%d expected: Optional<user>; Result: %s\n", idToFind, usersInRep.find(idToFind));
            System.out.printf("Find id:%d expected: Optional.Empty; Result: %s\n", idToFind, corruptedUsersInRep.find(idToFind));

            // Posts repo test
            URL urlPosts = new URL("https://jsonplaceholder.typicode.com/posts");
            System.out.println("\nPosts repo test");
            PostsRepository postsRepository = new PostsRepository(urlPosts);
            PostsRepository emptyPostsRepository = new PostsRepository();
            //find posts test
            Consumer<Map.Entry<User, List<Post>>> print = entry -> System.out.println(
                    entry.getKey().getId() + ": " +
                            entry
                                    .getValue()
                                    .stream()
                                    .map(Post::getId)
                                    .map(Object::toString)
                                    .collect(Collectors.joining(" "))
            );
            postsRepository
                    .findPosts(usersInRep)
                    .entrySet()
                    .forEach(print);
            //test for empty post rep
            emptyPostsRepository
                    .findPosts(usersInRep)
                    .entrySet()
                    .forEach(print);
            //test for empty user rep
            postsRepository
                    .findPosts(new UsersRepository())
                    .entrySet()
                    .forEach(print);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}