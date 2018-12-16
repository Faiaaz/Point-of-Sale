
public class MyVATCalculator implements IVATCalculator {
	

	@Override
	public int getVATAmount(int saleTotal) {
		return (int)(saleTotal*.05);
	}
}
