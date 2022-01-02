package com.tacos.tacocloud.data.repository;

import com.tacos.tacocloud.domain.Taco;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tacos",
                        itemResourceRel = "taco",
                        collectionResourceRel = "tacos")
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
