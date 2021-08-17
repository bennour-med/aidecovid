package com.tww.aidecovid.repository;

import com.tww.aidecovid.model.Discussion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscussionRepository extends CrudRepository<Discussion, Long> {
}
