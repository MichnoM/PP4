package pl.michnom.creditcard;

import org.junit.jupiter.api.Test;
import pl.michnom.creditcarddata.CreditCard;
import pl.michnom.creditcarddata.creditBelowThresholdException;

import java.math.BigDecimal;

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
        var card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(1000));
        //Assert
        assert BigDecimal.valueOf(1000).equals(card.getBalance());
    }

    @Test
    void itAllowsAssignCreditV2() {
        //Arrange
        var card = new CreditCard();
        //Act
        card.assignCredit(BigDecimal.valueOf(2000));
        //Assert
        assert BigDecimal.valueOf(2000).equals(card.getBalance());
    }

    @Test
    void itDenyCreditBelowThreshold() {
        var card = new CreditCard();

        try {
            card.assignCredit(BigDecimal.valueOf(50));
            assert false;
        } catch (creditBelowThresholdException e) {
            assert true;
        }
    }
}
