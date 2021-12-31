package com.example.tutorial.user.service;

import com.example.tutorial.user.entity.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private UserInfo userInfo;

    public UserDetailsImpl(UserInfo user) {
        this.userInfo = user;
    }

    @Override
    public boolean equals(Object o){
        if(this==o) return true;
        if(!(o instanceof UserDetailsImpl)) return false;
        UserDetailsImpl userDetails = (UserDetailsImpl) o;
        return userInfo.getUsername().equals(userDetails.getUsername()) &&
               userInfo.getPassword().equals(userDetails.getPassword());
    }

    @Override
    public int hashCode(){
        return Objects.hash(userInfo.getUsername(), userInfo.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role : userInfo.getRole().split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUsername();
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
        return userInfo.isEnable();
    }
}
