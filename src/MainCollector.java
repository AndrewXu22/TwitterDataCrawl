import twitter4j.*;

import java.io.*;


public class MainCollector {
      TwitterDataFun fun;
      Twitter me;
      long myid;
      String myScreenName;
	/**
	 * @param args
	 * @throws TwitterException 
	 * @throws  
	 * @throws IOException 
	 */
      
    MainCollector() throws  TwitterException{
       this.fun=new TwitterDataFun();
       this.me=TwitterFactory.getSingleton();
     //this.me.getId();
       this.myid=me.getId();
       this.myScreenName=me.getScreenName();
     //this.me.getScreenName();
    }
    
    
    void initializeFromMe() throws TwitterException, IOException{
  		//TwitterDataFun fun=new TwitterDataFun();
		//fun.searchTweet("yui");
		//fun.tweet("This is a test from twitter4j!");
	//	IDs followerIds=me.getFollowersIDs(-1000);
	//  IDs followingIds=me.getFriendsIDs(-1000);
	    //getMyRelationshipList
    	String fnPrefix=this.myScreenName+this.myid;
		getRelationshipIntoFile(this.myid,fnPrefix);
		
	
	}

    void getRelationshipIntoFile(long midid, String fnPrefix) throws IOException, TwitterException{
    	OutputStream of = new FileOutputStream("D:\\TwitterEvilPlan\\"+fnPrefix+"FollowerList.txt", true);
    	FileReader fileReader=new FileReader("D:\\TwitterEvilPlan\\"+fnPrefix+"FollowerList.txt");
    	BufferedReader brfile=new BufferedReader(fileReader);
    	String empty=brfile.readLine()==null?"":"haveSth.";
    	fileReader.close();
    	long cursor=-1;//initialization
    	
		PagableResponseList<User> followerList;
		do{
			FileReader ifCursor=new FileReader("D:\\TwitterEvilPlan\\followerTmp\\Cursor.txt");
    		BufferedReader br=new BufferedReader(ifCursor);
    		String scursor=br.readLine();
			cursor=Long.parseLong(scursor);
			if(cursor==0 && !empty.equals("")) {
				//System.exit(0);
			   System.out.println("FollowerList done!");
			   break;
			}
			cursor=((cursor==0)?-1:cursor);
			System.out.println("cursor:"+cursor);
			
			followerList= this.me.getFollowersList(midid, cursor);
			for(User user:followerList){
				String id=Long.toString(user.getId());
				of.write(id.getBytes());
				of.write(',');
				of.write(user.getScreenName().getBytes());
				of.write("\r\n".getBytes());
			}
		cursor=followerList.getNextCursor();
		OutputStream ofCursor=new FileOutputStream("D:\\TwitterEvilPlan\\followerTmp\\Cursor.txt");
		scursor=Long.toString(cursor);
		ofCursor.write(scursor.getBytes());
		}while(cursor!=0);
		
		
		
		
		OutputStream of2 = new FileOutputStream("D:\\TwitterEvilPlan\\"+fnPrefix+"FollowingList.txt", true);
		FileReader fileReader2=new FileReader("D:\\TwitterEvilPlan\\"+fnPrefix+"FollowingList.txt");
    	BufferedReader brfile2=new BufferedReader(fileReader2);
    	String empty2=brfile2.readLine()==null?"":"haveSth.";
    	fileReader2.close();
    	long cursor2=-1;//initialization		
		
		PagableResponseList<User> followingList;
		do{
			FileReader ifCursor2=new FileReader("D:\\TwitterEvilPlan\\followingTmp\\Cursor.txt");
    		BufferedReader br2=new BufferedReader(ifCursor2);
    		String scursor2=br2.readLine();
			cursor2=Long.parseLong(scursor2);
			if(cursor2==0 && !empty2.equals("")) {
				//System.exit(0);
				System.out.println("FollowingList done!");
				break;
			}
			cursor2=((cursor2==0)?-1:cursor2);
			System.out.println("cursor2:"+cursor2);
			
			followingList = this.me.getFriendsList(midid, cursor2);
		    for(User user:followingList){
		    	String id=Long.toString(user.getId());
		    	of2.write(id.getBytes());
		    	of2.write(',');
		    	of2.write(user.getScreenName().getBytes());
		    	of2.write("\r\n".getBytes());
		    }
		    cursor2=followingList.getNextCursor();
			OutputStream ofCursor2=new FileOutputStream("D:\\TwitterEvilPlan\\followingTmp\\Cursor.txt");
			scursor2=Long.toString(cursor2);
			ofCursor2.write(scursor2.getBytes());
		}while(cursor2!=0);
		
    }
    
	public static void main(String[] args) throws TwitterException, IOException {
	  MainCollector mc=new MainCollector();
	  mc.initializeFromMe();
	  
	
	
	}	
	
}
