
public class BDVATAdapter implements IVATCalculator{
	BDVATCalculator bdv;

	
	public int getVATAmount(int saleTotal) {
  		bdv = new BDVATCalculator();
		return (int)(bdv.calculateVATAmount(saleTotal));
	}
	
	
}
