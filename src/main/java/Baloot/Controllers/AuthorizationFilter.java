package Baloot.Controllers;

import Baloot.Data.Services.Jwt;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(2)
public class AuthorizationFilter extends OncePerRequestFilter {
    private final String callbackUrl = "http://localhost:8080/callback";
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
            ) {
        try {
            if (!request.getRequestURL().toString().equals(callbackUrl)) {
                String token = request.getHeader("x-authorization");
                Claims claims = Jwt.parseToken(token);
                if (!Jwt.validate(claims))
                    throw new Exception("User not logged in");
                // request.setAttribute("username", claims.get("username").toString());
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(401);
        }
    }
}
