
public class ProductSpecification extends PersistentObject {
	private OID oid;
	private int id;
	private String name;
	private String description;
	private Manufacturer manufacturer;	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	int price;

	public ProductSpecification() {
		// TODO Auto-generated constructor stub
	}

	ProductSpecification(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String toString() {
		return "Product ID: " + this.id + ", Name: " + this.name + " Price: " + this.price;
	}

	public OID getOid() {
		return oid;
	}

	public void setOid(OID oid) {
		this.oid = oid;
	}
	
	@Override
	public int hashCode()
	{
	    return PersistentObject.class.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if((obj == null) || (obj.getClass() != this.getClass())) 
		{ 
			return false;
		}
		return true;

		
	}

}
