import java.util.Scanner;
import java.time.LocalDate;  
import java.time.Period;  

public class BondObject {
	
	public static void main(String[] args) {
		
		Scanner input =new Scanner(System.in);
		
		//This block asks user to input valation date, then computes settlement date
		System.out.println("Enter valuation date in YYYY-MM-DD format: ");  
		String valdate = input.nextLine();  
		
		//most recent last coupon date.
		System.out.println("Enter last recent coupon date in YYYY-MM-DD format: ");  
		String lastCpnDate = input.nextLine(); 
		LocalDate lastCpnDate0 = LocalDate.parse(lastCpnDate);
		
		//Next coupon date.
		System.out.println("Enter next coupon date in YYYY-MM-DD format: ");  
		String nxtCpnDate = input.nextLine(); 
		LocalDate nxtCpnDate0 = LocalDate.parse(nxtCpnDate);
		
		//maturity date.private final double nominal = 100;
		System.out.println("Enter maturity date in YYYY-MM-DD format: ");  
		String matDate = input.nextLine(); 
		LocalDate matDate0 = LocalDate.parse(matDate);

		//Coupon rate
		System.out.println("Enter coupon rate: ");
		double couponRate = input.nextDouble();
	
		
		//Yield to maturity
		System.out.println("Enter yield to maturity as a NACS rate: ");
		double ytm = input.nextDouble();
	
		
		input.close();
		//Creating the bond calculator object
		BondCalculator getBondPrices = new BondCalculator(valdate,lastCpnDate0,nxtCpnDate0,matDate0,couponRate,ytm);
		
		System.out.println("Dirty price: "+getBondPrices.getDirtyPrice());
		System.out.println("Accrued interest: "+getBondPrices.getAccruedInterest());
		System.out.println("Clean price: "+getBondPrices.getCleanPrice());
		
		
		
		
	}

} 
