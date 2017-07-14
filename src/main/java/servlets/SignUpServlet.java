package servlets;

import services.AccountService;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Igor Ivankov
 */
public class SignUpServlet extends HttpServlet {
    private AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Set<Status> statuses = accountService.signUp(login, password, email);
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        login = request.getParameter("login");
//
//        response.setContentType("text/html;charset=utf-8");
//
//        if (login == null || login.isEmpty()) {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        } else {
//            response.setStatus(HttpServletResponse.SC_OK);
//        }
//
//        Map<String, Object> pageVariables = new HashMap<>();
//        pageVariables.put("lastLogin", login == null ? "" : login);
//
//        response.getWriter().println(PageGenerator.getPage("authform.html", pageVariables));
    }

    public enum Status {
        CREATED("successfully created"),
        EMPTY_LOGIN("empty login"),
        INVALID_LOGIN("invalid login"),
        EXISTING_LOGIN("user having such login already exists"),
        EMPTY_PASSWORD("empty password"),
        INVALID_PASSWORD("invalid password"),
        TOO_SHORT_PASSWORD("too short password"),
        EMPTY_EMAIL("empty email"),
        INVALID_EMAIL("invalid email"),
        EXISTING_EMAIL("user having such email already exists");

        private final String text;

        Status(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
}
