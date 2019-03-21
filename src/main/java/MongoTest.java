import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

// most of this code and more examples can be found at:
// https://mongodb.github.io/mongo-java-driver/3.10/driver/getting-started/quick-start/

public class MongoTest {

    public static void main(String[] args) {
        System.out.println("Connecting with default values on localhost.");

        // connect to mongod
        MongoClient mongoClient = MongoClients.create();

        // select the database
        MongoDatabase database = mongoClient.getDatabase("demo_database");

        // select the collection
        MongoCollection<Document> collection = database.getCollection("demo_collection");

        System.out.println("Number of documents in collection: " + collection.countDocuments());

        // let's insert one and try again
        Document doc = new Document("name", "JJ")
                .append("age", 77)
                .append("height", 70)
                .append("deceased", false);

        collection.insertOne(doc);

        System.out.println("Number of documents NOW in collection: " + collection.countDocuments());

        // this will select all the documents in the collection and print them out
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }
}
