package servlets;

import enums.AccountStatus;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        Set<AccountStatus> accountStatuses = accountService.signUp(login, password, email);
        response.setContentType("text/html;charset=utf-8");
        if (accountStatuses.size() == 1 && accountStatuses.contains(AccountStatus.CREATED)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("lastLogin", login == null ? "" : login);

        response.getWriter().println(PageGenerator.getPage("authform.html", pageVariables));

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
}
