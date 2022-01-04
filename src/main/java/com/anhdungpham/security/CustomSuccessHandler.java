package com.anhdungpham.security;

import com.anhdungpham.utils.SecurityUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// https://www.baeldung.com/spring_redirect_after_login

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }


    private String determineTargetUrl(final Authentication authentication) {
        // Redirect URL
        StringBuilder url = new StringBuilder();
        List<String> roles = SecurityUtil.getAuthorities();
        if (isAdmin(roles)) {
            url.append("/admin/home");
        } else if (isUser(roles)) {
            url.append("/main-page");
        }
        return url.toString();
    }
    
    private boolean isAdmin(List<String> roles)  {
        return roles.contains("ADMIN");
    }
    private boolean isUser(List<String> roles)  {
        return roles.contains("USER");
    }

}
