package com.gtbr.gtbrshield.repository;

import com.gtbr.gtbrshield.entity.Api;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRepository extends CrudRepository<Api, Long> {

    @Query()
}
