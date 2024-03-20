package edu.arep.logService;
import com.mongodb.client.MongoDatabase;
import mongo.LogDAO;
import mongo.MongoUtil;
import spark.Spark;

import static spark.Spark.*;

public class LogService {

    public static void main(String[] args) {

        int port = getPort();
        port(port);

        Spark.get("/logservice", (req, res) -> {
            return saveAndGetLogs(req.queryParams("msg"));
        });


    }

    /**
     * Saves a log message to the database and retrieves the latest 10 logs.
     *
     * @param message the log message to be saved
     * @return the latest 10 logs from the database
     */
    private static String saveAndGetLogs(String message) {
        MongoDatabase database = MongoUtil.getDB();
        LogDAO logDAO = new LogDAO(database);
        logDAO.addLog(message);
        return logDAO.getLastLogs();
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000;
    }
}
