package org.ks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.ks.domain.CompanyDomain;

/**
 * Spring Data JPA repository for the Company entity.
 */
@Repository
public interface CompanyRepository extends 
		JpaRepository<CompanyDomain,Long>, 
		JpaSpecificationExecutor<CompanyDomain> {

}
