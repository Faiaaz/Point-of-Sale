


//---------CANCELLED It's Implementation due to Complexity of Hashing--------------------------------


import java.util.UUID;

public class OID {
	String oid;
	UUID uuid;
	
	public OID() {
		uuid = UUID.randomUUID();
		oid = uuid.toString();
		
	}
	
	public OID(String s) {
		this.oid = s;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	
	public void setOid(int oid) {
		this.oid = String.valueOf(oid);
	}
	
	
	@Override
	public int hashCode()
	{
	    return OID.class.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		OID check = (OID)obj;
		
		if((obj == null) || (obj.getClass() != this.getClass())) 
		{ 
			return false;
		}
		
		if(this.oid!=check.getOid())
			return false;
		return true;
		
	}


	
	
	
}
