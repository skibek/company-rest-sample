package org.ks.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.ks.domain.CompanyDomain;
import org.ks.repository.CompanyRepository;

@Service
@Transactional
public class CompanyService {

	private final Logger log = LoggerFactory.getLogger(CompanyService.class);
	
	@Inject
    private CompanyRepository companyRepository;
	
	@Transactional(readOnly = true)
    public CompanyDomain getCompanyWithTouch(Long id) {
		CompanyDomain companyDomain = companyRepository.findOne(id);
    	if (companyDomain != null) {
        	//touch - anti Lazy
    		companyDomain.getOwners().size();
        }
    	return companyDomain;
    }
}
