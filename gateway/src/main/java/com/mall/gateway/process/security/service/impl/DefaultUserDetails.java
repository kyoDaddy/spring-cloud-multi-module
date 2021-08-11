package com.mall.gateway.process.security.service.impl;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @EqualsAndHashCode
 *  : 자바 bean에서 동등성 비교를 위해 equals와 hashcode 메소드를 오버라이딩해서 사용
 *
 *      1. equals :  두 객체의 내용이 같은지, 동등성(equality) 를 비교하는 연산자
 *      2. hashCode : 두 객체가 같은 객체인지, 동일성(identity) 를 비교하는 연산자
 *      3. callSuper : callSuper 속성을 통해 eqauls와 hashCode 메소드 자동 생성 시 부모 클래스의 필드까지 감안할지의 여부를 설정할 수 있다.
 *      4. id : 연관 관계가 복잡해 질 때, @EqualsAndHashCode에서 서로 다른 연관 관계를 순환 참조하느라 무한 루프가 발생하고, 결국 stack overflow가 발생할 수 있기 때문에 id 값만 주로 사용
 *
 */
@Slf4j
@Builder
@EqualsAndHashCode(callSuper = false, of = {"id"})      // 자바 bean에서 동등성 비교를 위해 equals와 hashcode 메소드를 오버라이딩해서 사용
public class DefaultUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    //private final UUID id;    // UUID를 키값으로 사용할때 사용
    private final String id;

    private List<String> authorities;

    private List<String> authorities() {
        if(authorities == null) {
            authorities = new ArrayList<>();
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities().stream()
                .filter(authority -> authority != null && StringUtils.hasText(authority.trim()))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "*****";
    }

    @Override
    public String getUsername() {
        return id.toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
