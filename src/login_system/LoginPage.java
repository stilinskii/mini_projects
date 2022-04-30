package login_system;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//view
public class LoginPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton joinButton = new JButton("Join");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("password:");
	JLabel messageLabel = new JLabel();
	
	//to copy loginInfo
	//HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage() {
		
		//logininfo = loginInfoOriginal;
		//copy the loginInfo so that it can be globally available
		
		userIDLabel.setBounds(50,100,75,25);
		userPasswordLabel.setBounds(50,150,75,25);
		
		//bottom
		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
	
		userIDField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		loginButton.setBounds(125, 200, 100, 25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		joinButton.setBounds(225, 200, 100, 25);
		joinButton.setFocusable(false);
		joinButton.addActionListener(this);
		
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(joinButton);
		frame.add(messageLabel);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MemberDAO dao = MemberDAO.getInstance();
		
		if(e.getSource()==joinButton) {
			userIDField.setText("");
			userPasswordField.setText("");
		}
		
		
		if(e.getSource()==loginButton) {
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			int chk=dao.loginChk(userID,password);
			
			if(chk==1) {
					messageLabel.setForeground(Color.green);
					messageLabel.setText("Login successful");
					frame.dispose();
					WelcomePage welcomePage = new WelcomePage(userID);
			}else if(chk==2){
					messageLabel.setForeground(Color.red);
					messageLabel.setText("WRONG PASSWORD");
					userIDField.setText("");
					userPasswordField.setText("");

			}else {
				messageLabel.setForeground(Color.red);
				messageLabel.setText("username not found ");
				userIDField.setText("");
				userPasswordField.setText("");
				
			}
			
			
			
		}
	}
	
	
}
