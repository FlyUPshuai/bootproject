package com.demo.dao.impl;

import com.demo.dao.UserDao;
import com.demo.entity.Page;
import com.demo.entity.User;
import com.demo.util.DbUtil;
import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;


public class UserDaoImp implements UserDao{
    static DbUtil util=new DbUtil();
    static DBCollection userCollection=util.getDb().getCollection("LBUserLoginLog");

    @Override
    public List<String> getComputers(int productID) {

        BasicDBObject ob= new BasicDBObject("productId",productID);
        BasicDBObject key= new BasicDBObject("hardwareCodes",1);//指定需要显示列

        return util.find(ob,key,"hardwareCodes");
    }

    @Override
    public String getUsers(int productID) {
    	
    	DBObject matchBasicDBObjet = new BasicDBObject("$match",new BasicDBObject("productId",productID));
        DBObject groupFields = new BasicDBObject("_id", new BasicDBObject("username", "$username"));  
        groupFields.put("count", new BasicDBObject("$sum", 1));
        DBObject group = new BasicDBObject("$group", groupFields);         
        DBObject projectBasicDBObjet = new BasicDBObject("$project",  
	              new BasicDBObject("_id",0).append("username", "$_id.username").append("count", 1)); 
        List<DBObject> list = new ArrayList<DBObject>();  
        list.add(matchBasicDBObjet);  
        list.add(group);
        list.add(projectBasicDBObjet);
        AggregationOutput outputTemp = userCollection.aggregate(list);
        Iterable<DBObject> tempResult = outputTemp.results();
        int count = 0;  //
        for(DBObject dbObject:tempResult){
        	 count++;
        }
        return count+"";       
        
    }
	@Override
    public List<User> getUserList(int productId,String mixTime,Page page) {		

	        Long mixT = Long.parseLong(mixTime)*3600000; //小时转变为ms
	        DBObject matchBasicDBObjet = new BasicDBObject("$match",new BasicDBObject("productId",productId));  
	        /* Group操作*/ 
	        String[] subtr_array = {"$logoutTime","$loginTime"};
	        DBObject groupFields = new BasicDBObject("_id", new BasicDBObject("username", "$username").append("productId", "$productId"));  
	        groupFields.put("count", new BasicDBObject("$sum", new BasicDBObject("$subtract",subtr_array)));  //日期是ms级别的
	        DBObject group = new BasicDBObject("$group", groupFields);  
	          
	        DBObject matchBasicDBObjet2 = new BasicDBObject("$match",new BasicDBObject("count", new BasicDBObject("$gte",mixT))); 
	            
	        // 排序操作  
	        DBObject sortchBasicDBObjet = new BasicDBObject("$sort", new BasicDBObject("count",-1));
	        DBObject skipchBasicDBObjet = new BasicDBObject("$skip",(page.getPage() - 1) * page.getPageSize());
	        DBObject limitBasicDBObjet = new BasicDBObject("$limit",page.getPageSize());  
	        // project 操作  
	        DBObject projectBasicDBObjet = new BasicDBObject("$project",  
	              new BasicDBObject("_id",0).append("username", "$_id.username").append("pid", "$_id.productId").append("count", 1));  	          
	        List<DBObject> list = new ArrayList<DBObject>();  
	        list.add(matchBasicDBObjet);  
	        list.add(group); 
	        list.add(matchBasicDBObjet2);
	        list.add(sortchBasicDBObjet);
	        list.add(projectBasicDBObjet);
	        
	        AggregationOutput outputTemp = userCollection.aggregate(list);
	        Iterable<DBObject> tempResult = outputTemp.results();
	        int pageSum = 0;
	        for(DBObject dbObject:tempResult){
	        	pageSum++;
	        }
	        int pages = pageSum%page.getPageSize()>0?pageSum/page.getPageSize()+1:pageSum/page.getPageSize();
	        page.setPageSum(pages);
	        
	        list.add(skipchBasicDBObjet);          
	        list.add(limitBasicDBObjet);  
	            
	        AggregationOutput output = userCollection.aggregate(list);
	        Iterable<DBObject> result= output.results();
	        List<User> userList = new ArrayList<User>();
	        for(DBObject dbObject:result){  
	        	User u = new User();
	        	u.setName(dbObject.get("username").toString());
	        	Long loginTime = Long.parseLong(dbObject.get("count").toString())/1000;  //s级别
	        	String loginStr = util.getLoginStrFormat(loginTime);
	        	u.setLoginTime(loginStr);
	        	userList.add(u);	            
	        }
	          
	        return userList;
    }
}
