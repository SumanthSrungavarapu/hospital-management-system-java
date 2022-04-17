import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.Enumeration;

public class Appointment extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection con;
	PreparedStatement st;

	JLabel name = new JLabel("Enter your name:");
	JTextField jname = new JTextField();
	JLabel age = new JLabel("Enter your age:");
	JTextField jage = new JTextField();
	JLabel gender = new JLabel("Gender:");
	ButtonGroup group = new ButtonGroup();
	JRadioButton male = new JRadioButton("Male");
	JRadioButton female = new JRadioButton("Female");
	JRadioButton others = new JRadioButton("Others");
	JLabel weight = new JLabel("Enter your weight:");
	JTextField jweight = new JTextField();
	JLabel height = new JLabel("Enter your height(in cms):");
	JTextField jheight = new JTextField();
	String[] depts = { "General", "Cardiology", "Dental", "Dermatalogy", "Neurology", "E.N.T.", "Opthamalogy" };
	JLabel disease = new JLabel("Enter your disease,sypmtoms:");
	JTextField jdisease = new JTextField();
	JComboBox combo = new JComboBox(depts);
	JLabel jdept = new JLabel("select related department");
	JLabel phn = new JLabel("Enter your phone number:");
	JTextField jphn = new JTextField();
	JLabel email = new JLabel("Enter your email:");
	JTextField jemail = new JTextField();
	JButton submit = new JButton("SUBMIT");
	public static int x;
	public static String tid;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
//			Statement st=con.createStatement();
//			
//			String query="select name from patient where id=1";
//			ResultSet rs=st.executeQuery(query);
//			rs.next();
//			String name=rs.getString("name");
//			System.out.println(name);
//			st.close();
//			con.close();
	}

	Appointment(int y) {

		setVisible(true);
		setLayout(null);
		setSize(1000, 650);
		setTitle("Appointment");
		x = y;

		name.setBounds(100, 50, 300, 35);
		jname.setBounds(300, 50, 300, 35);
		age.setBounds(100, 100, 300, 35);
		jage.setBounds(300, 100, 300, 35);
		group.add(male);
		group.add(female);
		group.add(others);
		gender.setBounds(100, 150, 300, 35);
		male.setBounds(300, 150, 100, 50);
		female.setBounds(400, 150, 100, 50);
		others.setBounds(500, 150, 100, 50);
		weight.setBounds(100, 200, 300, 35);
		jweight.setBounds(300, 200, 300, 35);
		height.setBounds(100, 250, 300, 35);
		jheight.setBounds(300, 250, 300, 35);
		disease.setBounds(100, 300, 300, 35);
		jdisease.setBounds(300, 300, 300, 35);
		jdept.setBounds(100, 350, 300, 35);
		combo.setBounds(300, 350, 300, 35);
		phn.setBounds(100, 400, 300, 35);
		jphn.setBounds(300, 400, 300, 35);
		email.setBounds(100, 450, 300, 35);
		jemail.setBounds(300, 450, 300, 35);
		submit.setBounds(375, 550, 100, 35);

		submit.addActionListener(this);

		add(name);
		add(jname);
		add(age);
		add(jage);
		add(gender);
		add(male);
		add(female);
		add(others);
		add(weight);
		add(height);
		add(jweight);
		add(jheight);
		add(phn);
		add(jphn);
		add(email);
		add(jemail);
		add(combo);
		add(jdept);
		add(disease);
		add(jdisease);
		add(submit);

		if (x == 0) {
			tid = JOptionPane.showInputDialog("Enter patient id ");
			databs();
			try {
				ResultSet rs;
				String gen;
				st = con.prepareStatement("select * from patient where id=?");
				st.setString(1, tid);
				rs = st.executeQuery();
				rs.next();
				jname.setText(rs.getString("name"));
				jage.setText(rs.getString("age"));
				jweight.setText(rs.getString("weight"));
				gen = rs.getString("gender").toString();
				if (gen.equals("Male")) {
					male.doClick();
				}
				if (gen.equals("Female")) {
					female.doClick();
				} else
					others.doClick();
				jheight.setText(rs.getString("height"));
				jdisease.setText(rs.getString("disease"));
				combo.setSelectedItem(rs.getString("department"));
				jphn.setText(rs.getString("phone"));
				jemail.setText(rs.getString("email"));

				jname.setEditable(false);
				jphn.setEditable(false);
				jemail.setEditable(false);
				male.setEnabled(false);
				female.setEnabled(false);
				others.setEnabled(false);
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
	
	public String book(String id) {
		databs();
		try {
			ResultSet rs;
			st = con.prepareStatement("select * from patient where id=?");
			
			st.setString(1, id);

			rs=st.executeQuery();
			rs.next();
			String dt=rs.getString("department");
			st = con.prepareStatement("select * from doctor where department=? and status='open';");
			st.setString(1, dt);
			rs=st.executeQuery();
			if(rs.next()) {
				return "successful";
			}
			else {
				return "failed (no doctors available";
			}
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return "";
	}

	@Override
	public void actionPerformed(ActionEvent e) {

//		else {
		if (e.getSource() == submit) {
			if (x == 1) {
				String pname = jname.getText();
				String page = jage.getText();
				String pgender = "Male";
				String pweight = jweight.getText();
				String pheight = jheight.getText();
				String pphn = jphn.getText();
				String pemail = jemail.getText();
				String pdept = combo.getSelectedItem().toString();
				String pdisease = jdisease.getText();
				for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();

					if (button.isSelected()) {
						pgender = button.getText();
						break;
					}
				}
				databs();
				try {
					ResultSet rs;
					st = con.prepareStatement(
							"insert into patient (name,age,weight,gender,height,disease,department,phone,email) values(?,?,?,?,?,?,?,?,?);");
					st.setString(1, pname);
					st.setString(2, page);
					st.setString(3, pweight);
					st.setString(4, pgender);
					st.setString(5, pheight);
					st.setString(6, pdisease);
					st.setString(7, pdept);
					st.setString(8, pphn);
					st.setString(9, pemail);
					st.executeUpdate();

					st = con.prepareStatement("select id from patient order by id desc limit 1");
					rs = st.executeQuery();
					rs.next();
					String id = rs.getString("id");
					String res=book(id);
					JOptionPane.showMessageDialog(null, "Your patient id is: " + id+"\nAppointment:"+res);
					
				} catch (Exception ee) {
					System.out.println(ee);
				}

				dispose();

			}
			if (x == 0) {
				String page = jage.getText();
				String pweight = jweight.getText();
				String pheight = jheight.getText();
				String pdept = combo.getSelectedItem().toString();
				String pdisease = jdisease.getText();
				databs();
				try {
					ResultSet rs;
					st = con.prepareStatement(
							"update patient set age=?,weight=?,height=?,department=?,disease=? WHERE (id=?);");
					st.setString(1, page);
					st.setString(2, pweight);
					st.setString(3, pheight);
					st.setString(4, pdept);
					st.setString(5, pdisease);
					st.setString(6, tid);

					st.executeUpdate();
					String res=book(tid);
					JOptionPane.showMessageDialog(null, "Your patient id is: " + tid+"\nAppointment:"+res);
				
				} catch (Exception ee) {
					System.out.println(ee);
				}
				dispose();

			}
		}
//	}
	}
}