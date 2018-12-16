
public class SeniorCitizenDiscount implements ISalePricingStrategy {
	double percentage = 0.20;
	
	public int getTotal(Sale s){
		return (int)(s.getPreDiscountTotal()-s.getPreDiscountTotal()*percentage);
	}
}
