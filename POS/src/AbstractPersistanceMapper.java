import java.util.HashMap;

public abstract class AbstractPersistanceMapper implements IMapper {

	HashMap<String, PersistentObject> cachedObjects = new HashMap<String, PersistentObject>();

	public final Object get(String oid) {
		Object obj = cachedObjects.get(oid);

		if (obj == null) {

			obj = getObjectFromStorage(oid);
			cachedObjects.put(oid, (PersistentObject) obj);
		}
		return obj;

	}

	public final void put(String oid, Object obj) {
		this.putObjectInStrorage(oid, obj);
	}

	public final void update(Object obj) {
		this.updateObjectInStorage(obj);
	}
	
	public final void insert(Object obj){
		this.insertIntoStorage(obj);
	}
	
	public final void delete(Object obj){
		this.deleteFromStorage(obj);
	}
	
	public final void reload(Object obj){
		this.reloadObjectFromStorage(obj);
	}
	
	protected abstract void reloadObjectFromStorage(Object obj);
	
	protected abstract void deleteFromStorage(Object obj);
	
	protected abstract void insertIntoStorage(Object obj);

	protected abstract Object getObjectFromStorage(String oid);

	protected abstract void putObjectInStrorage(String oid, Object obj);

	protected abstract void updateObjectInStorage(Object obj);
}
