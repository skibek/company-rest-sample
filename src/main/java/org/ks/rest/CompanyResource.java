package org.ks.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.ks.domain.CompanyDomain;
import org.ks.repository.CompanyRepository;
import org.ks.service.CompanyService;

/**
 * REST controller for managing Company.
 */
@RestController
@RequestMapping("/api")
public class CompanyResource {

	private final Logger log = LoggerFactory.getLogger(CompanyResource.class);
	
	@Inject
    private CompanyRepository companyRepository;
	@Inject
    private CompanyService companyService;	
    
    /**
     * POST  /company -> Create a new company.
     */
    @RequestMapping(value = "/company",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody CompanyDomain company) throws URISyntaxException {
        log.debug("REST request to save Company : {}", company);
        if (company.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new company cannot already have an ID").build();
        }
        companyRepository.save(company);
        return ResponseEntity.created(new URI("/api/company/" + company.getId())).build();
    }

    /**
     * PUT  /company -> Updates an existing company.
     */
    @RequestMapping(value = "/company",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody CompanyDomain company) throws URISyntaxException {
        log.debug("REST request to update Company : {}", company); 
                
        //wczesniej musimy ustawic ChargeID
        if (company.getId() == null) {
            return create(company);
        } else {
        	companyRepository.save(company);
        	return ResponseEntity.ok().build();
        }
    }

    /**
     * GET  /company -> get all charges.
     */
	@RequestMapping(value = "/company",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompanyDomain>> getAll() throws URISyntaxException {
		List<CompanyDomain> page = companyRepository.findAll();
		/* TEST
		if (page.size() == 0) {
			CompanyDomain company = new CompanyDomain();
			company.setAddress("Address");
			company.setCity("city");
			company.setCountry("country");
			company.setEmail("email");
			company.setName("name");
			company.setPhoneNumber("phoneNumber");
			page.add(company);
		}*/
        return new ResponseEntity<List<CompanyDomain>>(page, HttpStatus.OK);
    }

    /**
     * GET  /company/:id -> get the "id" charge.
     */
    @RequestMapping(value = "/company/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDomain> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Charge : {}", id);
        CompanyDomain companyDomain = companyService.getCompanyWithTouch(id);
        if (companyDomain == null) {
        	//tworzymy nowy obiekt do pracy - nie zapisujemy od razu
        	companyDomain = new CompanyDomain();
        }
        return new ResponseEntity<>(companyDomain, HttpStatus.OK);
    }
}
