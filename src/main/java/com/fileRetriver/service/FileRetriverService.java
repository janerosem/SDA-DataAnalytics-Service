package com.fileRetriver.service;

import com.fileRetriver.entity.FileEntity;
import com.fileRetriver.repository.FileRetriverRepo;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class FileRetriverService {
	
	private final DB db;
	private FileRetriverRepo  retriverRepo = null;
 
    public FileRetriverService(DB db) {
        this.db = db;
    }
    
    
    public void listenToDBChanges(){
    	retriverRepo = new FileRetriverRepo(db);
    	DBCollection col = db.getCollection("fs.files");
    	final DBCursor cur = col.find().sort(BasicDBObjectBuilder.start("$natural", 1).get())
                .addOption(Bytes.QUERYOPTION_TAILABLE | Bytes.QUERYOPTION_AWAITDATA);
    	
    	System.out.println("== open cursor ==");

        Runnable task = () -> {
            System.out.println("\tWaiting for events");
            while (cur.hasNext()) {
                DBObject obj = cur.next();
                System.out.println( obj );
                retriverRepo.saveToNewDB(retriverRepo.findFile(obj.get("filename").toString()));
            }
        };
        new Thread(task).start();

    }

}
