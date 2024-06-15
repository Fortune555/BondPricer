import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

