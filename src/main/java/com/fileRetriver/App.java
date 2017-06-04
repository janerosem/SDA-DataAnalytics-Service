package com.fileRetriver;

import static spark.Spark.get;

import com.fileRetriver.entity.FileEntity;
import com.fileRetriver.service.FileRetriverService;
import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {/*
    	 get("/file","application/json", new Route() {
             //@Override
             public Object handle(Request request, Response response) {
            	 FileEntity file = null;
            	 Gson gson = null;
                 try {
					FileRetriverService retriver =  new FileRetriverService(mongo());
					//file = retriver.find("testrff.txt");
					retriver.listenToDBChanges();
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                gson = new Gson();
				return gson.toJson(file);
             }
         });
    */
    	try {
			FileRetriverService retriver =  new FileRetriverService(mongo());
			//file = retriver.find("testrff.txt");
			retriver.listenToDBChanges();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	}
    
    
    private static DB mongo() throws Exception {
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
       // if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            return mongoClient.getDB("local");
       // }
        /*int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        String dbname = System.getenv("OPENSHIFT_APP_NAME");
        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        if (db.authenticate(username, password.toCharArray())) {
            return db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }*/
    }
}
