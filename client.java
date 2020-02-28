import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class client {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		
		//Connect database.
		dbconnection dbc=new dbconnection();
	    Connection con=dbc.makeConnection();
		Statement stmt=con.createStatement();
		
		//Communication-related settings.
		Socket client=null; 
		BufferedReader in=null;
	    PrintWriter out=null;
		ResultSet db_dataset=null;
		String server_ip="192.168.180.53";
		String server_port="5000";
		//Create login page.
	    event event=new event();
	    
	    //Variables to check if id,password matches.
	    boolean id=false;
		boolean password=false;
		
		//Store the name of the matching id, password. 
	    String name=null;
	    
	    //Save values from the chatting_user table in database.
	    db_dataset=stmt.executeQuery("SELECT"+" "+"*"+" "+"from"+" "+"chatting_user"+";");
	    
	    //Check if id,password matches.
	    while(id==false||password==false) {
	        System.out.print("");
	    	
	        if(event.id!=null) {
	        
	    		db_dataset.beforeFirst();//Reset result set.
	       
	    		while(db_dataset.next()) {
	        	    id=false;
	    	        password=false;
	    			if(event.id.equals( db_dataset.getString(1))) {id=true;} 
	            	if(event.password.equals( db_dataset.getString(2))) {password=true;}
	            	if(id==true&&password==true) break;
	    		}
	       
           if(id==true&&password==true) {
        	   name=db_dataset.getString(3);
           }
           
	     }
	    	
	   }
	    
	    //Try to connect to the server.
	    try {
			client=new Socket();//Create empty socket.
			System.out.println("try to connect with a server...");
			client.connect(new InetSocketAddress(server_ip,Integer.parseInt(server_port)),3000);//Request server connect.
			System.out.println("connection success!");
		} catch(Exception e) {System.out.println("connection failed!");}
	    
		//Create an I/O stream.
		in=new BufferedReader(
	   		new InputStreamReader(client.getInputStream(),"UTF-8"));//소켓의 입력스트림을 가져와 객체 생성
		out=new PrintWriter(new OutputStreamWriter(
				client.getOutputStream(),"UTF-8"),true);//출력스트림 생성, true면 버퍼 자동 비움
			
		//Create chat window.
		event=new event(out,server_port,con,name);
		
		//Get chat logs from the database.
		db_dataset=stmt.executeQuery("SELECT"+" "+"*"+" "+"from"+" "+"chatting_log"+
		    " "+"where port="+"\'"+server_port+"\'"+"ORDER BY `id` ASC"+";");	 
		
		String log=null;
		
		//Print out the chat logs on client's chat window.
		while(db_dataset.next()) {
		    log=event.text_display.getText();
		    if(log.length()==0) event.text_display.setText(db_dataset.getString(3)+": "+db_dataset.getString(4)+" ["+db_dataset.getString(5)+"]");
		    else event.text_display.setText(log+"\n"+db_dataset.getString(3)+": "+db_dataset.getString(4)+" ["+db_dataset.getString(5)+"]");
		 }
		event.text_display.setText(log+"\n"+"----The above is a chat log from the DB----");
		
		//Send client's name to server.
		out.println(name);

		String msg;
		
		//Receive message from the server and print out it on chat window.
		while(in!=null) {
			
			msg=in.readLine();
			
			//Online or offline user check
			if(msg.contains("%")) {
			
				String[] user_set=msg.split("%");
			    String user=new String();
			   	
			    for(int i=0;i<user_set.length;i++) {
			   		if(user==null) {
			   			user=user_set[i];
			   		}
			   		else {
			   		user=user.concat(user_set[i]);
			   		}
		    	}
			    
			   	event.gui_user.setText(user);   	
			    
			}
			
			else {
			log=event.text_display.getText();
		    if(log.length()==0) event.text_display.setText(msg);
		    else event.text_display.setText(log+"\n"+msg);
			}
		}
			
		System.out.println("shut down the client!");
		in.close();
		out.close();
		client.close();
		
	}
	
}