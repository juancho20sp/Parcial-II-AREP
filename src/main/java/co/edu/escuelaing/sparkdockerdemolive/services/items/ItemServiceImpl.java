package co.edu.escuelaing.sparkdockerdemolive.services.items;

import co.edu.escuelaing.sparkdockerdemolive.services.database.MongoDB;
import com.mongodb.*;
//import org.json.*;
import com.google.gson.JsonObject;
//import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ItemServiceImpl {
    MongoClient mongoClient;
    DB database;
    DBCollection collection;

    public ItemServiceImpl() {
        // $
        System.out.println("Creating ItemServiceImpl");

        this.mongoClient = MongoDB.getInstance();
        this.database = this.mongoClient.getDB("items");
        this.collection = this.database.getCollection("myItems");

        // $
        System.out.println("Client: " + this.mongoClient.toString());
        System.out.println("Database: " + this.database.toString());
        System.out.println("Collection: " + this.collection.toString());
    }

    public JsonObject getAllItems() {
        DBCursor cursor = this.collection.find();

        System.out.println(cursor.toArray());

        JsonObject json = new JsonObject();

        List<DBObject> data = this.collection.find().toArray();

        json.addProperty("data", String.valueOf(data));

//        return this.collection.find().toArray();
        return json;
    }

    public void addItem(String item){
//        JSONObject jsonObject = new JSONObject(item);
//
//        String text = jsonObject.getString("text");
//
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
//        Date date = new Date(System.currentTimeMillis());
//
//        DBObject mongoItem = new BasicDBObject()
//                .append("text", text)
//                .append("date", formatter.format(date));
//
//        this.collection.insert(mongoItem);
    }
}
