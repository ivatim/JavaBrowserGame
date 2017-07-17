package services;

import enums.AccountStatus;
import main.User;

import java.util.*;

/**
 * Created by ivankov on 13.07.2017.
 */
public class AccountService {
    private static Map<String, User> userMap = new HashMap<>();

    public Set<AccountStatus> signUp(String login, String password, String email) {
        Set<AccountStatus> accountStatuses = new HashSet<>();

        Validator validator = new Validator(accountStatuses);
        validator.validate(login, password, email);

        if (!accountStatuses.isEmpty())
            return accountStatuses;

        createUser(login, password, email);
        accountStatuses.add(AccountStatus.CREATED);

        return accountStatuses;
    }

    private void createUser(String login, String password, String email) {
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setEmail(email);
        userMap.put(login, newUser);
    }

    public Set<AccountStatus> signIn(String login, String password) {
        Set<AccountStatus> accountStatuses = new HashSet<>();

        Validator validator = new Validator(accountStatuses);
        validator.validate(login, password);

        if (!accountStatuses.isEmpty())
            return accountStatuses;

        return accountStatuses;
    }

    public static Map<String, User> getUserMap() {
        return userMap;
    }
}
