package com.tww.aidecovid.security;

import com.tww.aidecovid.model.Member;
import com.tww.aidecovid.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberRepository repository;

    Member detMember;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Member> member =  repository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("User Not Found!");
        }
        Member memberDetail = member.get();
        return new UserDetailsImpl(memberDetail);
    }

}
