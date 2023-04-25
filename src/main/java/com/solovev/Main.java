package com.solovev;

import com.solovev.repository.FromURLtoFile;
import com.solovev.util.Json2PojoGenerator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    //TODO remove exceptions from main
    public static void main(String[] args) throws IOException {
        //Generate classes from the given Json File
        //creating file
        URL urlUsers = new URL("https://jsonplaceholder.typicode.com/users ");
        FromURLtoFile users = new FromURLtoFile(urlUsers);
        String fileName = "Users.json";
        String dirName = "src/main/java/";
        users.readAndSave(fileName);
        //generate classes:
        Json2PojoGenerator generator = new Json2PojoGenerator(fileName,dirName);
        String className = "Users";
        String packageName ="com.solovev.model";
        generator.generate(className,packageName);

    }
}