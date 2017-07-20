package interfaces;

import enums.AccountStatus;

import java.util.Set;

public interface AccountService {
    public Set<AccountStatus> signUp(String login, String password, String email);
    public Set<AccountStatus> signIn(String login, String password);
}
