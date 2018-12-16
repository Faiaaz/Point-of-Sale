import java.util.LinkedList;

public class ProcessSaleController {
	Sale sale;
	SaleFactory sf;
	PricingStrategyFactory psFactory;
	
	public static ProcessSaleController psc;
	

	void makeNewSale() {
		
		sale = new Sale();
		sf = new SaleFactory();	
		psFactory = new PricingStrategyFactory();
	}

	LinkedList<SaleLineItem> getSaleLineItemList() {
		return sale.sli;
	}

	ProductSpecification getProductSpec(int id) {

		return SaleFactory.getInstance().getProductSpec(id);
		

	}

	void addItem(int id, int quantity) {
		sale.addSaleLineItem(id, quantity);
	}
	
	void selectedPricingStrategy(String s){
		psFactory.setPSname(s);
	}
	
	
	
	public static synchronized ProcessSaleController getPscInstance(){
		if(psc==null)
			psc = new ProcessSaleController();	
		return psc;
	}
	
	public void saveEditData(ProductSpecification ps){
		ps.commit();
	}
	
}
