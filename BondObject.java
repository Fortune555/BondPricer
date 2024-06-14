import java.util.Scanner;
import java.time.LocalDate;  
import java.time.Period;  

public class BondObject {
	
	public static void main(String[] args) {
		
		Scanner input =new Scanner(System.in);
		
		//This block asks user to input valation date, then computes settlement date
		System.out.println("Enter valuation date in YYYY-MM-DD format: ");  
		//String valdate = input.nextLine();  
		String valdate = "2024-06-07";
		
		//most recent last coupon date.
		System.out.println("Enter last recent coupon date in YYYY-MM-DD format: ");  
		//String lastCpnDate = input.nextLine(); 
		String lastCpnDate = "2023-12-21";	
		LocalDate lastCpnDate0 = LocalDate.parse(lastCpnDate);
		
		//Next coupon date.
		System.out.println("Enter next coupon date in YYYY-MM-DD format: ");  
		//String nxtCpnDate = input.nextLine(); 
		String nxtCpnDate = "2024-06-21";
		LocalDate nxtCpnDate0 = LocalDate.parse(nxtCpnDate);
		
		//maturity date.private final double nominal = 100;
		System.out.println("Enter maturity date in YYYY-MM-DD format: ");  
		//String matDate = input.nextLine(); 
		String matDate = "2026-12-21";
		LocalDate matDate0 = LocalDate.parse(matDate);

		//Coupon rate
		System.out.println("Enter coupon rate: ");
		//double couponRate = input.nextDouble();
		double couponRate = 0.105;	
		
		//Yield to maturity
		System.out.println("Enter yield to maturity as a NACS rate: ");
		//double ytm = input.nextDouble();
		double ytm = 0.0875;
		
		input.close();
		
		BondCalculator getBondPrices = new BondCalculator(valdate,lastCpnDate0,nxtCpnDate0,matDate0,couponRate,ytm);
		
		//System.out.println("Dates between: "+getBondPrices.numOfDaysBtwn(getBondPrices.lastCouponDate,getBondPrices.nextCouponDate));
		//System.out.println("Last Coupon Date: "+ getBondPrices.lastCouponDate);
		//System.out.println("Next Coupon Date: "+ getBondPrices.nextCouponDate);
				
		//System.out.println("get number of days of Accrued Interest: "+getBondPrices.daysOfAcruedInterest());
		//System.out.println("Settlement Date: "+getBondPrices.SettlementDate());
		System.out.println("Dirty price: "+getBondPrices.getDirtyPrice());
		System.out.println("Accrued interest: "+getBondPrices.getAccruedInterest());
		System.out.println("Clean price: "+getBondPrices.getCleanPrice());
		
		
		
		
	}

}