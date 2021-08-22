package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MemberService {

    Member getById(Long id);

    Member getByLogin(String login);

    void save(Member member);

    void delete(Member member);

    List<Member> getAllMembers();


}
