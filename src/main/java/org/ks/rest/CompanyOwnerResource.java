package org.ks.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.ks.domain.CompanyDomain;
import org.ks.domain.OwnerDomain;
import org.ks.dto.OwnerDTO;
import org.ks.repository.CompanyOwnerRepository;
import org.ks.repository.CompanyRepository;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api")
public class CompanyOwnerResource {

	private final Logger log = LoggerFactory.getLogger(CompanyOwnerResource.class);
	
	@Inject
    private CompanyOwnerRepository companyOwnerRepository;
	@Inject
    private CompanyRepository companyRepository;
    
    /**
     * POST  /companyOwner -> Create a new owner.
     */
    @RequestMapping(value = "/companyOwner",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody OwnerDTO ownerDto) throws URISyntaxException {
        log.debug("REST request to save ownerDto : {}", ownerDto);
        if (ownerDto.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new owner cannot already have an ID").build();
        }
        OwnerDomain owner = companyOwnerRepository.save(createOwnerFromDTO(ownerDto));
        return ResponseEntity.created(new URI("/api/companyOwner/" + owner.getId())).build();
    }
    
    private OwnerDomain createOwnerFromDTO(OwnerDTO ownerDto) {
    	OwnerDomain owner = new OwnerDomain();
    	owner.setName(ownerDto.getName());
    	CompanyDomain companyDomain = companyRepository.findOne(ownerDto.getCompanyId());
    	owner.setCompany(companyDomain);
    	return owner;
    }
    
    /**
     * PUT  /companyOwner -> Updates an existing owner.
     */
    /*
    @RequestMapping(value = "/companyOwner",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody OwnerDomain owner) throws URISyntaxException {
        log.debug("REST request to update OwnerDomain : {}", owner); 
                
        //wczesniej musimy ustawic ChargeID
        if (owner.getId() == null) {
            return create(owner);
        } else {
        	companyOwnerRepository.save(owner);
        	return ResponseEntity.ok().build();
        }
    }
    */
}
