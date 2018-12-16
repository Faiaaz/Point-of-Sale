
public class SaleLineItem extends PersistentObject{

	ProductSpecification ps;
	

	int quantity;
	



	SaleLineItem(int id, int quantity) {

		this.ps = SaleFactory.getInstance().getProductSpec(id);
		this.quantity = quantity;
		
	}

	int getSubtotal() {

		return this.quantity * this.ps.price;
	}

	public ProductSpecification getPs() {
		return ps;
	}

	public void setPs(ProductSpecification ps) {
		this.ps = ps;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
