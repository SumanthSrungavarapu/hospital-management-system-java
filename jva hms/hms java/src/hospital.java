import javax.swing.*;
import java.awt.Component;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class hospital extends JFrame implements ActionListener {

	JButton appointment = new JButton("Book a appointment");
	JButton schedule = new JButton("Doctors/Hospital Schedule");
	JButton login = new JButton("Doctor Login");

	public static void main(String[] args) {
		hospital h = new hospital();
	}

	hospital() {
		setVisible(true);
		setLayout(null);
		setSize(1000, 650);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		setTitle("Hospital Management System");

		appointment.setBounds(300, 80, 300, 100);
		schedule.setBounds(300, 280, 300, 100);
		login.setBounds(300, 470, 300, 100);
		appointment.setFocusable(false);
		schedule.setFocusable(false);
		login.setFocusable(false);

		schedule.addActionListener(this);
		login.addActionListener(this);
		appointment.addActionListener(this);

		add(appointment);
		add(schedule);
		add(login);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == schedule) {
			Schedule s = new Schedule();
		}
		if (e.getSource() == login) {
			doctor d = new doctor();
		}

		if (e.getSource() == appointment) {
			int x = JOptionPane.showConfirmDialog(null, "are you an existing patient?", "info",
					JOptionPane.YES_NO_OPTION);
			if (x == 0) {
				Appointment a = new Appointment(x);
			}
			if (x == 1) {

				Appointment a = new Appointment(x);
			}
		}

	}
}