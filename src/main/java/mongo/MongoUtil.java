package mongo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static final String CONNECTION_STRING = "mongodb://mongodatabase:27017";
    //private static final String CONNECTION_STRING = "mongodb://localhost:27017/";
    private static final String DATABASE_NAME = "db";

    public static MongoDatabase getDB() {
        MongoClient client = null;
        try {
            client = MongoClients.create(CONNECTION_STRING);
            return client.getDatabase(DATABASE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a la base de datos MongoDB", e);
        }
    }

    private void close() {
        MongoClient client = MongoClients.create(CONNECTION_STRING);
        client.close();
    }
}
