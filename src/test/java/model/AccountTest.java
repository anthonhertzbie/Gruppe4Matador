package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    @DisplayName("a balance set is getable")
    void balanceSetGet() {
        Account account = new Account();
        account.setBalance(1000);
        assertEquals(1000, account.getBalance());
    }
    @Test
    @DisplayName("a assetsValue set is getable")
    void assetsValueSetGet() {
        Account account = new Account();
        account.setAssetsValue(1000);
        assertEquals(1000, account.getAssetsValue());

    }

    @Test
    @DisplayName("a negative balance is not allowed")
    void negativeBalance() {
        assertThrows(UnsupportedOperationException.class, () -> {
            Account account = new Account();
            account.setBalance(-10);
        });

    }

}
