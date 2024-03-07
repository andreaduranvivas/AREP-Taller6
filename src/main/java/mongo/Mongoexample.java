package mongo;

import com.mongodb.client.MongoDatabase;


public class Mongoexample {


        public static void main(String[] args) {
            MongoDatabase database = mongo.MongoUtil.getDB();
            mongo.UserDAO userDAO = new UserDAO(database);

            // Create a new user
            userDAO.addUser("John Doe", 30);

            // List users
            userDAO.listUsers();

            // Update user
            userDAO.updateUser("John Doe", 31);

                // Delete user
                userDAO.deleteUser("John Doe");
        }

}
