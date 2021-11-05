package com.tww.aidecovid.service;

import com.tww.aidecovid.model.Member;
import com.tww.aidecovid.repository.MemberRepository;
import javassist.NotFoundException;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Arrays;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository repository;

    @Override
    public Member getById(Long id) {
        Optional<Member> member = repository.findById(id);
       if(member.isEmpty()) throw new EntityNotFoundException("Member not found");
       return member.get();
    }

    @Override
    public Member getByEmail(String email) {
        Optional<Member> member = repository.findByEmail(email);
        if(member.isEmpty()) throw new EntityNotFoundException("Member not found");
        return member.get();
    }

    @Override
    public void save(Member member) {
        repository.save(member);
    }

    @Override
    public void delete(Member member) {
        repository.delete(member);
    }

    @Override
    public List<Member> getAllMembers() {

        return IterableUtils.toList(repository.findAll());
    }
}
