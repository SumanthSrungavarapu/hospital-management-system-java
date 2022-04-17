import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class doctor extends JFrame implements ActionListener {

	Connection con;
	PreparedStatement st;
	JTextField uid = new JTextField("enter id");
	JTextField stat= new JTextField("enter status");
	JTextField password = new JTextField("enter password");
	JButton submit = new JButton("SUBMIT");
	JButton update = new JButton("UPDATE STATUS");

	doctor() {
		
		setLayout(null);
		setSize(500,750);
//		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		uid.setBounds(150, 25, 200, 35);
		password.setBounds(150, 80, 200, 35);
		submit.setBounds(200, 130, 100, 35);
		stat.setBounds(150, 170, 200, 35);
		update.setBounds(150,220, 190, 35);
		
		add(uid);
		add(password);
		add(submit);
		submit.addActionListener(this);
		
		setVisible(true);
	}

	public void databs() {
		String url = "jdbc:mysql://localhost:3306/hms";
		String uname = "root";
		String pass = "sumanth";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, uname, pass);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void showstatus(String uid) {
		add(update);
		setVisible(true);
		try {
			databs();
			
			ResultSet rs;
			st = con.prepareStatement("select * from doctor where id=?;");
			st.setString(1, uid);
			rs = st.executeQuery();
			rs.next();
			String sts = rs.getString("status");
			stat.setText(sts);
			
			add(stat);
			update.addActionListener(this);

		} catch (Exception ee) {
			System.out.println(ee);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == submit) {
			try {
				databs();
				String userName = uid.getText();
				String pwd = password.getText();
				ResultSet rs;
				st = con.prepareStatement("select * from doctor where id=?;");
				st.setString(1, userName);
				rs = st.executeQuery();
				rs.next();
				if ( rs.getString("password").equals(pwd)) {
					showstatus(userName);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid details");
				}
			} catch (Exception ee) {
				System.out.println(ee);
			}
		}
		if (e.getSource() == update) {
			try {
				databs();
				String userName = uid.getText();

				String sts = stat.getText();
				st = con.prepareStatement("update doctor set status=? where id=?;");
				st.setString(1, sts);
				st.setString(2, userName);
				st.executeUpdate();

				JOptionPane.showMessageDialog(null, "Status updated");
			} catch (Exception ee) {
				System.out.println(ee);
			}
		}
	}
	
}