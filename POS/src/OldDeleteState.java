
public class OldDeleteState extends PObjectState{
	public void commit(PersistentObject obj){
		PersistenceFacade.getInstance().delete(obj);
		obj.setSate(OldCleanState.getInstance());
	}
	
	public void rollback(PersistentObject obj){
		PersistenceFacade.getInstance().reload(obj);
		obj.setSate(OldCleanState.getInstance());
		System.out.println("OldDelete RollBack Called");
	}
	public String toString(){
		return "State= OldDelete";
	}
}
