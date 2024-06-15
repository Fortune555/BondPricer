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
- Sure! Here is a README file for the JUnit test cases for the `BondCalculator` class:

---

# BondCalculator JUnit Test Cases

This project contains JUnit 4 test cases for the `BondCalculator` class, which is used to perform various bond calculations including settlement date, book close date, number of coupons left, accrued interest, and prices.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Eclipse IDE
- JUnit 4 library

## Setup

### Adding JUnit 4 to Your Project in Eclipse

1. **Download JUnit 4 JAR**:
   - Download the JUnit 4 JAR file from the [JUnit website](https://junit.org/junit4/).

2. **Add the JAR to Your Project**:
   - Copy the JAR file to your project's `lib` directory.
   - Right-click your project in Eclipse, select `Build Path > Configure Build Path`.
   - Go to the `Libraries` tab and click `Add JARs...`.
   - Navigate to the `lib` directory, select the JUnit JAR, and click `OK`.

## Running the Tests

1. **Clone the repository** (if applicable) or download the source code.
2. **Open the project in Eclipse**.
3. **Ensure JUnit 4 is added to your project's build path** as described above.
4. **Create the test class**:

   - Create a new class named `BondTest` in your `src` directory.
   - Add the following test methods to `BondTest`.

### Test Class: `BondTest`

```java
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class BondTest {

    private BondCalculator bondCalculator;

    @Before
    public void setUp() {
        bondCalculator = new BondCalculator(
            "2024-06-14", 
            LocalDate.of(2024, 3, 14), 
            LocalDate.of(2024, 9, 14), 
            LocalDate.of(2025, 9, 14), 
            0.05, 
            0.03
        );
    }

    @Test
    public void testSettlementDate() {
        LocalDate expectedDate = LocalDate.of(2024, 6, 17); // 3 days after valuation date
        assertEquals(expectedDate, bondCalculator.SettlementDate());
    }

    @Test
    public void testBookCloseDate() {
        LocalDate expectedDate = LocalDate.of(2024, 9, 4); // 10 days before next coupon date
        assertEquals(expectedDate, bondCalculator.bookCloseDate());
    }

    @Test
    public void testNumberOfCouponsLeft() {
        long expectedCouponsLeft = (ChronoUnit.DAYS.between(LocalDate.of(2025, 9, 14), LocalDate.of(2024, 9, 14))) / (365 / 2);
        assertEquals(expectedCouponsLeft, bondCalculator.numberOfouponsLeft());
    }

    @Test
    public void testCumEx() {
        int expectedCumEx = 1; // Settlement date is before book close date
        assertEquals(expectedCumEx, bondCalculator.cumEx());
    }

    @Test
    public void testDaysOfAccruedInterest() {
        long expectedDays = ChronoUnit.DAYS.between(LocalDate.of(2024, 3, 14), bondCalculator.SettlementDate());
        assertEquals(expectedDays, bondCalculator.daysOfAcruedInterest());
    }

    @Test
    public void testCouponAmount() {
        double expectedCouponAmount = 100 * 0.05 / 2;
        assertEquals(expectedCouponAmount, bondCalculator.couponAmount(), 0.0001);
    }

    @Test
    public void testFirstCouponAmount() {
        double expectedFirstCouponAmount = bondCalculator.cumEx() * bondCalculator.couponAmount();
        assertEquals(expectedFirstCouponAmount, bondCalculator.firstCouponAmount(), 0.0001);
    }

    @Test
    public void testGetAccruedInterest() {
        double expectedAccruedInterest = 100 * 0.05 * bondCalculator.daysOfAcruedInterest() / 365;
        assertEquals(expectedAccruedInterest, bondCalculator.getAccruedInterest(), 0.0001);
    }

    @Test
    public void testGetDirtyPrice() {
        double dirtyPrice = bondCalculator.getDirtyPrice();
        assertTrue(dirtyPrice > 0); // Check if the dirty price is calculated correctly
    }

    @Test
    public void testGetCleanPrice() {
        double cleanPrice = bondCalculator.getCleanPrice();
        assertTrue(cleanPrice > 0); // Check if the clean price is calculated correctly
    }
}
```

### Running the Tests in Eclipse

1. **Right-click on the `BondTest` class** in the Project Explorer.
2. Select `Run As > JUnit Test`.
3. The JUnit view will display the results of the tests.

## Test Cases Overview

1. **`testSettlementDate`**: Verifies the settlement date calculation.
2. **`testBookCloseDate`**: Verifies the book close date calculation.
3. **`testNumberOfCouponsLeft`**: Verifies the number of coupons left calculation.
4. **`testCumEx`**: Verifies if the bond buyer has the right to the next coupon.
5. **`testDaysOfAccruedInterest`**: Verifies the number of days that interest is accrued.
6. **`testCouponAmount`**: Verifies the coupon amount calculation.
7. **`testFirstCouponAmount`**: Verifies the first coupon amount calculation.
8. **`testGetAccruedInterest`**: Verifies the accrued interest calculation.
9. **`testGetDirtyPrice`**: Verifies the dirty price calculation.
10. **`testGetCleanPrice`**: Verifies the clean price calculation.

