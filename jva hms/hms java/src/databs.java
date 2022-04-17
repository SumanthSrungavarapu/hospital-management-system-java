import java.sql.*;
public class databs {
	
	public static void main(String[] args) throws Exception {
		String url="jdbc:mysql://localhost:3306/hms";
		String uname="root";
		String pass="sumanth";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pass);
		Statement st=con.createStatement();
		
		String query="select name from patient where id=1";
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String name=rs.getString("name");
		System.out.println(name);
		st.close();
		con.close();
	}

}
