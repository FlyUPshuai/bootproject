package com.demo.util;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;


public class DbUtil {

//	String filePath = 
//	private static String host;	
//	private static int port;	
//	private static String database;
	
    private static MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
    private static DB db=null;
    public DB getDb(){
        if (db==null){
            this.db=mongoClient.getDB("mydb");
        }
        return this.db;
    }

    public  MongoClient getClient(){
        if(mongoClient==null){
            this.mongoClient=new MongoClient("localhost" , 27017 );
        }
        return this.mongoClient;
    }
    public List<String> find(BasicDBObject condition, BasicDBObject key, String index) {
        
    	List<String> cList=new ArrayList<String>();
        DBCursor find = getDb().getCollection("LBUserLoginLog").find(condition, key);

        while (find.hasNext()) {
           String code=find.next().get(index).toString().trim();
            code=code.replace("[ \"","").replace("\"]", "");

            cList.add(code);
        }
        return cList;
    }
    
    public String getLoginStrFormat(Long loginTime){
    	String day = (loginTime/86400)>0?(loginTime/86400)+"天":"";
    	String hour = (loginTime%86400/3600)>0?(loginTime%86400/3600)+"小时":"";
    	String minute = (loginTime%86400%3600/60)>0?(loginTime%86400%3600/60)+"分":"";
    	String sec = (loginTime%86400%3600%60)>0?(loginTime%86400%3600%60)+"秒":"";
    	String loginStr = day+hour+minute+sec;
    	return loginStr;
    }

}
