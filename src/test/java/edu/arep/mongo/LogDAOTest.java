package mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

class LogDAOTest {

    private LogDAO logDAO;
    private MongoCollection<Document> mockCollection;
    private MongoDatabase mockDatabase;

    @BeforeEach
    void setUp() {
        mockCollection = Mockito.mock(MongoCollection.class);
        mockDatabase = Mockito.mock(MongoDatabase.class);
        when(mockDatabase.getCollection("logs")).thenReturn(mockCollection);
        logDAO = new LogDAO(mockDatabase);
    }

    @Test
    void testAddLog() {
        String message = "Test log";
        logDAO.addLog(message);
        verify(mockCollection, times(1)).insertOne(any(Document.class));
    }


    @Test
    void testDeleteLog() {
        String message = "Log para eliminar";
        logDAO.addLog(message);
        logDAO.deleteLog(message);
        verify(mockCollection, times(1)).deleteOne(Filters.eq("message", message));
    }

    @Test
    void testUpdateLog() {
        String oldMessage = "Log antiguo";
        String newMessage = "Log nuevo";
        logDAO.updateLog(oldMessage, newMessage);
        verify(mockCollection, times(1)).updateOne(Filters.eq("message", oldMessage), Updates.set("message", newMessage));
    }
}
