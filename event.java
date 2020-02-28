import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

class event extends JFrame{

	//The value entered in GUI are stored here. and called by the client.
	String id = null; 
	String password = null;
	JTextArea text_display;
	JTextArea gui_user;
    CardLayout layout=new CardLayout();
    
	//Start page + login page.
	event(){
		
		setLayout(layout);
       //*FONT
	     Font font1 = new Font("DX경필고딕B", Font.BOLD, 20);
	      ImageIcon first_back = new ImageIcon("c:\\pic\\first_back.jpg");
	      
	      JPanel panel_start =new JPanel(){
	             public void paintComponent(Graphics g) {
	             Dimension d = getSize();

	                g.drawImage(first_back.getImage(),0,0,d.width,d.height,null);

	             setOpaque(false); 
	            
	             }
	             
	          };
	      
	         panel_start.setLayout(null);
	         panel_start.setBounds(0,0,800,600);
	         
	         JButton start_button = new JButton("시작하기");
	         start_button.setForeground(SystemColor.text);
	         start_button.setBackground(SystemColor.textHighlight);
	         start_button.setBounds(325, 350, 164, 53);
	         panel_start.add(start_button);
	         
	         
        Font font2 = new Font("DX경필고딕B", Font.BOLD, 20);
        
       //*PANEL
        ImageIcon icon = new ImageIcon("c:\\pic\\loginBack.jpg");
     
        JPanel panel = new JPanel() {
         public void paintComponent(Graphics g) {
         Dimension d = getSize();
//         g.drawImage(icon.getImage(),0,0,null);
            g.drawImage(icon.getImage(),0,0,d.width,d.height,null);
         
        
         setOpaque(false); 
         //panel.setBackground(back);//*background color
         }
      };
        
      panel.setLayout(null);
      panel.setForeground(SystemColor.text);
      
       //*LABEL 
       JLabel label_id = new JLabel("ID:"); //Id label.
       JLabel label_password = new JLabel("PASSWORD:"); //Password label.
       label_id.setFont(font1);
       label_password.setFont(font1);
       
       //*TEXTFIELD 
       JTextField text_id = new JTextField(30); //Id text field.
       text_id.setForeground(Color.BLACK);
       JTextField text_password = new JTextField(20); //Password text field.
       text_password.setForeground(Color.BLACK);
       
       
       //*BUTTON 
       JButton button_login = new JButton("LOGIN"); //Login button.
       button_login.setForeground(SystemColor.text);
       button_login.setBackground(SystemColor.textHighlight);
       JButton button_join = new JButton("JOIN"); //Join button.
       button_join.setForeground(SystemColor.text);
       button_join.setBackground(SystemColor.textHighlight);
       //button_login.setBackground(Color.BLACK);
      
       
       //Locate the components.
       label_id.setBounds(203,267,80,30);   text_id.setBounds(357,268,164,30);
       label_password.setBounds(203,331,180,30);  text_password.setBounds(357,332,164,30);
       button_login.setBounds(230,446,100,30); 
       button_join.setBounds(397,446,100,30);

       //Attach the components to the panel.
       panel.add(label_id);panel.add(text_id);
       panel.add(label_password);panel.add(text_password);
       panel.add(button_login);panel.add(button_join);
       
       //Icon
       Toolkit kit=Toolkit.getDefaultToolkit();
       Image logo = kit.getImage("c:\\pic\\logotool.png");
       setIconImage(logo);
       
     //Event handler activated when the button on this frame is pressed.
       
       add("0",panel_start);
       add("1",panel);
    
		ActionListener l=new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource()==button_login) {
					id = text_id.getText();
					password = text_password.getText();
				}
				
				//if 'join button' is pushed, launch a web page for user registration. 
				if(e.getSource()==button_join) {
					String url="http://davichiar1.cafe24.com/join.html";
					try { Desktop.getDesktop().browse(new java.net.URI(url)); } 
					catch (IOException x) { x.printStackTrace(); } 
					catch (URISyntaxException x) { x.printStackTrace(); } 
					} 
				
				if(e.getSource()==start_button) {
					layout.next(this.getContentPane());
				}
				}

			private Container getContentPane() {
				// TODO Auto-generated method stub
				return getRootPane().getContentPane();
			}
		};
		
		start_button.addActionListener(l);
		button_login.addActionListener(l);
		button_join.addActionListener(l);
       //Attach the panel to the frame.
	   setTitle("LOGIN");
       setSize(800,600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       setVisible(true);
       
    }

		 public event(PrintWriter out,String port,Connection con,String name) {
		      Font f1 = new Font("DX경필고딕B", Font.BOLD, 20);
		       //*PANEL
		      ImageIcon chat_back = new ImageIcon("c:\\pic\\chat_back.jpg");
		      Color back = new Color(216,223,203);
		      setTitle("client_name");

		      JPanel panel_send=new JPanel(){
		          public void paintComponent(Graphics g) {
		          Dimension d = getSize();

		             g.drawImage(chat_back.getImage(),0,0,d.width,d.height,null);

		          setOpaque(false); 
		         
		          }
		          
		       };
		   
		      panel_send.setLayout(null);
		      panel_send.setBounds(0,0,800,600);
		      
		      JTextField text_send = new JTextField(20);
		      text_send.setLayout(null);
		      text_send.setBounds(57,429,345,92);
		      text_send.setFont(f1);
		      
		      JButton button_send = new JButton("  ");
		      button_send.setIcon(new ImageIcon("c:\\pic\\send_btn.png"));
		      button_send.setHorizontalAlignment(SwingConstants.LEFT);
		      button_send.setBackground(SystemColor.activeCaption);
		      button_send.setContentAreaFilled(false);
		      
		      button_send.setRolloverEnabled(true);
		      button_send.setForeground(SystemColor.textHighlight);
		      button_send.setBorderPainted(false);
		      button_send.setLayout(null);
		      button_send.setBounds(400,429,98,92);
		      button_send.setFont(f1);
		   
		      panel_send.add(text_send);
		      panel_send.add(button_send);
		      
		      //Create chat window component.
		      text_display = new JTextArea(17,20);
		      text_display.setEditable(false);
		      text_display.setLineWrap(true);
		      text_display.setForeground(Color.BLUE);
		      text_display.setLayout(null);
		      text_display.setBounds(50,400,90,90);
		      text_display.setFont(f1);
		      
		      //Create scroll bar.
		      JScrollPane jsp1=new JScrollPane(text_display);
		      JScrollBar jsb1=new JScrollBar();
		      jsb1=jsp1.getVerticalScrollBar();
		      jsp1.setBounds(50, 60, 458, 350);
		      getContentPane().add(jsp1);
		      getContentPane().add(panel_send);
		            
		            //Create scroll bar.
		      JScrollPane jsp2 = new JScrollPane();
		      JScrollBar jsb2 = new JScrollBar();
		      jsb2=jsp2.getVerticalScrollBar();
		      jsp2.setBounds(543, 30, 198, 433);

		      getContentPane().add(jsp2);
		            
		            //Create using user window.
		      gui_user = new JTextArea();
		      gui_user.setBounds(541, 57, 198, 350);
		      panel_send.add(gui_user);
		      gui_user.setEditable(false);
		      gui_user.setLineWrap(true);
		      gui_user.setForeground(Color.BLUE);
		      gui_user.setFont(f1);
		      
		      //광고 버튼 -> 안보이지만 이미지 누르면 됩니다 
		      JButton btnNewButton = new JButton("   ");
		      btnNewButton.setContentAreaFilled(false);
		      btnNewButton.setIcon(null);
		      btnNewButton.setBorderPainted(false);
		      btnNewButton.setBounds(541, 429, 208, 92);
		      panel_send.add(btnNewButton);
		      
		      Toolkit kit = Toolkit.getDefaultToolkit();

				ActionListener l=new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Go to kpu site.
						if(e.getSource()==btnNewButton) {
							String url="http://www.kpu.ac.kr";
							try { Desktop.getDesktop().browse(new java.net.URI(url)); } 
							catch (IOException x) { x.printStackTrace(); } 
							catch (URISyntaxException x) { x.printStackTrace(); } 
							} 
						}
				};
				btnNewButton.addActionListener(l); 
				
				
				//Event handler that operates when the send button is pressed.
				ActionListener l2=new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//Get message from text field.
						String msg=text_send.getText();
						
					    PreparedStatement pstmt=null;
					    
					    //Get current time to send information to the database.
						String SQL="SELECT NOW()";
						ResultSet rs=null;
					    try {pstmt = con.prepareStatement(SQL);} 
					    catch (SQLException e3) {e3.printStackTrace();}
					    try {rs = pstmt.executeQuery(); rs.next();} 
						catch (SQLException e3) {e3.printStackTrace();}
						
					    //Send chat information to database.
						StringBuilder sql=new StringBuilder();
						sql.append("INSERT INTO chatting_log (port, name, content, time)");
					    sql.append("VALUES (?, ?, ?, ?)");
						
					    try {pstmt=con.prepareStatement(sql.toString());} 
						catch (SQLException e3) {e3.printStackTrace();}
						
						try {pstmt.setString(1,port);} 
						catch (SQLException e1) {e1.printStackTrace();}
						
						try {pstmt.setString(2,name);} 
						catch (SQLException e1) {e1.printStackTrace();}
						
						try {pstmt.setString(3, msg);} 
						catch (SQLException e1) {e1.printStackTrace();}
						
						try {pstmt.setString(4, rs.getString(1));} 
						catch (SQLException e2) {e2.printStackTrace();}
						
						try {pstmt.execute();} 
						catch (SQLException e1) {e1.printStackTrace();}	
						
						//Send message to server while outputs it to the client's chat window. 
						calendar time=new calendar();
						String log=text_display.getText();
						if(log.length()==0) text_display.setText(name+": "+msg+" "+time);
						else text_display.setText(log+"\n"+name+": "+msg+" "+time);
						out.println(msg);
					}
				};
				
				button_send.addActionListener(l2);
				
		      //Icon
		      Image logo = kit.getImage("c:\\pic\\logotool.png");
		      setIconImage(logo);
		            
		      getContentPane().setLayout(null);      
		      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      setSize(800,600);
		      setVisible(true);
		   }

}
