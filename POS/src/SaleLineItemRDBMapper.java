import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

public class SaleLineItemRDBMapper extends AbstractRDBMapper{
	
	 public SaleLineItemRDBMapper(String tableName) {
		super(tableName);
	}
	
	@Override
	protected Object getObjectFromRecord(String oid, ResultSet DBRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void putObjectInStrorage(String oid,Object obj) {
		
		SaleLineItem sli = (SaleLineItem)obj;
		
				
		String subTotal = String.valueOf(sli.getSubtotal());
		String name = sli.getPs().getName();
		int id = sli.getPs().getId();
		int quantity = sli.quantity;
		
		try{		
					
			st= (Statement) dbc.con.createStatement();  			  
			st.executeUpdate("INSERT INTO "+tableName+" (item_id,name,quantity,subtotal) VALUES ("+id+",'"+name+"',"+quantity+",'"+subTotal+"')");  
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	

	@Override
	protected void updateObjectInStorage(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void insertIntoStorage(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void deleteFromStorage(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void reloadObjectFromStorage(Object obj) {
		// TODO Auto-generated method stub
		
	}


	

}
