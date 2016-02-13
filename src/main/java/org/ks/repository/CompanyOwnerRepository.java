package org.ks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import org.ks.domain.OwnerDomain;

/**
 * Spring Data JPA repository for the Owner entity.
 */
@Repository
public interface CompanyOwnerRepository extends 
		JpaRepository<OwnerDomain,Long>, 
		JpaSpecificationExecutor<OwnerDomain> {

}
