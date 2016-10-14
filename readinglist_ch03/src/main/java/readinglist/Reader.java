package readinglist;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * ...90p.UserDetails 인터페이스의 메서드를 구현함으로써 ReaderDto 를
 *    사용자를 표현하는 객체로 사용할 수 있음.
 */
@Entity
//public class ReaderDto { //...by89p.
public class Reader implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String username;
    private String fullname;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //...89p.UserDetails 메서드
	/*
	 * ...90p. 항상 READER 권한을 부여하고, 그외에는 모두 true 를 반환하여
	 *    사용자 계정을 절대 잠그거나 폐기 또는 만료하지 않도록 함.
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	
    //...89p.항상 READER 권한부여.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
    }

    //...90p.계정이 만료되지 않았다는 것을 반환함.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //...90p.계정이 잠겨 있지 않았다는 것을 반환함.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //...90p.자격이 유효하다는 것을 반환함.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //...90p.계정이 활성화되어 있다는 것을 반환함.
    @Override
    public boolean isEnabled() {
        return true;
    }

}
