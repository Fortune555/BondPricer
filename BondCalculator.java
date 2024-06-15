
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.*;   
import java.util.*;   

public class BondCalculator {
    private String valuationDate;
    public LocalDate lastCouponDate;

    public LocalDate nextCouponDate;
    public LocalDate bookDateCloseDate;
    private LocalDate maturityDate;

    private double couponRate;
    private double yieldToMaturity;

    private final double nominal = 100;
    private final double redemptionAmount = 100;
    private long devisor = 365/2;


    public BondCalculator(String valuationDate, LocalDate lastCouponDate,
    					LocalDate nextCouponDate,
    					LocalDate maturityDate, double couponRate,
                          double yieldToMaturity) {

        this.valuationDate = valuationDate;
        this.lastCouponDate = lastCouponDate;
        this.nextCouponDate = nextCouponDate;
        this.maturityDate = maturityDate;
        this.couponRate = couponRate;
        this.yieldToMaturity = yieldToMaturity;
    }
    
    //Settlement date
    public LocalDate SettlementDate() { 	
    	LocalDate valDate = LocalDate.parse(valuationDate); 
		LocalDate settlementDate = valDate.plusDays(3);   	
    	return settlementDate;
    	}	
    
    //Book close date
    public LocalDate bookCloseDate() {  	
		LocalDate bookCloseDate = nextCouponDate.minusDays(10);   	
    	return bookCloseDate;
    	}
 
    //Number of coupons left
    public long numberOfCouponsLeft() {
    	long numberOfCouponsLeft = ChronoUnit.DAYS.between(nextCouponDate,maturityDate)/devisor;
    	return numberOfCouponsLeft;   	
    	}
    
    //indicator function to determine if the bond buyer has a right to the next coupon 
    public int cumEx() {
    	int cumEx;
    	if(SettlementDate().isBefore(bookCloseDate())) {
			cumEx = 1;	
			}else {
				cumEx = 0;
			}
    	return cumEx;
    	}
   
    //Number of days that interest is accrued
    public long daysOfAcruedInterest() {
    	long daysOfAcruedInterest;
		if(cumEx()==1) {		
			daysOfAcruedInterest = ChronoUnit.DAYS.between(lastCouponDate,SettlementDate());
			}else {	
				daysOfAcruedInterest = ChronoUnit.DAYS.between(SettlementDate(),nextCouponDate);
				}
		return daysOfAcruedInterest;
    		}
    
    //Coupon amount
    public double couponAmount() {
    	double couponAmount = nominal*couponRate/2;
    	return couponAmount;
    	}
    
    //first coupon amount
    public double firstCouponAmount() {
    	double firstCouponAmount = cumEx()*couponAmount();
    	return firstCouponAmount;
    	}
    
    //get semi annual discount factors
    public double semiAnualDiscountFactor() {
    	double semiAnualDiscountFactor = 1/(1+yieldToMaturity/2);
    	return semiAnualDiscountFactor;
    	}
    
    //get tau
    public double tau(){
    	double tau =0;	
		if(nextCouponDate.isBefore(maturityDate)) {			
			tau = (double) ChronoUnit.DAYS.between(SettlementDate(), nextCouponDate)/ChronoUnit.DAYS.between(lastCouponDate,nextCouponDate);			
			
			}else{
				tau = ChronoUnit.DAYS.between(SettlementDate(),nextCouponDate)/182.5;
				}
        return tau; 
    }
    
   // get period discount factor 
   public double  periodDiscountFactor() {
	   	double periodDiscountFactor = 0;
		if(nextCouponDate.isBefore(maturityDate)) {
			periodDiscountFactor = Math.pow(semiAnualDiscountFactor(), tau());   
			}else if (nextCouponDate.isEqual(maturityDate)) {
				periodDiscountFactor = 1/(1+(yieldToMaturity/2)*tau());
				}  
			return periodDiscountFactor;
   			}
   
   //Get Accrued interest
   public double getAccruedInterest(){
	   double accruedInterest = nominal*couponRate*daysOfAcruedInterest()/365;
       return accruedInterest;
   }
   
   //Get dirty price
    public double getDirtyPrice(){
    	double midValue = (1-Math.pow(semiAnualDiscountFactor(),numberOfCouponsLeft()))/(1-semiAnualDiscountFactor());
    	double value = firstCouponAmount() + couponAmount()*semiAnualDiscountFactor()*midValue + redemptionAmount*Math.pow(semiAnualDiscountFactor(),numberOfCouponsLeft());
    	double allInPrice = periodDiscountFactor()*value;
        return allInPrice;
    }

    //get clean price
    public double getCleanPrice(){
        double cleanPrice = getDirtyPrice()-getAccruedInterest();
        return cleanPrice;
    }
	
    // Calculate difference between dates
    public long numOfDaysBtwn(LocalDate date1, LocalDate date2){
        //calculates the amount of time between two dates and returns the years
    	
        if ((date1 != null) && (date2 != null)){
        	
            return ChronoUnit.DAYS.between(date1, date2);
        }
        else{
        	
            return 0;
        	}
        
    	}
    
}
