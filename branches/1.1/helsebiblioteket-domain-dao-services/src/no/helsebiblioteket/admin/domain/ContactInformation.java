package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Date;
import org.apache.myfaces.custom.fileupload.UploadedFile;

public class ContactInformation implements Serializable {
	private static final long serialVersionUID = 5776043183143665803L;

	// Primary key
	private Integer id;
	
	// Local values
	private String postalAddress;
	private String postalCode;
	private String telephoneNumber;
	private String postalLocation;
	private String email;
	private Date lastChanged;
	private String info;
	private byte[] logoImage;
	private String logoName;
	private String logoContentType;
	
	
	public String getLogoName() {
		return logoName;
	}


	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}


	public String getLogoContentType() {
		return logoContentType;
	}


	public void setLogoContentType(String logoContentType) {
		this.logoContentType = logoContentType;
	}

	private UploadedFile uploadedImage;
	private String deletIt;

	
	// Helpers
	@Override
	public String toString() {
		return id + ": " + postalAddress + ", " + postalCode + ", " + email;
	}

	
	// GET/SET
	public Integer getId() {
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getPostalLocation() {
		return postalLocation;
	}
	public void setPostalLocation(String postalLocation) {
		this.postalLocation = postalLocation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	public byte[] getLogoImage() {
		java.lang.System.out.println("getLogoImage: " + logoImage);
		java.lang.System.out.println("getLogoImage.length: " + logoImage != null ? logoImage.length : "logoimage er null, har ingen lengde");
		return logoImage;
	}
	
	public void setLogoImage(byte[] logoImage) {
		this.logoImage = logoImage;
	}

	public UploadedFile getUploadedImage() {
		return uploadedImage;
	}

	public void setUploadedImage(UploadedFile uploadedImage) {
		this.uploadedImage = uploadedImage;
	}

	public String getDeletIt() {
		return deletIt;
	}

	public void setDeletIt(String deletIt) {
		this.deletIt = deletIt;
	}
	
	public boolean isLogoImage() {
		return this.logoImage != null;
	}
}