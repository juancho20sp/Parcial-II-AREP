package co.edu.escuelaing.sparkdockerdemolive.services.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;


public class MongoDB {
    public static MongoClient getInstance() {
        String URL = "localhost:27017";
//        return new MongoClient(new MongoClientURI("mongodb://" + URL));
        return new MongoClient("localhost", 27017);
    }
}
