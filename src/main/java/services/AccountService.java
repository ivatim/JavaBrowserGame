package services;

import main.User;
import servlets.SignUpServlet;

import java.util.*;

/**
 * Created by ivankov on 13.07.2017.
 */
public class AccountService {
    private static Map<String, User> userMap = new HashMap<>();

    public Set<SignUpServlet.Status> signUp(String login, String password, String email) {
        Set<SignUpServlet.Status> statuses = new HashSet<>();

        validateUserCreation(login, password, email, statuses);

        if (!statuses.isEmpty())
            return statuses;

        createUser(login, password, email);
        statuses.add(SignUpServlet.Status.CREATED);

        return statuses;
    }

    private void validateUserCreation(String login, String password, String email, Set<SignUpServlet.Status> statuses) {
        validateLogin(login, statuses);
        validatePassword(password, statuses);
        validateEmail(email, statuses);
    }

    private void validateLogin(String login, Set<SignUpServlet.Status> statuses) {
        isEmptyLogin(login, statuses);
        isInvalidLogin(login, statuses);
        isExistingLogin(login, statuses);
    }

    private void isEmptyLogin(String login, Set<SignUpServlet.Status> statuses) {
        if (login.isEmpty())
            statuses.add(SignUpServlet.Status.EMPTY_LOGIN);
    }

    private void isInvalidLogin(String login, Set<SignUpServlet.Status> statuses) {
        // TODO
    }

    private void isExistingLogin(String login, Set<SignUpServlet.Status> statuses) {
        if (userMap.containsKey(login))
            statuses.add(SignUpServlet.Status.EXISTING_LOGIN);
    }

    private void validatePassword(String password, Set<SignUpServlet.Status> statuses) {
        isEmptyPassword(password, statuses);
        isTooShortPassword(password, statuses);
    }

    private void isEmptyPassword(String password, Set<SignUpServlet.Status> statuses) {
        if (password.isEmpty())
            statuses.add(SignUpServlet.Status.EMPTY_PASSWORD);
    }

    private void isInvalidPassword(String password, Set<SignUpServlet.Status> statuses) {
        // TODO
    }

    private void isTooShortPassword(String password, Set<SignUpServlet.Status> statuses) {
        if (password.length() < 8) {
            statuses.add(SignUpServlet.Status.TOO_SHORT_PASSWORD);
        }
    }

    private void validateEmail(String email, Set<SignUpServlet.Status> statuses) {
        isEmptyEmail(email, statuses);
        isInvalidEmail(email, statuses);
        isExistingEmail(email, statuses);
    }

    private void isEmptyEmail(String email, Set<SignUpServlet.Status> statuses) {
        if (email.isEmpty()) {
            statuses.add(SignUpServlet.Status.EMPTY_EMAIL);
        }
    }

    private void isInvalidEmail(String email, Set<SignUpServlet.Status> statuses) {
        if (!email.contains("@") || !email.contains(".")) {
            statuses.add(SignUpServlet.Status.INVALID_EMAIL);
        }
    }

    private void isExistingEmail(String email, Set<SignUpServlet.Status> statuses) {
        // TODO
    }

    private void createUser(String login, String password, String email) {
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setEmail(email);
        userMap.put(login, newUser);
    }
}
