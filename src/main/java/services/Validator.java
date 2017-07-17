package services;

import enums.AccountStatus;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ivankov on 17.07.2017.
 */
public class Validator {
    private Set<AccountStatus> accountStatuses = new HashSet<>();

    public Validator(Set<AccountStatus> accountStatuses) {
        this.accountStatuses = accountStatuses;
    }

    public void validate(String login, String password) {
        validateLogin(login);
        validatePassword(password);
    }

    public void validate(String login, String password, String email) {
        validateLogin(login);
        validatePassword(password);
        validateEmail(email);
    }

    private void validateLogin(String login) {
        isEmptyLogin(login);
        isInvalidLogin(login);
        isExistingLogin(login);
    }

    private void isEmptyLogin(String login) {
        if (login.isEmpty())
            accountStatuses.add(AccountStatus.EMPTY_LOGIN);
    }

    private void isInvalidLogin(String login) {
        // TODO
        if (false)
            accountStatuses.add(AccountStatus.INVALID_LOGIN);
    }

    private void isExistingLogin(String login) {
        if (AccountService.getUserMap().containsKey(login))
            accountStatuses.add(AccountStatus.EXISTING_LOGIN);
    }

    private void validatePassword(String password) {
        isEmptyPassword(password);
        isTooShortPassword(password);
    }

    private void isEmptyPassword(String password) {
        if (password.isEmpty())
            accountStatuses.add(AccountStatus.EMPTY_PASSWORD);
    }

    private void isInvalidPassword(String password) {
        // TODO
        if (false)
            accountStatuses.add(AccountStatus.INVALID_PASSWORD);
    }

    private void isTooShortPassword(String password) {
        if (password.length() < 8) {
            accountStatuses.add(AccountStatus.TOO_SHORT_PASSWORD);
        }
    }

    private void validateEmail(String email) {
        isEmptyEmail(email);
        isInvalidEmail(email);
        isExistingEmail(email);
    }

    private void isEmptyEmail(String email) {
        if (email.isEmpty()) {
            accountStatuses.add(AccountStatus.EMPTY_EMAIL);
        }
    }

    private void isInvalidEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            accountStatuses.add(AccountStatus.INVALID_EMAIL);
        }
    }

    private void isExistingEmail(String email) {
        // TODO
        if (false)
            accountStatuses.add(AccountStatus.EXISTING_EMAIL);
    }

    public Set<AccountStatus> getAccountStatuses() {
        return accountStatuses;
    }
}
