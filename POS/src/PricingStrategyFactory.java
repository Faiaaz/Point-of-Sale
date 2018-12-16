
public class PricingStrategyFactory {

	static String psname;
	ISalePricingStrategy pstrat;

	public void setPSname(String s) {
		PricingStrategyFactory.psname = s;
	}

	public ISalePricingStrategy getPricingStrategy() {

		try {
			pstrat = (ISalePricingStrategy) Class.forName(psname).newInstance();

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		return pstrat;

	}
}
