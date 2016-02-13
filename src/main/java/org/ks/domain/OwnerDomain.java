package org.ks.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_OWNER_DOMAIN")
public class OwnerDomain implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="company_id", nullable=true, updatable=true, insertable=true)
    private CompanyDomain company;  
 
    public Long getId() {
		return id;
	}
    public void setId(Long id) {
		this.id = id;
	}
    
    public String getName() {
		return name;
	}
    public void setName(String name) {
		this.name = name;
	}
    
    public CompanyDomain getCompany() {
		return company;
	}
    public void setCompany(CompanyDomain company) {
		this.company = company;
	}
}
