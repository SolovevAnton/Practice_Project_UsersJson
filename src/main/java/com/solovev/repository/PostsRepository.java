package com.solovev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solovev.model.post.Post;
import com.solovev.model.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class to collect and store posts got from URL
 */
public class PostsRepository {
    private List<Post> posts = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PostsRepository() {
    }

    /**
     * Method to read all objects of type post from source url to Set
     *
     * @param url url to get data from
     * @throws IOException
     */
    public PostsRepository(URL url) throws IOException {
        posts = objectMapper.readValue(url, new TypeReference<>() {
        });
    }

    /**
     * Method to build a map of all posts and users who post them
     *
     * @param rep repository where all users are stored
     * @return map of users and their posts. Users with no posts are omitted
     */
    public HashMap<User, List<Post>> findPosts(UsersRepository rep) {
        Set<Integer> existingUsersId = rep
                .getUsers()
                .stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        List<Post> filteredPosts = posts;
//                .stream()
//                .filter(existingUsersId::contains)
//                .collect(Collectors.toList());
        Function<Post, User> getUserByPostId = p -> rep.find(p.getUserId()).get();
        Function<Post, ArrayList<Post>> listOfUsersPost = post -> filteredPosts
                .stream()
                .filter(p -> p.getUserId() == post.getUserId())
                .collect(Collectors.toCollection(ArrayList::new));
        return new HashMap<>(
                filteredPosts
                        .stream()
                        .collect(Collectors.groupingBy(getUserByPostId))
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostsRepository that = (PostsRepository) o;

        if (!Objects.equals(posts, that.posts)) return false;
        return objectMapper != null ? objectMapper.equals(that.objectMapper) : that.objectMapper == null;
    }

    @Override
    public int hashCode() {
        int result = posts != null ? posts.hashCode() : 0;
        result = 31 * result + (objectMapper != null ? objectMapper.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PostsRepository{" +
                "posts=" + posts +
                ", objectMapper=" + objectMapper +
                '}';
    }
}
