package hyu.erica.capstone.service.auth.impl;

import hyu.erica.capstone.domain.User;
import hyu.erica.capstone.repository.UserRepository;
import hyu.erica.capstone.security.details.CustomUserDetails;
import hyu.erica.capstone.service.auth.AuthService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long userId = parseUsernameToUserId(username);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        List<GrantedAuthority> authorities = List.of();

        return CustomUserDetails.builder()
                .email(user.getEmail())
                .userId(user.getId())
                .password(user.getPassword())
                .enabled(true)
                .authorities(authorities)
                .build();
    }

    private Long parseUsernameToUserId(String username) {
        try {
            return Long.parseLong(username);
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid user ID format");
        }
    }
}
