
public interface IMapper {
	public Object get(String oid);
	public void put(String oid,Object obj);
	public void update(Object obj);
	public void insert(Object obj);
	public void delete(Object obj);
	public void reload(Object obj);
}
