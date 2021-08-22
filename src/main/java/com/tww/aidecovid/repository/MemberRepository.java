package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByLogin(String login);
}


