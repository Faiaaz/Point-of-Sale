
public class OldDirtyState extends PObjectState{
	public void commit(PersistentObject obj){
		PersistenceFacade.getInstance().update(obj);
		obj.setSate(OldCleanState.getInstance());
	}
	
	public void rollback(PersistentObject obj){
		PersistenceFacade.getInstance().reload(obj);
		obj.setSate(OldCleanState.getInstance());
		System.out.println("OldDirty RollBack Called");
	}
	
	public void delete(PersistentObject obj){		
		obj.setSate(OldDeleteState.getInstance());
	}
	public String toString(){
		return "State= OldDirty";
	}
	
}
