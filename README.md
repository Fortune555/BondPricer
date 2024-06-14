# BondPricer
## Overview

The `BondCalculator` class is designed to calculate various financial metrics related to bonds, such as the settlement date, book close date, number of coupons left, accrued interest, and bond pricing. This class uses `LocalDate` for date manipulations and provides methods for calculating important bond-related values.

## Features

- Calculate the bond's settlement date
- Determine the book close date
- Calculate the number of coupons left until maturity
- Check if the bond buyer has the right to the next coupon (cumEx)
- Calculate the number of days interest has accrued
- Calculate the coupon amount and first coupon amount
- Get semi-annual discount factors
- Calculate tau (a factor related to time between coupon payments)
- Calculate period discount factors
- Calculate accrued interest
- Calculate dirty price and clean price of the bond

## Usage

### Class Initialization

To use the `BondCalculator` class, initialize it with the following parameters:

- `valuationDate` (String): The date of bond valuation in `yyyy-MM-dd` format.
- `lastCouponDate` (LocalDate): The date of the last coupon payment.
- `nextCouponDate` (LocalDate): The date of the next coupon payment.
- `maturityDate` (LocalDate): The date when the bond matures.
- `couponRate` (double): The coupon rate of the bond.
- `yieldToMaturity` (double): The yield to maturity of the bond.

```java
import java.time.LocalDate;

BondCalculator bondCalculator = new BondCalculator(
    "2024-06-14",  // Valuation date
    LocalDate.of(2024, 3, 14),  // Last coupon date
    LocalDate.of(2024, 9, 14),  // Next coupon date
    LocalDate.of(2025, 9, 14),  // Maturity date
    0.05,  // Coupon rate
    0.03   // Yield to maturity
);
```

### Methods

- `LocalDate SettlementDate()`: Calculates and returns the settlement date.
- `LocalDate bookCloseDate()`: Calculates and returns the book close date.
- `long numberOfCouponsLeft()`: Calculates and returns the number of coupons left until maturity.
- `int cumEx()`: Determines if the bond buyer has the right to the next coupon.
- `long daysOfAcruedInterest()`: Calculates and returns the number of days interest has accrued.
- `double couponAmount()`: Calculates and returns the coupon amount.
- `double firstCouponAmount()`: Calculates and returns the first coupon amount.
- `double semiAnualDiscountFactor()`: Calculates and returns the semi-annual discount factor.
- `double tau()`: Calculates and returns tau.
- `double periodDiscountFactor()`: Calculates and returns the period discount factor.
- `double getAccruedInterest()`: Calculates and returns the accrued interest.
- `double getDirtyPrice()`: Calculates and returns the dirty price of the bond.
- `double getCleanPrice()`: Calculates and returns the clean price of the bond.
- `long numOfDaysBtwn(LocalDate date1, LocalDate date2)`: Calculates the number of days between two dates.

### Example Usage

```java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        BondCalculator bondCalculator = new BondCalculator(
            "2024-06-14", 
            LocalDate.of(2024, 3, 14), 
            LocalDate.of(2024, 9, 14), 
            LocalDate.of(2025, 9, 14), 
            0.05, 
            0.03
        );

        LocalDate settlementDate = bondCalculator.SettlementDate();
        System.out.println("Settlement Date: " + settlementDate);

        LocalDate bookCloseDate = bondCalculator.bookCloseDate();
        System.out.println("Book Close Date: " + bookCloseDate);

        long couponsLeft = bondCalculator.numberOfCouponsLeft();
        System.out.println("Number of Coupons Left: " + couponsLeft);

        int cumExStatus = bondCalculator.cumEx();
        System.out.println("CumEx Status: " + cumExStatus);

        long accruedDays = bondCalculator.daysOfAcruedInterest();
        System.out.println("Days of Accrued Interest: " + accruedDays);

        double accruedInterest = bondCalculator.getAccruedInterest();
        System.out.println("Accrued Interest: " + accruedInterest);

        double dirtyPrice = bondCalculator.getDirtyPrice();
        System.out.println("Dirty Price: " + dirtyPrice);

        double cleanPrice = bondCalculator.getCleanPrice();
        System.out.println("Clean Price: " + cleanPrice);
    }
}
```

## Dependencies

- Java 8 or higher
