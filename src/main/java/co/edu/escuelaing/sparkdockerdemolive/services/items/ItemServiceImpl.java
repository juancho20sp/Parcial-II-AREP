package co.edu.escuelaing.sparkdockerdemolive.services.items;

import com.mongodb.*;
import com.mongodb.client.*;

import java.util.*;
import org.bson.Document;



public class ItemServiceImpl {
    private MongoClient mongoClient;
    private final static String URL = "mongodb+srv://admin:admin@mycluster.79lwk.mongodb.net/?retryWrites=true&w=majority";

    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public ItemServiceImpl() {
        ConnectionString connection = new ConnectionString(URL);
        this.mongoClient = MongoClients.create(connection);
        this.database = this.mongoClient.getDatabase("items");
        this.collection = this.database.getCollection("myItems");
    }

    public ArrayList<String> getAllItems() {
        ArrayList<String> messages = new ArrayList<>();

        FindIterable fit = this.collection.find();
        ArrayList<Document> docs = new ArrayList<>();

        fit.into(docs);
        docs.forEach(document -> {
            String message = document.toJson();
            messages.add(message);
        });

        return messages;
    }

    public void addItem(String item){
        System.out.println(" --- ITEM ---");
        System.out.println(item);

        Document myDocument = new Document();
        myDocument.put("text", item);
        myDocument.put("date", new Date());

        this.collection.insertOne(myDocument);
    }
}
