package mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Indexes.descending;

public class LogDAO {

    private final MongoCollection<Document> logsCollection;

    public LogDAO(MongoDatabase database) {
        this.logsCollection = database.getCollection("logs");
    }
    public void addLog(String message) {
        Document newLog = new Document("message", message)
                .append("timestamp", new Date());
        logsCollection.insertOne(newLog);
    }

    public String getLastLogs() {
        FindIterable<Document> logs = logsCollection.find().sort(descending("timestamp")).limit(10);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Document log : logs) {
            sb.append(log.toJson()).append(",");
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1); // Remove last comma
        }
        sb.append("]");
        return sb.toString();
    }

    public void deleteLog(String message) {
        logsCollection.deleteOne(Filters.eq("message", message));
    }

    public void updateLog(String oldMessage, String newMessage) {
        logsCollection.updateOne(Filters.eq("message", oldMessage), Updates.set("message", newMessage));
    }

    public List<Document> listLogs() {
        return logsCollection.find().into(new ArrayList<>());
    }
}
