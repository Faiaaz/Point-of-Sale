import java.util.Date;
import java.util.LinkedList;

public class Sale {

	LinkedList<SaleLineItem> sli;
	LinkedList<PropertyListener> listeners;
	String date;
	int saleTotal;
	int vatAmount;
	PricingStrategyFactory psFactory;
	IVATCalculator ivac;
	ISalePricingStrategy pricingStrategy;
	

	Sale() {
		listeners = new LinkedList<PropertyListener>();
		sli = new LinkedList<SaleLineItem>();
		date = new Date().toString();	
		psFactory = new PricingStrategyFactory();
	}

	void addSaleLineItem(int id, int quantity) {
		SaleLineItem item = new SaleLineItem(id, quantity);

		sli.add(item);
		
		//--------------------Adding the item to Database for Future Record---------------
		
		PersistenceFacade.getInstance().put(String.valueOf(item.getPs().getId()),item);
		
		this.setTotal();
		this.setVatAmount();

	}

	int getPreDiscountTotal() {
		int total = 0;

		for (SaleLineItem f : sli) {
			total = total + f.getSubtotal();
		}
		return total;

	}
	
	public void addPropertyListener(PropertyListener lis){
		listeners.add(lis);
	}	
	
	
	public void publishPropertyEvent(String name, int value){
		for(PropertyListener lis:listeners){
			lis.onPropertyEvent(this, name, value);
		}
	}
	
	public void setTotal(){
		this.saleTotal = this.getPreDiscountTotal();
		publishPropertyEvent("sale.total", this.saleTotal);
	}
	
	public void setVatAmount(){
		this.vatAmount = this.getVATAmount();
		publishPropertyEvent("vat.Amount", this.vatAmount);
	}
	
	
	
	int getTotal(){
		pricingStrategy = psFactory.getPricingStrategy();
		return pricingStrategy.getTotal(this);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public int getVATAmount(){
		ivac = SaleFactory.getInstance().getVatCalculator();
		return ivac.getVATAmount(saleTotal);
	}
	
	public int getGrandTotal(){
		return this.getVATAmount()+this.getTotal();
	}
}
