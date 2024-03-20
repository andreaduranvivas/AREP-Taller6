package mongo;

import com.mongodb.client.MongoDatabase;


public class Mongoexample {


        public static void main(String[] args) {
            MongoDatabase database = mongo.MongoUtil.getDB();
            LogDAO logDAO = new LogDAO(database);

            // Añadir un nuevo log
            logDAO.addLog("Primer log");
            logDAO.addLog("Segundo log");
            logDAO.addLog("Tercer log");
            logDAO.addLog("Cuarto log");
            logDAO.addLog("Quinto log");
            logDAO.addLog("Sexto log");
            logDAO.addLog("Séptimo log");
            logDAO.addLog("Octavo log");
            logDAO.addLog("Noveno log");
            logDAO.addLog("Décimo log");
            logDAO.addLog("11 log");

            // Listar los últimos logs
            System.out.println(logDAO.getLastLogs());
        }

}
