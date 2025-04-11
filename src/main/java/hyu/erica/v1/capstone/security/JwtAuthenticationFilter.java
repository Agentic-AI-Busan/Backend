package hyu.erica.v1.capstone.security;

import hyu.erica.v1.capstone.api.exception.GeneralException;
import hyu.erica.v1.capstone.security.details.CustomUserDetails;
import hyu.erica.v1.capstone.service.auth.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService; // MemberService 주입 추가

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isReissueRequest(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = jwtTokenProvider.resolveToken(request);
            if (isValidToken(token))
                authenticateUser(token);
            filterChain.doFilter(request, response);
        } catch (GeneralException e) {
            handleException(response, e);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        return path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/api/users/login")  // 🔥 로그인 요청 필터 제외
                || path.startsWith("/api/users/sign-up"); // 🔥 회원가입 요청 필터 제외
    }


    private boolean isReissueRequest(HttpServletRequest request) {
        return Objects.equals(request.getRequestURI(), "/spot/reissue");
    }

    private boolean isValidToken(String token) {
        log.info(token);
        jwtTokenProvider.validateToken(token, false);
        return true;
    }

    private void authenticateUser(String token) {
        Long userId = jwtTokenProvider.getUserIdByToken(token);
        CustomUserDetails userDetails = (CustomUserDetails) authService.loadUserByUsername(userId.toString());
        Authentication authentication = jwtTokenProvider.getAuthentication(token, userDetails);
        log.info("Authenticated user: {}", userDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private void handleException(HttpServletResponse response, GeneralException e) throws IOException {
        response.setContentType("text/plain; charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        try (PrintWriter writer = response.getWriter()) {
            writer.write("Invalid JWT token: " + e.getStatus().getMessage());
            writer.flush();
        }
    }
}