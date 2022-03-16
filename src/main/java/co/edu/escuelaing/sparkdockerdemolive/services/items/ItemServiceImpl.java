package co.edu.escuelaing.sparkdockerdemolive.services.items;

import com.mongodb.*;
import com.mongodb.client.*;

import java.net.UnknownHostException;
import java.util.*;

import org.bson.Document;

import org.json.*;
import com.google.gson.JsonObject;
//import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Iterator;


public class ItemServiceImpl {
    private MongoClient mongoClient;
    private final static String URL = "mongodb+srv://admin:admin@mycluster.79lwk.mongodb.net/?retryWrites=true&w=majority";

//    private DB database;
    private MongoDatabase database;
//    private DBCollection collection;
    private MongoCollection<Document> collection;

    public ItemServiceImpl() {
        ConnectionString connection = new ConnectionString(URL);
        this.mongoClient = MongoClients.create(connection);
        this.database = this.mongoClient.getDatabase("items");
        this.collection = this.database.getCollection("myItems");

        System.out.println("DATABASE CONNECTION STABLISHED");
//        this.setupMongoDatabase();
//        // $
//        System.out.println("Creating ItemServiceImpl");
//
//        //this.mongoClient = MongoDB.getInstance();
//        this.mongoClient = new MongoClient( "localhost" , 27017 );
//
//        // $
//        System.out.println(this.mongoClient.toString());
//
//        this.database = this.mongoClient.getDatabase("items");
//
//        // $
//        System.out.println(this.database.toString());
//
//        this.collection = this.database.getCollection("myItems");
//
//        // $
//        System.out.println(this.collection.toString());

        //Creating a MongoDB client
//        MongoClient mongo = new MongoClient( "localhost" , 27017 );
//        //Connecting to the database
//        MongoDatabase database = mongo.getDatabase("items");
//        //Creating a collection object
//        MongoCollection<Document> collection = database.getCollection("myItems");

        // $
//        System.out.println("Client: " + mongoClient.toString());
//        System.out.println("Database: " + this.database.toString());
//        System.out.println("Collection: " + this.collection.toString());
    }

    /**
     * Connect to the Database
     */
    public static void setupMongoDatabase() {
        String URL = "localhost:27017";

//        this.mongoClient = new MongoClient(new MongoClientURI("mongodb://" + URL));


//        this.database = this.mongoClient.getDatabase("items");
//        this.collection = this.database.getCollection("myItems");

        // $
        System.out.println(" --- ");
        System.out.println("DATABASE CREATED");
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
        Document myDocument = new Document();
        myDocument.put("text", item);
        myDocument.put("date", new Date());

        this.collection.insertOne(myDocument);

        // $
        System.out.println("ITEM SUCCESSFULLY ADDED");

//        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
//        MongoDatabase database = mongoClient.getDatabase("items");
//        MongoCollection<Document> myCollection = database.getCollection("myItems");
//
//        Document document = new Document();
//        document.put("text", item);
//        document.put("date", new Date());
//
//        // $
//        System.out.println("DOCUMENT CREATED");
//        System.out.println(document.toString());
//
//        myCollection.insertOne(document);
//
//        // $
//        System.out.println("DOCUMENT INSERTED");



//        List<DBObject> response = new ArrayList<>();
//
//        // $
//        System.out.println(" --- ");
//        System.out.println("RESPONSE ARRAY CREATED");
//
//        this.collection.insert(new BasicDBObject("text", item)
//                .append("date", new Date()));
//
//        // $
//        System.out.println("ITEM ADDED TO COLLECTION");
//
//        DBCursor cursor = this.collection.find().limit(10);
//
//        // $
//        System.out.println("CURSOR DONE");
//
//
//        while(cursor.hasNext()) {
////            response.add(cursor.next());
//            System.out.println("Value: " + cursor.next());
//        }
//
//        return response;

//        JSONObject jsonObject = new JSONObject(item);
//
//        String text = jsonObject.getString("text");
//
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
//        Date date = new Date(System.currentTimeMillis());
//
//        Document mongoItem = new Document()
//                .append("text", text)
//                .append("date", formatter.format(date));
//
//        this.collection.insertOne(mongoItem);
    }
}
