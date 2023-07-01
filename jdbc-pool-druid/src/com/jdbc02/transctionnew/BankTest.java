package com.jdbc02.transctionnew;

import org.junit.Test;

import java.sql.SQLException;

/**
 * ClassName: BankTest
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/1 16:57
 * @Version 1.0
 */
public class BankTest {

    @Test
    public void testBank() throws SQLException {
        BankService bankService = new BankService();

        bankService.transfer("tttttt", "dddddd", 500);

    }
}
