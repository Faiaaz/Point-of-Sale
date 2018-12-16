import java.sql.ResultSet;
import com.mysql.jdbc.Statement;

public abstract class AbstractRDBMapper extends AbstractPersistanceMapper{
	String tableName;
	ResultSet DBRecord;
	DBConnect dbc;	
	Statement st;
	
	public AbstractRDBMapper(String tableName) {
		this.tableName = tableName;
		dbc = new DBConnect();
		
	}

	@Override
	protected Object getObjectFromStorage(String oid) {
		DBRecord = getDBRecord(oid);
		
		return getObjectFromRecord(oid, DBRecord);
		
	}
	
	
	protected abstract Object getObjectFromRecord(String oid,ResultSet DBRecord);
	

	private ResultSet getDBRecord(String oid){
		
		String key = oid;
		
		
		try {
			

			st= (Statement) dbc.con.createStatement();  
			  
			DBRecord = st.executeQuery("select * from "+tableName+" where id="+key);  
						
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return DBRecord;
		
		
		
		
	}
	protected abstract void insertIntoStorage(Object obj);
	
	protected  abstract void putObjectInStrorage(String oid,Object obj);
	
	protected abstract void updateObjectInStorage(Object obj);

	protected abstract void deleteFromStorage(Object obj);
	
	protected abstract void reloadObjectFromStorage(Object obj);
	
}
	
	

