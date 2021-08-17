package com.tww.aidecovid.service;


import com.tww.aidecovid.model.Member;
import com.tww.aidecovid.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository repository;

    @Override
    public Optional<Member> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(Member member) {
        repository.save(member);
    }

    @Override
    public void delete(Member member) {
        repository.delete(member);
    }
}
