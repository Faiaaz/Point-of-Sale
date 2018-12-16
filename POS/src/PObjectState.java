
public class PObjectState {
	static PObjectState instance;
	
	public void commit(PersistentObject obj){
		
	}
	public void delete(PersistentObject obj){
		
	}
	public void rollback(PersistentObject obj){
		
	}
	
	public void save(PersistentObject obj){
		
	}
	
	public static PObjectState getInstance(){
		if(instance==null){
			instance = new PObjectState();
		}
		return instance;
		
	}
	
	public String toString(){
		return "State= nono";
	}
	
}
