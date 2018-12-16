import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;


public class ProductSpecificationRDBMapper extends AbstractRDBMapper {

	
	public ProductSpecificationRDBMapper(String tableName) {
		super(tableName);		
		
	}
	
	@Override
	protected Object getObjectFromRecord(String id, ResultSet DBRecord) {
		String name = null;
		String price = null;
		String oid = null;
		
		try {
			while(DBRecord.next()){
				name = DBRecord.getString("name");
				oid = DBRecord.getString("id");
				price = DBRecord.getString("price");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		if(oid==null)
			return null;
		
		ProductSpecification ps = new ProductSpecification();
		
		
		try{
			
			ps.setId(Integer.parseInt(oid));
			ps.setName(name);
			ps.setPrice(Integer.parseInt(price));
			ps.setSate(new OldCleanState());
		}catch(Exception e){
			e.printStackTrace();
		}
		return ps;
		
		
	}

	

	@Override
	protected void putObjectInStrorage(String oid,Object obj) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void updateObjectInStorage(Object obj) {
		ProductSpecification ps = (ProductSpecification)obj;
		int key = ps.getId();
		String name = ps.getName();
		int price = (int)ps.getPrice();
		
		try{					
			st= (Statement) dbc.con.createStatement();  			  
			st.executeUpdate("UPDATE "+tableName+" SET name='"+name+"', price="+price+" WHERE id ="+key);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void insertIntoStorage(Object obj) {
		ProductSpecification ps = (ProductSpecification)obj;
		String name = ps.getName();
		int price = (int)ps.getPrice();
		try{					
			st= (Statement) dbc.con.createStatement();  			  
			st.executeUpdate("INSERT INTO "+tableName+" (name,price) VALUES ('"+name+"',"+price+")");			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	protected void deleteFromStorage(Object obj) {
		ProductSpecification ps = (ProductSpecification)obj;		
		int key = ps.getId();
		try{					
			st= (Statement) dbc.con.createStatement();  			  
			st.executeUpdate("DELETE FROM "+tableName+" WHERE id="+key);			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	protected void reloadObjectFromStorage(Object obj) {
		ProductSpecification ps = (ProductSpecification)obj;
		ps.setSate(new OldCleanState());
		
	}	
	
	
	


	
}
