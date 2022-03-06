package co.edu.escuelaing.sparkdockerdemolive.services.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import java.net.UnknownHostException;

public class MongoDB {
    public static MongoClient getInstance() {
        String URL = "localhost:27017";
        try {
            return new MongoClient(new MongoClientURI("mongodb://" + URL));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
