package gr.aueb.cf.springauthsession1.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public static final String REDIRECT_URL_SESSION_ATTRIBUTE_NAME = "REDIRECT_URL";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Object redirectURLObject = request.getSession().getAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME);

        if (redirectURLObject != null)
            setDefaultTargetUrl(redirectURLObject.toString());
        else {
            setDefaultTargetUrl("/dashboard");
        }

        request.getSession().removeAttribute(REDIRECT_URL_SESSION_ATTRIBUTE_NAME);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
