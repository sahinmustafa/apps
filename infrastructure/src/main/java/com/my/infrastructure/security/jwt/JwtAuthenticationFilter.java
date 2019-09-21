package com.my.infrastructure.security.jwt;

import com.my.infrastructure.security.TokenProvider;
import com.my.infrastructure.security.user.UserDetailsService;
import com.my.infrastructure.security.user.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = getToken(httpServletRequest);
        token.ifPresent(this::authenticate);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void authenticate(String token) {
        try {
            tokenProvider.validate(token);
            String userId = tokenProvider.getUserIdFromToken(token);

            UserPrincipal userDetail = userDetailsService.findById(userId);
            userDetail.validateToken(token);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
        }
    }

    private Optional<String> getToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith("Bearer ")) {
            return Optional.empty();
        }
        return Optional.of(bearerToken.substring(7));
    }

}
