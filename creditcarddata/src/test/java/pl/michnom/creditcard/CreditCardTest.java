package pl.michnom.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {

    @Test
    void itAllowsAssignCredit() {
        //When testing we should focus on observable effects, not how the credit card internally works
        //Useful shortcuts:
        // F2 to automatically jump to the next error proposed by IDE
        // ALT + ENTER to show suggested actions
        // SHIFT double click to switch between recently opened files
        // ctrl + B to jump to selected function declaration directory

        //Arrange
        CreditCard card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(1000));
        //Assert
        assertEquals(
                BigDecimal.valueOf(1000),
                card.getBalance()
        );
    }

    @Test
    void itAllowsAssignCreditV2() {
        //Arrange
        CreditCard card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(1200));
        //Assert
        assert BigDecimal.valueOf(1200).equals(card.getBalance());
        assertEquals(
                BigDecimal.valueOf(1200),
                card.getBalance()
        );
    }

    @Test
    void itDenyCreditBelowThresholdV1() {
        CreditCard card = new CreditCard();
        try {
            card.assignCredit(BigDecimal.valueOf(50));
            fail("Should throw exception");
       } catch (CreditBelowThresholdException e) {
            assertTrue(true);
        }
    }
    @Test
    void itDenyCreditBelowThresholdV2() {
        CreditCard card = new CreditCard();
        //python // lambda x: x + 2
        //java // (x) -> x + 2

        assertThrows(
                CreditBelowThresholdException.class,
                () -> card.assignCredit(BigDecimal.valueOf(10))
        );
    }

    @Test
    void itDenyCreditReassignment() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1000));

        card.pay(BigDecimal.valueOf(900));

        assertEquals(
                BigDecimal.valueOf(100),
                card.getBalance()
        );
    }

    @Test
    void itDeniesWhenNoSufficientFunds() {
        CreditCard card = new CreditCard();
        card.assignCredit(BigDecimal.valueOf(1000));
        card.pay(BigDecimal.valueOf(900));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.pay(BigDecimal.valueOf(200))
        );
    }
}