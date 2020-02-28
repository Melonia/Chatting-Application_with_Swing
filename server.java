import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class server {

   protected static boolean cont=true; 
   static Socket[] client_socket=new Socket[100]; //An array of sockets to connect to multiple clients.
   static PrintWriter[] out=new PrintWriter[100]; //An array of output stream to connect to multiple clients.
   static int i=0; //Client's index.
   static String[] user_set=new String[100]; 
   
   public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

	   ServerSocket server_socket=null; 

       String port="5000";
       
       //Create server socket.
       try {server_socket=new ServerSocket(Integer.parseInt(port));} 
       catch(IOException e) {System.out.println("failed to create server socket!");}
       System.out.println("create server socket!");

       //Wait for the client to connect, and when connected, create a thread.
       while(cont) {
    	   
    	   System.out.println("waiting for connection...");
     
    	   //Wait for the client.
    	   client_socket[i]=server_socket.accept();
           
    	   //Create output stream for the connected client.
    	   out[i]=new PrintWriter(new OutputStreamWriter(
    			   client_socket[i].
               getOutputStream(),"UTF-8"),true);
           
    	   //Create a thread.
           new threadex(client_socket[i],i).start();
           
           //Increase the index.
           i++;
           
       } 
      
      System.out.println("shut down the server!!!");
      server_socket.close();
   }
   
}

