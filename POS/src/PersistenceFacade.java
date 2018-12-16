import java.util.HashMap;

public class PersistenceFacade {
	public static PersistenceFacade instance;
	
	HashMap<Class<?>,IMapper> mappers = new HashMap<Class<?>,IMapper>();
	
	public PersistenceFacade() {
		mappers.put(ProductSpecification.class,new ProductSpecificationRDBMapper("productspecification"));	
		mappers.put(SaleLineItem.class, new SaleLineItemRDBMapper("salelineitems"));
		
		
	}
	
	
	public static PersistenceFacade getInstance(){
		if(instance==null){
			instance = new PersistenceFacade();
		}
		return instance;
	}
	
	public Object get(String oid,Class<?> persistanceClass){
		IMapper mapper=null;
		
		mapper = (IMapper)mappers.get(persistanceClass);
		
		return mapper.get(oid);
		
		
		
		
	}
	
	public void put(String oid,Object obj){
		IMapper mapper = null;
		
		mapper = (IMapper) mappers.get(obj.getClass());
		
		mapper.put(oid, obj);
		
	}


	public void update(Object obj) {
		IMapper mapper = null;
		
		mapper = (IMapper) mappers.get(obj.getClass());
		
		mapper.update(obj);
		
	}


	public void insert(Object obj) {
		IMapper mapper = null;
		
		mapper = (IMapper) mappers.get(obj.getClass());
		
		mapper.insert(obj);
		
	}


	public void delete(Object obj) {
		IMapper mapper = null;
		
		mapper = (IMapper) mappers.get(obj.getClass());
		
		mapper.delete(obj);
		
	}


	public void reload(Object obj) {
	IMapper mapper = null;
		
		mapper = (IMapper) mappers.get(obj.getClass());
		
		mapper.reload(obj);
		
	}
	
	
}
