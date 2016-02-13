package org.ks.dto;


public class OwnerDTO{

    private Long id;
    private String name;
    private Long companyId;  
 
    public OwnerDTO() {
    }

    public OwnerDTO(Long id, String name, Long companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
    }
    
    public Long getId() {
		return id;
	}    
    public String getName() {
		return name;
	}
    public Long getCompanyId() {
		return companyId;
	}
}
