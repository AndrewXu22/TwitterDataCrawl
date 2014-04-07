import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import twitter4j.*;

import java.io.*;

import twitter4j.*;
public class TwitterDataFun {
  void searchTweet(String s){
		try {
			Twitter twitter = TwitterFactory.getSingleton();
		    Query query = new Query(s);
		    QueryResult result;
			result = twitter.search(query);
			for (Status status : result.getTweets()) {
		        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
		    }
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
  }
	
  
  
  void tweet(String s) throws TwitterException{
	  Twitter twitter = TwitterFactory.getSingleton();
	    Status status = twitter.updateStatus(s);
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
  }
  
 
  
  
}
