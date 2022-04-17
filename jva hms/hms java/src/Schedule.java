import javax.swing.*;
import java.awt.Component;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class Schedule extends JFrame implements ActionListener {
	Connection con;
	PreparedStatement st;
	JLabel time = new JLabel("Hospital timings: 	open    24/7");
	JButton doctor = new JButton("Doctors Schedule");
	JButton timings = new JButton("Lab timings");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Schedule s = new Schedule();
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

	Schedule() {
		setVisible(true);
		setLayout(null);
		setSize(1000, 650);

		setTitle("Schedules");

		time.setBounds(100, 2, 300, 100);
		doctor.setBounds(100, 100, 300, 100);
		timings.setBounds(100, 300, 300, 100);
		doctor.setFocusable(false);
		timings.setFocusable(false);

		add(doctor);
		add(timings);
		add(time);

		doctor.addActionListener(this);
		timings.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == doctor) {
			JFrame temp = new JFrame();
			temp.setVisible(true);
			temp.setLayout(new FlowLayout());
			temp.setSize(1000, 650);
			databs();
			try {
				ResultSet rs;
				st = con.prepareStatement("select id,name,status,department from doctor;");
				rs = st.executeQuery();
				rs.next();
				String[][] rows = { { rs.getString("id"), rs.getString("name"), rs.getString("status"),
						rs.getString("department") } };
				;
//			rs.next();
				for (int i = 0; rs.next(); i++) {
					rs.next();
					String[] data = { rs.getString("id"), rs.getString("name"), rs.getString("status"),
							rs.getString("department") };
					String arrNew[][] = Arrays.copyOf(rows, rows.length + 1);
					arrNew[rows.length] = data;

					if (!rs.next()) {
						rows = arrNew;
					}
				}

//			rows=arrNew;
				String column[] = { "ID", "NAME", "STATUS", "DEPARTMENT" };
				JTable jt = new JTable(rows, column);
				jt.setBounds(30, 40, 200, 300);
				JScrollPane sp = new JScrollPane(jt);
				temp.add(sp);
				temp.setTitle("Docotors Schedule");
				temp.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
			} catch (Exception ee) {
				System.out.println(ee);
			}

		}
		if (e.getSource() == timings) {
			JFrame temp = new JFrame();
			temp.setVisible(true);
			temp.setLayout(new FlowLayout());
			temp.setSize(1000, 650);

			JLabel l = new JLabel("labs open 24/7");
			temp.add(l);
			temp.setTitle("Labs Schedule");
			temp.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		}

	}
}
