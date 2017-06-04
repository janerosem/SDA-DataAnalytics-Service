package com.fileRetriver.repository;

import java.net.UnknownHostException;

import com.fileRetriver.entity.FileEntity;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.Bytes;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

public class FileRetriverRepo {
	private final DB db;
    private GridFS gridFs = null;
 
    public FileRetriverRepo(DB db) {
        this.db = db;
    }
    
    public DB getNewDBInstance() throws UnknownHostException{
    	MongoClient mongoClient = new MongoClient("localhost");
    	return mongoClient.getDB("filesave");
    }
    
   /* public FileEntity find(String filename) {
    	FileEntity entity = new FileEntity();
    	try{
    	 
    	 gridFs = new GridFS(db);
    	 GridFSDBFile file = gridFs.findOne(filename);
    	 entity.setFileName(file.getFilename());
    	 InputStreamReader reader = new InputStreamReader(file.getInputStream());
    	 BufferedReader br = new BufferedReader(reader);
    	 StringBuffer content = new StringBuffer();
    	 String c = null;
    	 while((c = br.readLine()) != null){
    		 content.append(c);
    	 }
    	 entity.setData(content.toString());
    	}catch(Exception e){
    		 
    	 }
    	
    	
    	return entity;
    }*/
    
    public GridFSDBFile findFile(String filename){
     gridFs = new GridFS(db);
   	 GridFSDBFile file = gridFs.findOne(filename);
   	 return file;
    }
    
    
	public void saveToNewDB(GridFSDBFile inputStream) {
		try{
			gridFs = new GridFS(getNewDBInstance());
	    	gridFs.createFile(inputStream.getInputStream(),inputStream.getFilename()).save();;
	    	
	    	
		}catch(Exception e){
			
		}
		
	}

}
