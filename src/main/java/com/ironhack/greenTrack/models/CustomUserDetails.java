package com.ironhack.greenTrack.models;

import com.ironhack.greenTrack.models.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


public class CustomUserDetails implements UserDetails {


    private final int id;
    private final String name;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
    }

    public int getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override public String getPassword() {
        return password;
    }

    @Override public boolean isAccountNonExpired() { return true; }

    @Override public boolean isAccountNonLocked() { return true; }

    @Override public boolean isCredentialsNonExpired() { return true; }

    @Override public boolean isEnabled() { return true; }
}
