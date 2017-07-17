package services;

import enums.AccountStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * Created by ivankov on 13.07.2017.
 */

@RunWith(Parameterized.class)
public class AccountServiceTest {
    private AccountService accountService;
    private String login;
    private String password;
    private String email;
    private AccountStatus[] expectedAccountStatuses;

    @Before
    public void initialize() {
        accountService = new AccountService();
    }

    public AccountServiceTest(Object[] input, Object[] expectedStatuses) {
        this.login = (String) input[0];
        this.password = (String) input[1];
        this.email = (String) input[2];
        this.expectedAccountStatuses = Arrays.copyOf(expectedStatuses, expectedStatuses.length, AccountStatus[].class);
    }

    @Parameterized.Parameters
    public static Collection inputs() {
        return Arrays.asList(new Object[][][]{
                {{"admin", "adminadmin", "ololo@mail.ru"}, {AccountStatus.CREATED}},
                {{"admin", "admin", "ololo@mail.ru"}, {AccountStatus.EXISTING_LOGIN, AccountStatus.TOO_SHORT_PASSWORD}},
                {{"", "adminshaasdffd", "ololo@mail.ru"}, {AccountStatus.EMPTY_LOGIN}},
                {{"ololosha", "fucjiasdf", "privetmail.ru"}, {AccountStatus.INVALID_EMAIL}}
        });
    }

    @Test
    public void testSignUp() throws Exception {
        Set<AccountStatus> actualAccountStatuses = accountService.signUp(login, password, email);
        assertThat(actualAccountStatuses, containsInAnyOrder(expectedAccountStatuses));
    }
}