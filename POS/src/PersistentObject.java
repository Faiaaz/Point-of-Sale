
public class PersistentObject {
	 PObjectState state;
	 
	 public void commit(){
		 state.commit(this);
	 }
	 public void delete(){
		 state.delete(this);
	 }
	 public void rollback(){
		 state.rollback(this);
	 }
	 public void save(){
		 state.save(this);
	 }
	 
	 public void setSate(PObjectState state){
		 this.state=state;
	 }
	 
	
}
