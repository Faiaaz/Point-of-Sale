import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SaleFactory {
	//LinkedList<ProductSpecification> psList = new LinkedList<ProductSpecification>();
	ProductSpecification ps;
	

	public static SaleFactory instance;

	IVATCalculator vatCalculator;
	

	ProductSpecification getProductSpec(int id) {
		ProductSpecification ps = null;
		
		String oid = String.valueOf(id);	
		

		ps = (ProductSpecification)PersistenceFacade.getInstance().get(oid,ProductSpecification.class);
		
		return ps;
		
	
	}

	public static synchronized SaleFactory getInstance() {
		if (instance == null)
			instance = new SaleFactory();
		return instance;

	}

	

	public IVATCalculator getVatCalculator() {

		if (vatCalculator == null) {
			this.loadSystemProperties();

			// -------------Getting the vat calculator name defined in
			// configuration file -----------

			String className = System.getProperty("vatcalculator.class.name");

			try {
				vatCalculator = (IVATCalculator) Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return vatCalculator;
	}

	public void loadSystemProperties() {

		FileInputStream propFile = null;

		try {
			propFile = new FileInputStream("vatcalculator.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Properties p = new Properties(System.getProperties());

		try {
			p.load(propFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// Setting the system property that we just loaded along with default
		// system properties

		System.setProperties(p);
	}

}
