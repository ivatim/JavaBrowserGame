package services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import services.AccountService;
import servlets.SignUpServlet;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by ivankov on 13.07.2017.
 */

@RunWith(Parameterized.class)
public class AccountServiceTest {
    private AccountService accountService;
    private String login;
    private String password;
    private String email;
    private SignUpServlet.Status[] expectedStatuses;

    @Before
    public void initialize() {
        accountService = new AccountService();
    }

    public AccountServiceTest(Object[] input, Object[] expectedStatuses) {
        this.login = (String)input[0];
        this.password = (String)input[1];
        this.email = (String)input[2];
        this.expectedStatuses = Arrays.copyOf(expectedStatuses, expectedStatuses.length, SignUpServlet.Status[].class);
    }

    @Parameterized.Parameters
    public static Collection inputs() {
        return Arrays.asList(new Object[][][] {
                {{"admin", "adminadmin", "ololo@mail.ru"}, {SignUpServlet.Status.CREATED}},
                {{"admin", "admin", "ololo@mail.ru"}, {SignUpServlet.Status.EXISTING_LOGIN, SignUpServlet.Status.TOO_SHORT_PASSWORD}},
                {{"", "adminshaasdffd", "ololo@mail.ru"}, {SignUpServlet.Status.EMPTY_LOGIN}},
                {{"ololosha", "fucjiasdf", "privetmail.ru"}, {SignUpServlet.Status.INVALID_EMAIL}}
        });
    }

    @Test
    public void testSignUp() throws Exception {
        assertArrayEquals(expectedStatuses, accountService.signUp(login, password, email).toArray());
    }
}