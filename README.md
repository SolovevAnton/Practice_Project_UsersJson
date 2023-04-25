# Practice_Project_UsersJson
Project on the topic "Jackson for parsing json forms" 

## Tasks: 
1. Create a UsersJson project
 2. Generate through a special data service model reflecting the objects from the sent response server. 
The initial class has the name: User. Take data from the resource: https://jsonplaceholder.typicode.com/users 
3. Create a repository, a constructor that uses the force to produce resources and create a list of objects, which is in the field of the repository.
 4. Similarly, we created a data model and repositories for objects returned with resources: https://jsonplaceholder.typicode.com/posts. The initial class is named: Post
5. In the user repositories, write a method that takes an id as input and returns a user object corresponding to the passed id 
6. In PostRepository, write a method that takes UserRepository as input and returns a HashMap<User, ArrayList<Post>>, showing which property corresponds to which posts. 
To determine the author of a post, the Post object has a userId field that refers to the id gender from the User class. When testing this method, a dictionary is displayed on the screen in the following order: userId: all post ids of this user
