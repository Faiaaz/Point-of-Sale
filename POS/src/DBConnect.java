import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DBConnect {		
		
		public static DBConnect instance;
		public Connection con;
		private Statement st;
		private ResultSet rs;

		public DBConnect() {
			try {
				Class.forName("com.mysql.jdbc.Driver");

				con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public ResultSet getData(String query) {
			try {

				st = (Statement) con.createStatement();

				rs = st.executeQuery(query);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return rs;
			
		}
		
		public synchronized static DBConnect getInstance(){
			if(instance==null){
				instance = new DBConnect();
			}
			return instance;
		}
		
}


