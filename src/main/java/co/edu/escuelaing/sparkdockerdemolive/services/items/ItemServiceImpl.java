package co.edu.escuelaing.sparkdockerdemolive.services.items;

import co.edu.escuelaing.sparkdockerdemolive.services.database.MongoDB;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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
    private DB database;
    private DBCollection collection;

    public ItemServiceImpl() {
        this.setupMongoDatabase();
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
        System.out.println("Client: " + this.mongoClient.toString());
        System.out.println("Database: " + this.database.toString());
        System.out.println("Collection: " + this.collection.toString());
    }

    /**
     * Connect to the Database
     */
    private void setupMongoDatabase() {
        String URL = "localhost:27017";

        this.mongoClient = new MongoClient(new MongoClientURI("mongodb://" + URL));
        this.database = mongoClient.getDB("items");
        this.collection = this.database.getCollection("myItems");
    }

    public void getAllItems() {
//        FindIterable<Document> iterableDocument = this.collection.find();

        System.out.println(" --- ");
        System.out.println("Pls value 1:");
        System.out.println(this.collection.find().toString());

//        Iterator it = iterableDocument.iterator();
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }


//        return this.collection.find().toArray();
//        return json;
    }

    public List<DBObject> addItem(String item){
        List<DBObject> response = new ArrayList<>();

        this.collection.insert(new BasicDBObject("text", item)
                .append("date", new Date()));

        DBCursor cursor = this.collection.find().limit(10);

        while(cursor.hasNext()) {
            response.add(cursor.next());
        }

        return response;

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
