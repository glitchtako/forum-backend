package com.glitchtako.forum.service.impl;

import com.glitchtako.forum.exception.UserNotFoundException;
import com.glitchtako.forum.model.dto.UserDetailsDTO;
import com.glitchtako.forum.model.entity.Permission;
import com.glitchtako.forum.model.entity.User;
import com.glitchtako.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepository.findByUsername(username).orElseThrow();

        return this.getSecurityUser(user);
    }

    private UserDetailsDTO getSecurityUser(User user) {

        Set<String> permissions = new HashSet<>();
        user.getRoles().forEach(role -> {
            permissions.add(role.getName());
            permissions.addAll(role.getPermissions().stream().map(Permission::getName).collect(Collectors.toSet()));
        });

        List<GrantedAuthority> authorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", permissions));

        UserDetailsDTO userDetailsDTO = UserDetailsDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(authorities)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .build();

        return userDetailsDTO;
    }
}
