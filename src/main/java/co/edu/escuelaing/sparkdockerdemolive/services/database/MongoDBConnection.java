package co.edu.escuelaing.sparkdockerdemolive.services.database;

import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;


public class MongoDBConnection {
    private String url = "db";
//    private String url = "localhost";
    private int port = 27017;
    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;
    private MongoCollection<Document> mongoCollection;


    public void createConnection() {
        try {
            this.mongoClient = new MongoClient(this.url);
            this.mongoDatabase = this.mongoClient.getDatabase("items");
            this.mongoCollection = this.mongoDatabase.getCollection("myItems");
        } catch (MongoException ex){
            System.out.println(ex.toString());
        }
    }

    public void closeConnection() {
        this.mongoClient.close();
    }

    public ArrayList<String> addItem(String item){
        System.out.println(" --- ITEM ---");
        System.out.println(item);
        ArrayList<String> documents = new ArrayList<>();

        Document myDocument = new Document();
        myDocument.put("text", item);
        myDocument.put("date", new Date());

        this.mongoCollection.insertOne(myDocument);
        documents.add(myDocument.toJson());

        return documents;
    }

    public ArrayList<String> getAllItems() {
        ArrayList<String> messages = new ArrayList<>();

        FindIterable<Document> result = this.mongoCollection.find();

        result.forEach((Consumer<? super Document>) document -> messages.add(document.toJson()));

//        FindIterable fit = this.mongoCollection.find();
//        ArrayList<Document> docs = new ArrayList<>();
//
//        fit.into(docs);
//        docs.forEach(document -> {
//            String message = document.toJson();
//            messages.add(message);
//        });

        return messages;
    }




}
