package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> getById(Long id);

    void save(Member member);

    void delete(Member member);
}
