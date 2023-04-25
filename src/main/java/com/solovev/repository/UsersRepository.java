package com.solovev.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solovev.model.user.User;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Class to read snd store List of users from URL
 */
public class UsersRepository {
    private Set<User> users = new HashSet<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public UsersRepository() {
    }

    /**
     * Method to read all objects of type user from source url to Set
     *
     * @param url url to get data from
     * @throws IOException
     */
    public UsersRepository(URL url) throws IOException {
        this.users = objectMapper.readValue(url, new TypeReference<>() {
        });
    }

    /**
     * Method to first find user by its id in this object
     * @param id id to look for
     * @return Optional of user when user was found, empty optional otherwise
     */
    public Optional<User> find(int id){
        return users
                .stream()
                .filter(user -> user.getId() == id)
                .findAny();
    }

    public Set<User> getUsers() {
        return new HashSet<>(users);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersRepository that = (UsersRepository) o;

        if (!Objects.equals(users, that.users)) return false;
        return Objects.equals(objectMapper, that.objectMapper);
    }

    @Override
    public int hashCode() {
        int result = users != null ? users.hashCode() : 0;
        result = 31 * result + (objectMapper != null ? objectMapper.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsersRepository{" +
                "users=" + users +
                ", objectMapper=" + objectMapper +
                '}';
    }

}
