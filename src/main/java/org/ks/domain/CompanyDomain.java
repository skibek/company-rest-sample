package org.ks.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * JSON view
 * 	{
		"id": null,
		"name": "name",
		"address": "Address",
		"city": "city",
		"country": "country",
		"email": "email",
		"phoneNumber": "phoneNumber",
		"owners":[]
	}
 * @author ks
 *
 */
@Entity
@Table(name = "T_COMPANY")
public class CompanyDomain implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;
    
    @Column(name = "address")
    @NotEmpty
    private String address;
    
    @Column(name = "city")
    @NotEmpty
    private String city;
    
    @Column(name = "country")
    @NotEmpty
    private String country;
    
    @Column(name = "email")
    private String email; //not required
    
    @Column(name = "phone_number")
    private String phoneNumber; //not required
    
    @OneToMany(mappedBy="company", cascade = {CascadeType.REFRESH})
    @OrderBy("name ASC")
    private List<OwnerDomain> owners = new ArrayList<OwnerDomain>();  
    
    
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
    
    public String getAddress() {
		return address;
	}
    public void setAddress(String address) {
		this.address = address;
	}
    
    public String getCity() {
		return city;
	}
    public void setCity(String city) {
		this.city = city;
	}
    
    public String getCountry() {
		return country;
	}
    public void setCountry(String country) {
		this.country = country;
	}
    
    public String getEmail() {
		return email;
	}
    public void setEmail(String email) {
		this.email = email;
	}
    
    public String getPhoneNumber() {
		return phoneNumber;
	}
    public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
    public List<OwnerDomain> getOwners() {
		return owners;
	}
    public void setOwners(List<OwnerDomain> owners) {
		this.owners = owners;
	}
}
