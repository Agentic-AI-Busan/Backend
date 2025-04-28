package hyu.erica.capstone.security.details;

import java.util.Collection;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomUserDetails implements UserDetails {

    private String email; // 사용자 email
    private Long userId; // 사용자 이름
    private String password; // 비밀번호
    private boolean enabled; // 계정 활성화 여부
    private Collection<? extends GrantedAuthority> authorities; // 사용자 권한


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities;}

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return userId.toString();
    }
}
