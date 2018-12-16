
public class NewState extends PObjectState {
	public void commit(PersistentObject obj){
		PersistenceFacade.getInstance().insert(obj);
		obj.setSate(OldCleanState.getInstance());
		System.out.println("NewState Commit Called");
	}
}
