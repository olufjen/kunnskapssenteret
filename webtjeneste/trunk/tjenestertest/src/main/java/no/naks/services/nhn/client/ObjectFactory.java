
package no.naks.services.nhn.client;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the no.naks.services.nhn.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _ArrayOfCode_QNAME = new QName("http://register.nhn.no/Common", "ArrayOfCode");
    private final static QName _CommunicationParty_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CommunicationParty");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _CommunicationPartyStatistics_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CommunicationPartyStatistics");
    private final static QName _ValidationError_QNAME = new QName("http://register.nhn.no/Common", "ValidationError");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _Requisition_QNAME = new QName("http://register.nhn.no/HPR", "Requisition");
    private final static QName _ArrayOfOrganization_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfOrganization");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _HPRInformation_QNAME = new QName("http://register.nhn.no/HPR", "HPRInformation");
    private final static QName _ArrayOfEntityLogEntryData_QNAME = new QName("http://register.nhn.no/Common", "ArrayOfEntityLogEntryData");
    private final static QName _EntityLogEntry_QNAME = new QName("http://register.nhn.no/Common", "EntityLogEntry");
    private final static QName _ServiceCreate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ServiceCreate");
    private final static QName _ArrayOfOrganizationPerson_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfOrganizationPerson");
    private final static QName _KeyValuePairOfstringstring_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.Collections.Generic", "KeyValuePairOfstringstring");
    private final static QName _ArrayOfSearchFacet_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfSearchFacet");
    private final static QName _ArrayOfEntityLogEntry_QNAME = new QName("http://register.nhn.no/Common", "ArrayOfEntityLogEntry");
    private final static QName _Period_QNAME = new QName("http://register.nhn.no/Common", "Period");
    private final static QName _EntityLogEntryData_QNAME = new QName("http://register.nhn.no/Common", "EntityLogEntryData");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _Code_QNAME = new QName("http://register.nhn.no/Common", "Code");
    private final static QName _ArrayOfAuthorization_QNAME = new QName("http://register.nhn.no/HPR", "ArrayOfAuthorization");
    private final static QName _DepartmentCreate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "DepartmentCreate");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _ArrayOfDepartment_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfDepartment");
    private final static QName _OrganizationCreate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "OrganizationCreate");
    private final static QName _CitizenIdType_QNAME = new QName("http://register.nhn.no/Common", "CitizenIdType");
    private final static QName _Department_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Department");
    private final static QName _Authorization_QNAME = new QName("http://register.nhn.no/HPR", "Authorization");
    private final static QName _DepartmentUpdate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "DepartmentUpdate");
    private final static QName _GenericFault_QNAME = new QName("http://register.nhn.no/Common", "GenericFault");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _ArrayOfKeyValuePairOfstringstring_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.Collections.Generic", "ArrayOfKeyValuePairOfstringstring");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _CitizenId_QNAME = new QName("http://register.nhn.no/Common", "CitizenId");
    private final static QName _OrganizationUpdate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "OrganizationUpdate");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _ArrayOfCertificateDetails_QNAME = new QName("http://register.nhn.no/CertificateDetails", "ArrayOfCertificateDetails");
    private final static QName _ArrayOfElectronicAddress_QNAME = new QName("http://register.nhn.no/Common", "ArrayOfElectronicAddress");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _CommunicationPartySearch_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CommunicationPartySearch");
    private final static QName _ValidationFault_QNAME = new QName("http://register.nhn.no/Common", "ValidationFault");
    private final static QName _ElectronicAddress_QNAME = new QName("http://register.nhn.no/Common", "ElectronicAddress");
    private final static QName _ArrayOfValidationError_QNAME = new QName("http://register.nhn.no/Common", "ArrayOfValidationError");
    private final static QName _ArrayOfCommunicationParty_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfCommunicationParty");
    private final static QName _ArrayOfPhysicalAddress_QNAME = new QName("http://register.nhn.no/Common", "ArrayOfPhysicalAddress");
    private final static QName _ArrayOfService_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfService");
    private final static QName _Service_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Service");
    private final static QName _OrganizationPersonCreate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "OrganizationPersonCreate");
    private final static QName _ArrayOfSpeciality_QNAME = new QName("http://register.nhn.no/HPR", "ArrayOfSpeciality");
    private final static QName _ServiceUpdate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ServiceUpdate");
    private final static QName _CertificateDetails_QNAME = new QName("http://register.nhn.no/CertificateDetails", "CertificateDetails");
    private final static QName _ArrayOfBusinessTypeStatistics_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfBusinessTypeStatistics");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Organization_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Organization");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _ArrayOfstring_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "ArrayOfstring");
    private final static QName _Person_QNAME = new QName("http://register.nhn.no/HPR", "Person");
    private final static QName _ArrayOfSearchResultFacetEntry_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ArrayOfSearchResultFacetEntry");
    private final static QName _CommunicationPartyType_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CommunicationPartyType");
    private final static QName _OrganizationPerson_QNAME = new QName("http://register.nhn.no/CommunicationParty", "OrganizationPerson");
    private final static QName _BusinessTypeStatistics_QNAME = new QName("http://register.nhn.no/CommunicationParty", "BusinessTypeStatistics");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _OrganizationPersonUpdate_QNAME = new QName("http://register.nhn.no/CommunicationParty", "OrganizationPersonUpdate");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _SearchResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "SearchResult");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _PhysicalAddress_QNAME = new QName("http://register.nhn.no/Common", "PhysicalAddress");
    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _Speciality_QNAME = new QName("http://register.nhn.no/HPR", "Speciality");
    private final static QName _SearchFacet_QNAME = new QName("http://register.nhn.no/CommunicationParty", "SearchFacet");
    private final static QName _SearchResultFacetEntry_QNAME = new QName("http://register.nhn.no/CommunicationParty", "SearchResultFacetEntry");
    private final static QName _CreateOrganizationResponseCreateOrganizationResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CreateOrganizationResult");
    private final static QName _EntityLogEntryByUser_QNAME = new QName("http://register.nhn.no/Common", "ByUser");
    private final static QName _EntityLogEntryDescription_QNAME = new QName("http://register.nhn.no/Common", "Description");
    private final static QName _EntityLogEntryChanges_QNAME = new QName("http://register.nhn.no/Common", "Changes");
    private final static QName _SearchSearch_QNAME = new QName("http://register.nhn.no/CommunicationParty", "search");
    private final static QName _CodeSimpleType_QNAME = new QName("http://register.nhn.no/Common", "SimpleType");
    private final static QName _CodeCodeValue_QNAME = new QName("http://register.nhn.no/Common", "CodeValue");
    private final static QName _CodeCodeText_QNAME = new QName("http://register.nhn.no/Common", "CodeText");
    private final static QName _OrganizationUpdateElectronicAddresses_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ElectronicAddresses");
    private final static QName _OrganizationUpdateBusinessType_QNAME = new QName("http://register.nhn.no/CommunicationParty", "BusinessType");
    private final static QName _OrganizationUpdateValid_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Valid");
    private final static QName _OrganizationUpdatePhysicalAddresses_QNAME = new QName("http://register.nhn.no/CommunicationParty", "PhysicalAddresses");
    private final static QName _OrganizationUpdateDisplayName_QNAME = new QName("http://register.nhn.no/CommunicationParty", "DisplayName");
    private final static QName _SearchByIdResponseSearchByIdResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "SearchByIdResult");
    private final static QName _PersonMiddleName_QNAME = new QName("http://register.nhn.no/HPR", "MiddleName");
    private final static QName _PersonFirstName_QNAME = new QName("http://register.nhn.no/HPR", "FirstName");
    private final static QName _PersonSex_QNAME = new QName("http://register.nhn.no/HPR", "Sex");
    private final static QName _PersonCitizenId_QNAME = new QName("http://register.nhn.no/HPR", "CitizenId");
    private final static QName _PersonLastName_QNAME = new QName("http://register.nhn.no/HPR", "LastName");
    private final static QName _SearchCertificatesForValidatingSignatureResponseSearchCertificatesForValidatingSignatureResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "SearchCertificatesForValidatingSignatureResult");
    private final static QName _GenericFaultMessage_QNAME = new QName("http://register.nhn.no/Common", "Message");
    private final static QName _GenericFaultErrorCode_QNAME = new QName("http://register.nhn.no/Common", "ErrorCode");
    private final static QName _CitizenIdSex_QNAME = new QName("http://register.nhn.no/Common", "Sex");
    private final static QName _CitizenIdId_QNAME = new QName("http://register.nhn.no/Common", "Id");
    private final static QName _GetServiceDetailsResponseGetServiceDetailsResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetServiceDetailsResult");
    private final static QName _OrganizationPersonOrganizations_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Organizations");
    private final static QName _OrganizationPersonDepartments_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Departments");
    private final static QName _OrganizationPersonTitle_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Title");
    private final static QName _OrganizationPersonPerson_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Person");
    private final static QName _OrganizationPersonProperties_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Properties");
    private final static QName _ServiceLocationDescription_QNAME = new QName("http://register.nhn.no/CommunicationParty", "LocationDescription");
    private final static QName _ServiceCode_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Code");
    private final static QName _ValidationErrorValue_QNAME = new QName("http://register.nhn.no/Common", "Value");
    private final static QName _ValidationErrorDisplayName_QNAME = new QName("http://register.nhn.no/Common", "DisplayName");
    private final static QName _ValidationErrorPropertyName_QNAME = new QName("http://register.nhn.no/Common", "PropertyName");
    private final static QName _PhysicalAddressPostbox_QNAME = new QName("http://register.nhn.no/Common", "Postbox");
    private final static QName _PhysicalAddressStreetAddress_QNAME = new QName("http://register.nhn.no/Common", "StreetAddress");
    private final static QName _PhysicalAddressCity_QNAME = new QName("http://register.nhn.no/Common", "City");
    private final static QName _PhysicalAddressTypeCodeValue_QNAME = new QName("http://register.nhn.no/Common", "TypeCodeValue");
    private final static QName _SearchResultResults_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Results");
    private final static QName _SearchResultFacets_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Facets");
    private final static QName _UpdateOrganizationDetailsOrg_QNAME = new QName("http://register.nhn.no/CommunicationParty", "org");
    private final static QName _GetCertificateForEncryptionResponseGetCertificateForEncryptionResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetCertificateForEncryptionResult");
    private final static QName _SpecialityType_QNAME = new QName("http://register.nhn.no/HPR", "Type");
    private final static QName _SpecialityValid_QNAME = new QName("http://register.nhn.no/HPR", "Valid");
    private final static QName _HPRInformationHPRNo_QNAME = new QName("http://register.nhn.no/HPR", "HPRNo");
    private final static QName _HPRInformationAuthorizations_QNAME = new QName("http://register.nhn.no/HPR", "Authorizations");
    private final static QName _GetCertificateForValidatingSignatureResponseGetCertificateForValidatingSignatureResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetCertificateForValidatingSignatureResult");
    private final static QName _UpdateServiceDetailsT_QNAME = new QName("http://register.nhn.no/CommunicationParty", "t");
    private final static QName _OrganizationServices_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Services");
    private final static QName _OrganizationIndustryCodes_QNAME = new QName("http://register.nhn.no/CommunicationParty", "IndustryCodes");
    private final static QName _OrganizationPeople_QNAME = new QName("http://register.nhn.no/CommunicationParty", "People");
    private final static QName _GetCertificateDetailsForValidatingSignatureResponseGetCertificateDetailsForValidatingSignatureResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetCertificateDetailsForValidatingSignatureResult");
    private final static QName _GetOrganizationPersonDetailsResponseGetOrganizationPersonDetailsResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetOrganizationPersonDetailsResult");
    private final static QName _CertificateDetailsCertificate_QNAME = new QName("http://register.nhn.no/CertificateDetails", "Certificate");
    private final static QName _CertificateDetailsLdapUrl_QNAME = new QName("http://register.nhn.no/CertificateDetails", "LdapUrl");
    private final static QName _GetDepartmentDetailsResponseGetDepartmentDetailsResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetDepartmentDetailsResult");
    private final static QName _SearchCertificatesForEncryptionResponseSearchCertificatesForEncryptionResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "SearchCertificatesForEncryptionResult");
    private final static QName _ServiceCreateServiceCode_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ServiceCode");
    private final static QName _SearchCertificatesForValidatingSignatureSn_QNAME = new QName("http://register.nhn.no/CommunicationParty", "sn");
    private final static QName _SearchCertificatesForValidatingSignatureCn_QNAME = new QName("http://register.nhn.no/CommunicationParty", "cn");
    private final static QName _CreateServiceResponseCreateServiceResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CreateServiceResult");
    private final static QName _EntityLogEntryDataName_QNAME = new QName("http://register.nhn.no/Common", "Name");
    private final static QName _EntityLogEntryDataNewValue_QNAME = new QName("http://register.nhn.no/Common", "NewValue");
    private final static QName _EntityLogEntryDataOldValue_QNAME = new QName("http://register.nhn.no/Common", "OldValue");
    private final static QName _ValidationFaultErrors_QNAME = new QName("http://register.nhn.no/Common", "Errors");
    private final static QName _CreateOrganizationPersonResponseCreateOrganizationPersonResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CreateOrganizationPersonResult");
    private final static QName _CommunicationPartyParentName_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ParentName");
    private final static QName _CommunicationPartyParentBusinessType_QNAME = new QName("http://register.nhn.no/CommunicationParty", "ParentBusinessType");
    private final static QName _CommunicationPartyName_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Name");
    private final static QName _CommunicationPartyMunicipality_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Municipality");
    private final static QName _SearchResultFacetEntryValue_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Value");
    private final static QName _SearchResultFacetEntryText_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Text");
    private final static QName _SearchResultFacetEntryType_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Type");
    private final static QName _UpdateOrganizationPersonDetailsP_QNAME = new QName("http://register.nhn.no/CommunicationParty", "p");
    private final static QName _ElectronicAddressAddress_QNAME = new QName("http://register.nhn.no/Common", "Address");
    private final static QName _CommunicationPartySearchSearchConstraints_QNAME = new QName("http://register.nhn.no/CommunicationParty", "SearchConstraints");
    private final static QName _SearchFacetCodeValue_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CodeValue");
    private final static QName _SearchFacetEntries_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Entries");
    private final static QName _SearchByIdId_QNAME = new QName("http://register.nhn.no/CommunicationParty", "id");
    private final static QName _GetChangeLogResponseGetChangeLogResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetChangeLogResult");
    private final static QName _OrganizationPersonCreateHprNumber_QNAME = new QName("http://register.nhn.no/CommunicationParty", "HprNumber");
    private final static QName _OrganizationPersonCreateProfessions_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Professions");
    private final static QName _OrganizationPersonCreateSpecialities_QNAME = new QName("http://register.nhn.no/CommunicationParty", "Specialities");
    private final static QName _GetCertificateDetailsForEncryptionResponseGetCertificateDetailsForEncryptionResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetCertificateDetailsForEncryptionResult");
    private final static QName _AuthorizationProfession_QNAME = new QName("http://register.nhn.no/HPR", "Profession");
    private final static QName _AuthorizationSpecialities_QNAME = new QName("http://register.nhn.no/HPR", "Specialities");
    private final static QName _GetCommunicationPartyDetailsResponseGetCommunicationPartyDetailsResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetCommunicationPartyDetailsResult");
    private final static QName _SetCommunicationPartyValidPeriod_QNAME = new QName("http://register.nhn.no/CommunicationParty", "period");
    private final static QName _GetCommunicationPartyStatisticsResponseGetCommunicationPartyStatisticsResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetCommunicationPartyStatisticsResult");
    private final static QName _PingResponsePingResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "PingResult");
    private final static QName _CreateDepartmentResponseCreateDepartmentResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "CreateDepartmentResult");
    private final static QName _GetOrganizationDetailsResponseGetOrganizationDetailsResult_QNAME = new QName("http://register.nhn.no/CommunicationParty", "GetOrganizationDetailsResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: no.naks.services.nhn.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EntityLogEntry }
     * 
     */
    public EntityLogEntry createEntityLogEntry() {
        return new EntityLogEntry();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link GetCommunicationPartyStatistics }
     * 
     */
    public GetCommunicationPartyStatistics createGetCommunicationPartyStatistics() {
        return new GetCommunicationPartyStatistics();
    }

    /**
     * Create an instance of {@link GetCertificateDetailsForEncryption }
     * 
     */
    public GetCertificateDetailsForEncryption createGetCertificateDetailsForEncryption() {
        return new GetCertificateDetailsForEncryption();
    }

    /**
     * Create an instance of {@link Code }
     * 
     */
    public Code createCode() {
        return new Code();
    }

    /**
     * Create an instance of {@link GetServiceDetails }
     * 
     */
    public GetServiceDetails createGetServiceDetails() {
        return new GetServiceDetails();
    }

    /**
     * Create an instance of {@link ArrayOfSearchFacet }
     * 
     */
    public ArrayOfSearchFacet createArrayOfSearchFacet() {
        return new ArrayOfSearchFacet();
    }

    /**
     * Create an instance of {@link ArrayOfDepartment }
     * 
     */
    public ArrayOfDepartment createArrayOfDepartment() {
        return new ArrayOfDepartment();
    }

    /**
     * Create an instance of {@link ArrayOfBusinessTypeStatistics }
     * 
     */
    public ArrayOfBusinessTypeStatistics createArrayOfBusinessTypeStatistics() {
        return new ArrayOfBusinessTypeStatistics();
    }

    /**
     * Create an instance of {@link OrganizationPerson }
     * 
     */
    public OrganizationPerson createOrganizationPerson() {
        return new OrganizationPerson();
    }

    /**
     * Create an instance of {@link Service }
     * 
     */
    public Service createService() {
        return new Service();
    }

    /**
     * Create an instance of {@link ValidationError }
     * 
     */
    public ValidationError createValidationError() {
        return new ValidationError();
    }

    /**
     * Create an instance of {@link PhysicalAddress }
     * 
     */
    public PhysicalAddress createPhysicalAddress() {
        return new PhysicalAddress();
    }

    /**
     * Create an instance of {@link UpdateOrganizationDetails }
     * 
     */
    public UpdateOrganizationDetails createUpdateOrganizationDetails() {
        return new UpdateOrganizationDetails();
    }

    /**
     * Create an instance of {@link GetCertificateForEncryptionResponse }
     * 
     */
    public GetCertificateForEncryptionResponse createGetCertificateForEncryptionResponse() {
        return new GetCertificateForEncryptionResponse();
    }

    /**
     * Create an instance of {@link Speciality }
     * 
     */
    public Speciality createSpeciality() {
        return new Speciality();
    }

    /**
     * Create an instance of {@link UpdateOrganizationPersonDetailsResponse }
     * 
     */
    public UpdateOrganizationPersonDetailsResponse createUpdateOrganizationPersonDetailsResponse() {
        return new UpdateOrganizationPersonDetailsResponse();
    }

    /**
     * Create an instance of {@link GetDepartmentDetails }
     * 
     */
    public GetDepartmentDetails createGetDepartmentDetails() {
        return new GetDepartmentDetails();
    }

    /**
     * Create an instance of {@link DepartmentCreate }
     * 
     */
    public DepartmentCreate createDepartmentCreate() {
        return new DepartmentCreate();
    }

    /**
     * Create an instance of {@link GetCertificateForValidatingSignatureResponse }
     * 
     */
    public GetCertificateForValidatingSignatureResponse createGetCertificateForValidatingSignatureResponse() {
        return new GetCertificateForValidatingSignatureResponse();
    }

    /**
     * Create an instance of {@link UpdateServiceDetails }
     * 
     */
    public UpdateServiceDetails createUpdateServiceDetails() {
        return new UpdateServiceDetails();
    }

    /**
     * Create an instance of {@link GetOrganizationPersonDetailsResponse }
     * 
     */
    public GetOrganizationPersonDetailsResponse createGetOrganizationPersonDetailsResponse() {
        return new GetOrganizationPersonDetailsResponse();
    }

    /**
     * Create an instance of {@link GetOrganizationPersonDetails }
     * 
     */
    public GetOrganizationPersonDetails createGetOrganizationPersonDetails() {
        return new GetOrganizationPersonDetails();
    }

    /**
     * Create an instance of {@link ArrayOfCode }
     * 
     */
    public ArrayOfCode createArrayOfCode() {
        return new ArrayOfCode();
    }

    /**
     * Create an instance of {@link ArrayOfEntityLogEntryData }
     * 
     */
    public ArrayOfEntityLogEntryData createArrayOfEntityLogEntryData() {
        return new ArrayOfEntityLogEntryData();
    }

    /**
     * Create an instance of {@link CertificateDetails }
     * 
     */
    public CertificateDetails createCertificateDetails() {
        return new CertificateDetails();
    }

    /**
     * Create an instance of {@link GetCertificateForEncryption }
     * 
     */
    public GetCertificateForEncryption createGetCertificateForEncryption() {
        return new GetCertificateForEncryption();
    }

    /**
     * Create an instance of {@link CreateOrganization }
     * 
     */
    public CreateOrganization createCreateOrganization() {
        return new CreateOrganization();
    }

    /**
     * Create an instance of {@link DepartmentUpdate }
     * 
     */
    public DepartmentUpdate createDepartmentUpdate() {
        return new DepartmentUpdate();
    }

    /**
     * Create an instance of {@link GetOrganizationDetails }
     * 
     */
    public GetOrganizationDetails createGetOrganizationDetails() {
        return new GetOrganizationDetails();
    }

    /**
     * Create an instance of {@link GetDepartmentDetailsResponse }
     * 
     */
    public GetDepartmentDetailsResponse createGetDepartmentDetailsResponse() {
        return new GetDepartmentDetailsResponse();
    }

    /**
     * Create an instance of {@link ServiceCreate }
     * 
     */
    public ServiceCreate createServiceCreate() {
        return new ServiceCreate();
    }

    /**
     * Create an instance of {@link ArrayOfValidationError }
     * 
     */
    public ArrayOfValidationError createArrayOfValidationError() {
        return new ArrayOfValidationError();
    }

    /**
     * Create an instance of {@link CreateServiceResponse }
     * 
     */
    public CreateServiceResponse createCreateServiceResponse() {
        return new CreateServiceResponse();
    }

    /**
     * Create an instance of {@link GetCommunicationPartyDetails }
     * 
     */
    public GetCommunicationPartyDetails createGetCommunicationPartyDetails() {
        return new GetCommunicationPartyDetails();
    }

    /**
     * Create an instance of {@link EntityLogEntryData }
     * 
     */
    public EntityLogEntryData createEntityLogEntryData() {
        return new EntityLogEntryData();
    }

    /**
     * Create an instance of {@link UpdateOrganizationPersonDetails }
     * 
     */
    public UpdateOrganizationPersonDetails createUpdateOrganizationPersonDetails() {
        return new UpdateOrganizationPersonDetails();
    }

    /**
     * Create an instance of {@link ElectronicAddress }
     * 
     */
    public ElectronicAddress createElectronicAddress() {
        return new ElectronicAddress();
    }

    /**
     * Create an instance of {@link SearchCertificatesForEncryption }
     * 
     */
    public SearchCertificatesForEncryption createSearchCertificatesForEncryption() {
        return new SearchCertificatesForEncryption();
    }

    /**
     * Create an instance of {@link ArrayOfSpeciality }
     * 
     */
    public ArrayOfSpeciality createArrayOfSpeciality() {
        return new ArrayOfSpeciality();
    }

    /**
     * Create an instance of {@link SearchFacet }
     * 
     */
    public SearchFacet createSearchFacet() {
        return new SearchFacet();
    }

    /**
     * Create an instance of {@link GetChangeLogResponse }
     * 
     */
    public GetChangeLogResponse createGetChangeLogResponse() {
        return new GetChangeLogResponse();
    }

    /**
     * Create an instance of {@link SearchById }
     * 
     */
    public SearchById createSearchById() {
        return new SearchById();
    }

    /**
     * Create an instance of {@link GetCertificateDetailsForEncryptionResponse }
     * 
     */
    public GetCertificateDetailsForEncryptionResponse createGetCertificateDetailsForEncryptionResponse() {
        return new GetCertificateDetailsForEncryptionResponse();
    }

    /**
     * Create an instance of {@link ArrayOfAuthorization }
     * 
     */
    public ArrayOfAuthorization createArrayOfAuthorization() {
        return new ArrayOfAuthorization();
    }

    /**
     * Create an instance of {@link GetCommunicationPartyDetailsResponse }
     * 
     */
    public GetCommunicationPartyDetailsResponse createGetCommunicationPartyDetailsResponse() {
        return new GetCommunicationPartyDetailsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfPhysicalAddress }
     * 
     */
    public ArrayOfPhysicalAddress createArrayOfPhysicalAddress() {
        return new ArrayOfPhysicalAddress();
    }

    /**
     * Create an instance of {@link UpdateOrganizationDetailsResponse }
     * 
     */
    public UpdateOrganizationDetailsResponse createUpdateOrganizationDetailsResponse() {
        return new UpdateOrganizationDetailsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfKeyValuePairOfstringstring }
     * 
     */
    public ArrayOfKeyValuePairOfstringstring createArrayOfKeyValuePairOfstringstring() {
        return new ArrayOfKeyValuePairOfstringstring();
    }

    /**
     * Create an instance of {@link GetCertificateDetailsForValidatingSignature }
     * 
     */
    public GetCertificateDetailsForValidatingSignature createGetCertificateDetailsForValidatingSignature() {
        return new GetCertificateDetailsForValidatingSignature();
    }

    /**
     * Create an instance of {@link SetCommunicationPartyValidResponse }
     * 
     */
    public SetCommunicationPartyValidResponse createSetCommunicationPartyValidResponse() {
        return new SetCommunicationPartyValidResponse();
    }

    /**
     * Create an instance of {@link Department }
     * 
     */
    public Department createDepartment() {
        return new Department();
    }

    /**
     * Create an instance of {@link ArrayOfCertificateDetails }
     * 
     */
    public ArrayOfCertificateDetails createArrayOfCertificateDetails() {
        return new ArrayOfCertificateDetails();
    }

    /**
     * Create an instance of {@link GetOrganizationDetailsResponse }
     * 
     */
    public GetOrganizationDetailsResponse createGetOrganizationDetailsResponse() {
        return new GetOrganizationDetailsResponse();
    }

    /**
     * Create an instance of {@link CreateOrganizationResponse }
     * 
     */
    public CreateOrganizationResponse createCreateOrganizationResponse() {
        return new CreateOrganizationResponse();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link ArrayOfEntityLogEntry }
     * 
     */
    public ArrayOfEntityLogEntry createArrayOfEntityLogEntry() {
        return new ArrayOfEntityLogEntry();
    }

    /**
     * Create an instance of {@link OrganizationUpdate }
     * 
     */
    public OrganizationUpdate createOrganizationUpdate() {
        return new OrganizationUpdate();
    }

    /**
     * Create an instance of {@link ArrayOfSearchResultFacetEntry }
     * 
     */
    public ArrayOfSearchResultFacetEntry createArrayOfSearchResultFacetEntry() {
        return new ArrayOfSearchResultFacetEntry();
    }

    /**
     * Create an instance of {@link SearchByIdResponse }
     * 
     */
    public SearchByIdResponse createSearchByIdResponse() {
        return new SearchByIdResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link CommunicationPartyStatistics }
     * 
     */
    public CommunicationPartyStatistics createCommunicationPartyStatistics() {
        return new CommunicationPartyStatistics();
    }

    /**
     * Create an instance of {@link KeyValuePairOfstringstring }
     * 
     */
    public KeyValuePairOfstringstring createKeyValuePairOfstringstring() {
        return new KeyValuePairOfstringstring();
    }

    /**
     * Create an instance of {@link UpdateDepartmentDetailsResponse }
     * 
     */
    public UpdateDepartmentDetailsResponse createUpdateDepartmentDetailsResponse() {
        return new UpdateDepartmentDetailsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfElectronicAddress }
     * 
     */
    public ArrayOfElectronicAddress createArrayOfElectronicAddress() {
        return new ArrayOfElectronicAddress();
    }

    /**
     * Create an instance of {@link SearchCertificatesForValidatingSignatureResponse }
     * 
     */
    public SearchCertificatesForValidatingSignatureResponse createSearchCertificatesForValidatingSignatureResponse() {
        return new SearchCertificatesForValidatingSignatureResponse();
    }

    /**
     * Create an instance of {@link GenericFault }
     * 
     */
    public GenericFault createGenericFault() {
        return new GenericFault();
    }

    /**
     * Create an instance of {@link ArrayOfOrganizationPerson }
     * 
     */
    public ArrayOfOrganizationPerson createArrayOfOrganizationPerson() {
        return new ArrayOfOrganizationPerson();
    }

    /**
     * Create an instance of {@link CitizenId }
     * 
     */
    public CitizenId createCitizenId() {
        return new CitizenId();
    }

    /**
     * Create an instance of {@link GetServiceDetailsResponse }
     * 
     */
    public GetServiceDetailsResponse createGetServiceDetailsResponse() {
        return new GetServiceDetailsResponse();
    }

    /**
     * Create an instance of {@link OrganizationCreate }
     * 
     */
    public OrganizationCreate createOrganizationCreate() {
        return new OrganizationCreate();
    }

    /**
     * Create an instance of {@link SearchResult }
     * 
     */
    public SearchResult createSearchResult() {
        return new SearchResult();
    }

    /**
     * Create an instance of {@link ArrayOfOrganization }
     * 
     */
    public ArrayOfOrganization createArrayOfOrganization() {
        return new ArrayOfOrganization();
    }

    /**
     * Create an instance of {@link HPRInformation }
     * 
     */
    public HPRInformation createHPRInformation() {
        return new HPRInformation();
    }

    /**
     * Create an instance of {@link ArrayOfService }
     * 
     */
    public ArrayOfService createArrayOfService() {
        return new ArrayOfService();
    }

    /**
     * Create an instance of {@link GetCertificateForValidatingSignature }
     * 
     */
    public GetCertificateForValidatingSignature createGetCertificateForValidatingSignature() {
        return new GetCertificateForValidatingSignature();
    }

    /**
     * Create an instance of {@link CreateService }
     * 
     */
    public CreateService createCreateService() {
        return new CreateService();
    }

    /**
     * Create an instance of {@link Organization }
     * 
     */
    public Organization createOrganization() {
        return new Organization();
    }

    /**
     * Create an instance of {@link GetCertificateDetailsForValidatingSignatureResponse }
     * 
     */
    public GetCertificateDetailsForValidatingSignatureResponse createGetCertificateDetailsForValidatingSignatureResponse() {
        return new GetCertificateDetailsForValidatingSignatureResponse();
    }

    /**
     * Create an instance of {@link Requisition }
     * 
     */
    public Requisition createRequisition() {
        return new Requisition();
    }

    /**
     * Create an instance of {@link Period }
     * 
     */
    public Period createPeriod() {
        return new Period();
    }

    /**
     * Create an instance of {@link UpdateServiceDetailsResponse }
     * 
     */
    public UpdateServiceDetailsResponse createUpdateServiceDetailsResponse() {
        return new UpdateServiceDetailsResponse();
    }

    /**
     * Create an instance of {@link ArrayOfCommunicationParty }
     * 
     */
    public ArrayOfCommunicationParty createArrayOfCommunicationParty() {
        return new ArrayOfCommunicationParty();
    }

    /**
     * Create an instance of {@link BusinessTypeStatistics }
     * 
     */
    public BusinessTypeStatistics createBusinessTypeStatistics() {
        return new BusinessTypeStatistics();
    }

    /**
     * Create an instance of {@link SearchCertificatesForEncryptionResponse }
     * 
     */
    public SearchCertificatesForEncryptionResponse createSearchCertificatesForEncryptionResponse() {
        return new SearchCertificatesForEncryptionResponse();
    }

    /**
     * Create an instance of {@link SearchCertificatesForValidatingSignature }
     * 
     */
    public SearchCertificatesForValidatingSignature createSearchCertificatesForValidatingSignature() {
        return new SearchCertificatesForValidatingSignature();
    }

    /**
     * Create an instance of {@link ValidationFault }
     * 
     */
    public ValidationFault createValidationFault() {
        return new ValidationFault();
    }

    /**
     * Create an instance of {@link ArrayOfstring }
     * 
     */
    public ArrayOfstring createArrayOfstring() {
        return new ArrayOfstring();
    }

    /**
     * Create an instance of {@link CommunicationParty }
     * 
     */
    public CommunicationParty createCommunicationParty() {
        return new CommunicationParty();
    }

    /**
     * Create an instance of {@link CreateOrganizationPersonResponse }
     * 
     */
    public CreateOrganizationPersonResponse createCreateOrganizationPersonResponse() {
        return new CreateOrganizationPersonResponse();
    }

    /**
     * Create an instance of {@link SearchResultFacetEntry }
     * 
     */
    public SearchResultFacetEntry createSearchResultFacetEntry() {
        return new SearchResultFacetEntry();
    }

    /**
     * Create an instance of {@link GetChangeLog }
     * 
     */
    public GetChangeLog createGetChangeLog() {
        return new GetChangeLog();
    }

    /**
     * Create an instance of {@link CommunicationPartySearch }
     * 
     */
    public CommunicationPartySearch createCommunicationPartySearch() {
        return new CommunicationPartySearch();
    }

    /**
     * Create an instance of {@link UpdateDepartmentDetails }
     * 
     */
    public UpdateDepartmentDetails createUpdateDepartmentDetails() {
        return new UpdateDepartmentDetails();
    }

    /**
     * Create an instance of {@link Authorization }
     * 
     */
    public Authorization createAuthorization() {
        return new Authorization();
    }

    /**
     * Create an instance of {@link OrganizationPersonCreate }
     * 
     */
    public OrganizationPersonCreate createOrganizationPersonCreate() {
        return new OrganizationPersonCreate();
    }

    /**
     * Create an instance of {@link OrganizationPersonUpdate }
     * 
     */
    public OrganizationPersonUpdate createOrganizationPersonUpdate() {
        return new OrganizationPersonUpdate();
    }

    /**
     * Create an instance of {@link CreateDepartment }
     * 
     */
    public CreateDepartment createCreateDepartment() {
        return new CreateDepartment();
    }

    /**
     * Create an instance of {@link SetCommunicationPartyValid }
     * 
     */
    public SetCommunicationPartyValid createSetCommunicationPartyValid() {
        return new SetCommunicationPartyValid();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link CreateOrganizationPerson }
     * 
     */
    public CreateOrganizationPerson createCreateOrganizationPerson() {
        return new CreateOrganizationPerson();
    }

    /**
     * Create an instance of {@link GetCommunicationPartyStatisticsResponse }
     * 
     */
    public GetCommunicationPartyStatisticsResponse createGetCommunicationPartyStatisticsResponse() {
        return new GetCommunicationPartyStatisticsResponse();
    }

    /**
     * Create an instance of {@link CreateDepartmentResponse }
     * 
     */
    public CreateDepartmentResponse createCreateDepartmentResponse() {
        return new CreateDepartmentResponse();
    }

    /**
     * Create an instance of {@link ServiceUpdate }
     * 
     */
    public ServiceUpdate createServiceUpdate() {
        return new ServiceUpdate();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ArrayOfCode")
    public JAXBElement<ArrayOfCode> createArrayOfCode(ArrayOfCode value) {
        return new JAXBElement<ArrayOfCode>(_ArrayOfCode_QNAME, ArrayOfCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommunicationParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CommunicationParty")
    public JAXBElement<CommunicationParty> createCommunicationParty(CommunicationParty value) {
        return new JAXBElement<CommunicationParty>(_CommunicationParty_QNAME, CommunicationParty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommunicationPartyStatistics }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CommunicationPartyStatistics")
    public JAXBElement<CommunicationPartyStatistics> createCommunicationPartyStatistics(CommunicationPartyStatistics value) {
        return new JAXBElement<CommunicationPartyStatistics>(_CommunicationPartyStatistics_QNAME, CommunicationPartyStatistics.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationError }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ValidationError")
    public JAXBElement<ValidationError> createValidationError(ValidationError value) {
        return new JAXBElement<ValidationError>(_ValidationError_QNAME, ValidationError.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Requisition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Requisition")
    public JAXBElement<Requisition> createRequisition(Requisition value) {
        return new JAXBElement<Requisition>(_Requisition_QNAME, Requisition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOrganization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfOrganization")
    public JAXBElement<ArrayOfOrganization> createArrayOfOrganization(ArrayOfOrganization value) {
        return new JAXBElement<ArrayOfOrganization>(_ArrayOfOrganization_QNAME, ArrayOfOrganization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HPRInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "HPRInformation")
    public JAXBElement<HPRInformation> createHPRInformation(HPRInformation value) {
        return new JAXBElement<HPRInformation>(_HPRInformation_QNAME, HPRInformation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntryData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ArrayOfEntityLogEntryData")
    public JAXBElement<ArrayOfEntityLogEntryData> createArrayOfEntityLogEntryData(ArrayOfEntityLogEntryData value) {
        return new JAXBElement<ArrayOfEntityLogEntryData>(_ArrayOfEntityLogEntryData_QNAME, ArrayOfEntityLogEntryData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityLogEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "EntityLogEntry")
    public JAXBElement<EntityLogEntry> createEntityLogEntry(EntityLogEntry value) {
        return new JAXBElement<EntityLogEntry>(_EntityLogEntry_QNAME, EntityLogEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ServiceCreate")
    public JAXBElement<ServiceCreate> createServiceCreate(ServiceCreate value) {
        return new JAXBElement<ServiceCreate>(_ServiceCreate_QNAME, ServiceCreate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOrganizationPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfOrganizationPerson")
    public JAXBElement<ArrayOfOrganizationPerson> createArrayOfOrganizationPerson(ArrayOfOrganizationPerson value) {
        return new JAXBElement<ArrayOfOrganizationPerson>(_ArrayOfOrganizationPerson_QNAME, ArrayOfOrganizationPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyValuePairOfstringstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.Collections.Generic", name = "KeyValuePairOfstringstring")
    public JAXBElement<KeyValuePairOfstringstring> createKeyValuePairOfstringstring(KeyValuePairOfstringstring value) {
        return new JAXBElement<KeyValuePairOfstringstring>(_KeyValuePairOfstringstring_QNAME, KeyValuePairOfstringstring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSearchFacet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfSearchFacet")
    public JAXBElement<ArrayOfSearchFacet> createArrayOfSearchFacet(ArrayOfSearchFacet value) {
        return new JAXBElement<ArrayOfSearchFacet>(_ArrayOfSearchFacet_QNAME, ArrayOfSearchFacet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ArrayOfEntityLogEntry")
    public JAXBElement<ArrayOfEntityLogEntry> createArrayOfEntityLogEntry(ArrayOfEntityLogEntry value) {
        return new JAXBElement<ArrayOfEntityLogEntry>(_ArrayOfEntityLogEntry_QNAME, ArrayOfEntityLogEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Period")
    public JAXBElement<Period> createPeriod(Period value) {
        return new JAXBElement<Period>(_Period_QNAME, Period.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityLogEntryData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "EntityLogEntryData")
    public JAXBElement<EntityLogEntryData> createEntityLogEntryData(EntityLogEntryData value) {
        return new JAXBElement<EntityLogEntryData>(_EntityLogEntryData_QNAME, EntityLogEntryData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Code")
    public JAXBElement<Code> createCode(Code value) {
        return new JAXBElement<Code>(_Code_QNAME, Code.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfAuthorization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "ArrayOfAuthorization")
    public JAXBElement<ArrayOfAuthorization> createArrayOfAuthorization(ArrayOfAuthorization value) {
        return new JAXBElement<ArrayOfAuthorization>(_ArrayOfAuthorization_QNAME, ArrayOfAuthorization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartmentCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "DepartmentCreate")
    public JAXBElement<DepartmentCreate> createDepartmentCreate(DepartmentCreate value) {
        return new JAXBElement<DepartmentCreate>(_DepartmentCreate_QNAME, DepartmentCreate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDepartment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfDepartment")
    public JAXBElement<ArrayOfDepartment> createArrayOfDepartment(ArrayOfDepartment value) {
        return new JAXBElement<ArrayOfDepartment>(_ArrayOfDepartment_QNAME, ArrayOfDepartment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "OrganizationCreate")
    public JAXBElement<OrganizationCreate> createOrganizationCreate(OrganizationCreate value) {
        return new JAXBElement<OrganizationCreate>(_OrganizationCreate_QNAME, OrganizationCreate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CitizenIdType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "CitizenIdType")
    public JAXBElement<CitizenIdType> createCitizenIdType(CitizenIdType value) {
        return new JAXBElement<CitizenIdType>(_CitizenIdType_QNAME, CitizenIdType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Department }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Department")
    public JAXBElement<Department> createDepartment(Department value) {
        return new JAXBElement<Department>(_Department_QNAME, Department.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Authorization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Authorization")
    public JAXBElement<Authorization> createAuthorization(Authorization value) {
        return new JAXBElement<Authorization>(_Authorization_QNAME, Authorization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartmentUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "DepartmentUpdate")
    public JAXBElement<DepartmentUpdate> createDepartmentUpdate(DepartmentUpdate value) {
        return new JAXBElement<DepartmentUpdate>(_DepartmentUpdate_QNAME, DepartmentUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "GenericFault")
    public JAXBElement<GenericFault> createGenericFault(GenericFault value) {
        return new JAXBElement<GenericFault>(_GenericFault_QNAME, GenericFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.Collections.Generic", name = "ArrayOfKeyValuePairOfstringstring")
    public JAXBElement<ArrayOfKeyValuePairOfstringstring> createArrayOfKeyValuePairOfstringstring(ArrayOfKeyValuePairOfstringstring value) {
        return new JAXBElement<ArrayOfKeyValuePairOfstringstring>(_ArrayOfKeyValuePairOfstringstring_QNAME, ArrayOfKeyValuePairOfstringstring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CitizenId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "CitizenId")
    public JAXBElement<CitizenId> createCitizenId(CitizenId value) {
        return new JAXBElement<CitizenId>(_CitizenId_QNAME, CitizenId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "OrganizationUpdate")
    public JAXBElement<OrganizationUpdate> createOrganizationUpdate(OrganizationUpdate value) {
        return new JAXBElement<OrganizationUpdate>(_OrganizationUpdate_QNAME, OrganizationUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCertificateDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CertificateDetails", name = "ArrayOfCertificateDetails")
    public JAXBElement<ArrayOfCertificateDetails> createArrayOfCertificateDetails(ArrayOfCertificateDetails value) {
        return new JAXBElement<ArrayOfCertificateDetails>(_ArrayOfCertificateDetails_QNAME, ArrayOfCertificateDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ArrayOfElectronicAddress")
    public JAXBElement<ArrayOfElectronicAddress> createArrayOfElectronicAddress(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_ArrayOfElectronicAddress_QNAME, ArrayOfElectronicAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommunicationPartySearch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CommunicationPartySearch")
    public JAXBElement<CommunicationPartySearch> createCommunicationPartySearch(CommunicationPartySearch value) {
        return new JAXBElement<CommunicationPartySearch>(_CommunicationPartySearch_QNAME, CommunicationPartySearch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ValidationFault")
    public JAXBElement<ValidationFault> createValidationFault(ValidationFault value) {
        return new JAXBElement<ValidationFault>(_ValidationFault_QNAME, ValidationFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ElectronicAddress")
    public JAXBElement<ElectronicAddress> createElectronicAddress(ElectronicAddress value) {
        return new JAXBElement<ElectronicAddress>(_ElectronicAddress_QNAME, ElectronicAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfValidationError }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ArrayOfValidationError")
    public JAXBElement<ArrayOfValidationError> createArrayOfValidationError(ArrayOfValidationError value) {
        return new JAXBElement<ArrayOfValidationError>(_ArrayOfValidationError_QNAME, ArrayOfValidationError.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCommunicationParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfCommunicationParty")
    public JAXBElement<ArrayOfCommunicationParty> createArrayOfCommunicationParty(ArrayOfCommunicationParty value) {
        return new JAXBElement<ArrayOfCommunicationParty>(_ArrayOfCommunicationParty_QNAME, ArrayOfCommunicationParty.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ArrayOfPhysicalAddress")
    public JAXBElement<ArrayOfPhysicalAddress> createArrayOfPhysicalAddress(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_ArrayOfPhysicalAddress_QNAME, ArrayOfPhysicalAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfService")
    public JAXBElement<ArrayOfService> createArrayOfService(ArrayOfService value) {
        return new JAXBElement<ArrayOfService>(_ArrayOfService_QNAME, ArrayOfService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Service }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Service")
    public JAXBElement<Service> createService(Service value) {
        return new JAXBElement<Service>(_Service_QNAME, Service.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationPersonCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "OrganizationPersonCreate")
    public JAXBElement<OrganizationPersonCreate> createOrganizationPersonCreate(OrganizationPersonCreate value) {
        return new JAXBElement<OrganizationPersonCreate>(_OrganizationPersonCreate_QNAME, OrganizationPersonCreate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpeciality }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "ArrayOfSpeciality")
    public JAXBElement<ArrayOfSpeciality> createArrayOfSpeciality(ArrayOfSpeciality value) {
        return new JAXBElement<ArrayOfSpeciality>(_ArrayOfSpeciality_QNAME, ArrayOfSpeciality.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ServiceUpdate")
    public JAXBElement<ServiceUpdate> createServiceUpdate(ServiceUpdate value) {
        return new JAXBElement<ServiceUpdate>(_ServiceUpdate_QNAME, ServiceUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CertificateDetails", name = "CertificateDetails")
    public JAXBElement<CertificateDetails> createCertificateDetails(CertificateDetails value) {
        return new JAXBElement<CertificateDetails>(_CertificateDetails_QNAME, CertificateDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBusinessTypeStatistics }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfBusinessTypeStatistics")
    public JAXBElement<ArrayOfBusinessTypeStatistics> createArrayOfBusinessTypeStatistics(ArrayOfBusinessTypeStatistics value) {
        return new JAXBElement<ArrayOfBusinessTypeStatistics>(_ArrayOfBusinessTypeStatistics_QNAME, ArrayOfBusinessTypeStatistics.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Organization")
    public JAXBElement<Organization> createOrganization(Organization value) {
        return new JAXBElement<Organization>(_Organization_QNAME, Organization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/Arrays", name = "ArrayOfstring")
    public JAXBElement<ArrayOfstring> createArrayOfstring(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_ArrayOfstring_QNAME, ArrayOfstring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSearchResultFacetEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ArrayOfSearchResultFacetEntry")
    public JAXBElement<ArrayOfSearchResultFacetEntry> createArrayOfSearchResultFacetEntry(ArrayOfSearchResultFacetEntry value) {
        return new JAXBElement<ArrayOfSearchResultFacetEntry>(_ArrayOfSearchResultFacetEntry_QNAME, ArrayOfSearchResultFacetEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link List }{@code <}{@link String }{@code >}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CommunicationPartyType")
    public JAXBElement<List<String>> createCommunicationPartyType(List<String> value) {
        return new JAXBElement<List<String>>(_CommunicationPartyType_QNAME, ((Class) List.class), null, ((List<String> ) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "OrganizationPerson")
    public JAXBElement<OrganizationPerson> createOrganizationPerson(OrganizationPerson value) {
        return new JAXBElement<OrganizationPerson>(_OrganizationPerson_QNAME, OrganizationPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BusinessTypeStatistics }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessTypeStatistics")
    public JAXBElement<BusinessTypeStatistics> createBusinessTypeStatistics(BusinessTypeStatistics value) {
        return new JAXBElement<BusinessTypeStatistics>(_BusinessTypeStatistics_QNAME, BusinessTypeStatistics.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationPersonUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "OrganizationPersonUpdate")
    public JAXBElement<OrganizationPersonUpdate> createOrganizationPersonUpdate(OrganizationPersonUpdate value) {
        return new JAXBElement<OrganizationPersonUpdate>(_OrganizationPersonUpdate_QNAME, OrganizationPersonUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchResult")
    public JAXBElement<SearchResult> createSearchResult(SearchResult value) {
        return new JAXBElement<SearchResult>(_SearchResult_QNAME, SearchResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "PhysicalAddress")
    public JAXBElement<PhysicalAddress> createPhysicalAddress(PhysicalAddress value) {
        return new JAXBElement<PhysicalAddress>(_PhysicalAddress_QNAME, PhysicalAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Speciality }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Speciality")
    public JAXBElement<Speciality> createSpeciality(Speciality value) {
        return new JAXBElement<Speciality>(_Speciality_QNAME, Speciality.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFacet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchFacet")
    public JAXBElement<SearchFacet> createSearchFacet(SearchFacet value) {
        return new JAXBElement<SearchFacet>(_SearchFacet_QNAME, SearchFacet.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResultFacetEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchResultFacetEntry")
    public JAXBElement<SearchResultFacetEntry> createSearchResultFacetEntry(SearchResultFacetEntry value) {
        return new JAXBElement<SearchResultFacetEntry>(_SearchResultFacetEntry_QNAME, SearchResultFacetEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CreateOrganizationResult", scope = CreateOrganizationResponse.class)
    public JAXBElement<Organization> createCreateOrganizationResponseCreateOrganizationResult(Organization value) {
        return new JAXBElement<Organization>(_CreateOrganizationResponseCreateOrganizationResult_QNAME, Organization.class, CreateOrganizationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ByUser", scope = EntityLogEntry.class)
    public JAXBElement<String> createEntityLogEntryByUser(String value) {
        return new JAXBElement<String>(_EntityLogEntryByUser_QNAME, String.class, EntityLogEntry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Description", scope = EntityLogEntry.class)
    public JAXBElement<String> createEntityLogEntryDescription(String value) {
        return new JAXBElement<String>(_EntityLogEntryDescription_QNAME, String.class, EntityLogEntry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntryData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Changes", scope = EntityLogEntry.class)
    public JAXBElement<ArrayOfEntityLogEntryData> createEntityLogEntryChanges(ArrayOfEntityLogEntryData value) {
        return new JAXBElement<ArrayOfEntityLogEntryData>(_EntityLogEntryChanges_QNAME, ArrayOfEntityLogEntryData.class, EntityLogEntry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommunicationPartySearch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "search", scope = Search.class)
    public JAXBElement<CommunicationPartySearch> createSearchSearch(CommunicationPartySearch value) {
        return new JAXBElement<CommunicationPartySearch>(_SearchSearch_QNAME, CommunicationPartySearch.class, Search.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "SimpleType", scope = Code.class)
    public JAXBElement<String> createCodeSimpleType(String value) {
        return new JAXBElement<String>(_CodeSimpleType_QNAME, String.class, Code.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "CodeValue", scope = Code.class)
    public JAXBElement<String> createCodeCodeValue(String value) {
        return new JAXBElement<String>(_CodeCodeValue_QNAME, String.class, Code.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "CodeText", scope = Code.class)
    public JAXBElement<String> createCodeCodeText(String value) {
        return new JAXBElement<String>(_CodeCodeText_QNAME, String.class, Code.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = OrganizationUpdate.class)
    public JAXBElement<ArrayOfElectronicAddress> createOrganizationUpdateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, OrganizationUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessType", scope = OrganizationUpdate.class)
    public JAXBElement<Code> createOrganizationUpdateBusinessType(Code value) {
        return new JAXBElement<Code>(_OrganizationUpdateBusinessType_QNAME, Code.class, OrganizationUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = OrganizationUpdate.class)
    public JAXBElement<Period> createOrganizationUpdateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, OrganizationUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = OrganizationUpdate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createOrganizationUpdatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, OrganizationUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "DisplayName", scope = OrganizationUpdate.class)
    public JAXBElement<String> createOrganizationUpdateDisplayName(String value) {
        return new JAXBElement<String>(_OrganizationUpdateDisplayName_QNAME, String.class, OrganizationUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCommunicationParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchByIdResult", scope = SearchByIdResponse.class)
    public JAXBElement<ArrayOfCommunicationParty> createSearchByIdResponseSearchByIdResult(ArrayOfCommunicationParty value) {
        return new JAXBElement<ArrayOfCommunicationParty>(_SearchByIdResponseSearchByIdResult_QNAME, ArrayOfCommunicationParty.class, SearchByIdResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "MiddleName", scope = Person.class)
    public JAXBElement<String> createPersonMiddleName(String value) {
        return new JAXBElement<String>(_PersonMiddleName_QNAME, String.class, Person.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "FirstName", scope = Person.class)
    public JAXBElement<String> createPersonFirstName(String value) {
        return new JAXBElement<String>(_PersonFirstName_QNAME, String.class, Person.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Sex", scope = Person.class)
    public JAXBElement<Code> createPersonSex(Code value) {
        return new JAXBElement<Code>(_PersonSex_QNAME, Code.class, Person.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HPRInformation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "HPRInformation", scope = Person.class)
    public JAXBElement<HPRInformation> createPersonHPRInformation(HPRInformation value) {
        return new JAXBElement<HPRInformation>(_HPRInformation_QNAME, HPRInformation.class, Person.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CitizenId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "CitizenId", scope = Person.class)
    public JAXBElement<CitizenId> createPersonCitizenId(CitizenId value) {
        return new JAXBElement<CitizenId>(_PersonCitizenId_QNAME, CitizenId.class, Person.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "LastName", scope = Person.class)
    public JAXBElement<String> createPersonLastName(String value) {
        return new JAXBElement<String>(_PersonLastName_QNAME, String.class, Person.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBusinessTypeStatistics }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessTypeStatistics", scope = CommunicationPartyStatistics.class)
    public JAXBElement<ArrayOfBusinessTypeStatistics> createCommunicationPartyStatisticsBusinessTypeStatistics(ArrayOfBusinessTypeStatistics value) {
        return new JAXBElement<ArrayOfBusinessTypeStatistics>(_BusinessTypeStatistics_QNAME, ArrayOfBusinessTypeStatistics.class, CommunicationPartyStatistics.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCertificateDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchCertificatesForValidatingSignatureResult", scope = SearchCertificatesForValidatingSignatureResponse.class)
    public JAXBElement<ArrayOfCertificateDetails> createSearchCertificatesForValidatingSignatureResponseSearchCertificatesForValidatingSignatureResult(ArrayOfCertificateDetails value) {
        return new JAXBElement<ArrayOfCertificateDetails>(_SearchCertificatesForValidatingSignatureResponseSearchCertificatesForValidatingSignatureResult_QNAME, ArrayOfCertificateDetails.class, SearchCertificatesForValidatingSignatureResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Message", scope = GenericFault.class)
    public JAXBElement<String> createGenericFaultMessage(String value) {
        return new JAXBElement<String>(_GenericFaultMessage_QNAME, String.class, GenericFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "ErrorCode", scope = GenericFault.class)
    public JAXBElement<String> createGenericFaultErrorCode(String value) {
        return new JAXBElement<String>(_GenericFaultErrorCode_QNAME, String.class, GenericFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Sex", scope = CitizenId.class)
    public JAXBElement<Code> createCitizenIdSex(Code value) {
        return new JAXBElement<Code>(_CitizenIdSex_QNAME, Code.class, CitizenId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Id", scope = CitizenId.class)
    public JAXBElement<String> createCitizenIdId(String value) {
        return new JAXBElement<String>(_CitizenIdId_QNAME, String.class, CitizenId.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Service }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetServiceDetailsResult", scope = GetServiceDetailsResponse.class)
    public JAXBElement<Service> createGetServiceDetailsResponseGetServiceDetailsResult(Service value) {
        return new JAXBElement<Service>(_GetServiceDetailsResponseGetServiceDetailsResult_QNAME, Service.class, GetServiceDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOrganization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Organizations", scope = OrganizationPerson.class)
    public JAXBElement<ArrayOfOrganization> createOrganizationPersonOrganizations(ArrayOfOrganization value) {
        return new JAXBElement<ArrayOfOrganization>(_OrganizationPersonOrganizations_QNAME, ArrayOfOrganization.class, OrganizationPerson.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDepartment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Departments", scope = OrganizationPerson.class)
    public JAXBElement<ArrayOfDepartment> createOrganizationPersonDepartments(ArrayOfDepartment value) {
        return new JAXBElement<ArrayOfDepartment>(_OrganizationPersonDepartments_QNAME, ArrayOfDepartment.class, OrganizationPerson.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Title", scope = OrganizationPerson.class)
    public JAXBElement<String> createOrganizationPersonTitle(String value) {
        return new JAXBElement<String>(_OrganizationPersonTitle_QNAME, String.class, OrganizationPerson.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Person", scope = OrganizationPerson.class)
    public JAXBElement<Person> createOrganizationPersonPerson(Person value) {
        return new JAXBElement<Person>(_OrganizationPersonPerson_QNAME, Person.class, OrganizationPerson.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Properties", scope = OrganizationPerson.class)
    public JAXBElement<ArrayOfCode> createOrganizationPersonProperties(ArrayOfCode value) {
        return new JAXBElement<ArrayOfCode>(_OrganizationPersonProperties_QNAME, ArrayOfCode.class, OrganizationPerson.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "LocationDescription", scope = Service.class)
    public JAXBElement<String> createServiceLocationDescription(String value) {
        return new JAXBElement<String>(_ServiceLocationDescription_QNAME, String.class, Service.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Code", scope = Service.class)
    public JAXBElement<Code> createServiceCode(Code value) {
        return new JAXBElement<Code>(_ServiceCode_QNAME, Code.class, Service.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Message", scope = ValidationError.class)
    public JAXBElement<String> createValidationErrorMessage(String value) {
        return new JAXBElement<String>(_GenericFaultMessage_QNAME, String.class, ValidationError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Value", scope = ValidationError.class)
    public JAXBElement<String> createValidationErrorValue(String value) {
        return new JAXBElement<String>(_ValidationErrorValue_QNAME, String.class, ValidationError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "DisplayName", scope = ValidationError.class)
    public JAXBElement<String> createValidationErrorDisplayName(String value) {
        return new JAXBElement<String>(_ValidationErrorDisplayName_QNAME, String.class, ValidationError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "PropertyName", scope = ValidationError.class)
    public JAXBElement<String> createValidationErrorPropertyName(String value) {
        return new JAXBElement<String>(_ValidationErrorPropertyName_QNAME, String.class, ValidationError.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = OrganizationCreate.class)
    public JAXBElement<ArrayOfElectronicAddress> createOrganizationCreateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, OrganizationCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessType", scope = OrganizationCreate.class)
    public JAXBElement<Code> createOrganizationCreateBusinessType(Code value) {
        return new JAXBElement<Code>(_OrganizationUpdateBusinessType_QNAME, Code.class, OrganizationCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = OrganizationCreate.class)
    public JAXBElement<Period> createOrganizationCreateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, OrganizationCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = OrganizationCreate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createOrganizationCreatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, OrganizationCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "DisplayName", scope = OrganizationCreate.class)
    public JAXBElement<String> createOrganizationCreateDisplayName(String value) {
        return new JAXBElement<String>(_OrganizationUpdateDisplayName_QNAME, String.class, OrganizationCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Description", scope = PhysicalAddress.class)
    public JAXBElement<String> createPhysicalAddressDescription(String value) {
        return new JAXBElement<String>(_EntityLogEntryDescription_QNAME, String.class, PhysicalAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Postbox", scope = PhysicalAddress.class)
    public JAXBElement<String> createPhysicalAddressPostbox(String value) {
        return new JAXBElement<String>(_PhysicalAddressPostbox_QNAME, String.class, PhysicalAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "StreetAddress", scope = PhysicalAddress.class)
    public JAXBElement<String> createPhysicalAddressStreetAddress(String value) {
        return new JAXBElement<String>(_PhysicalAddressStreetAddress_QNAME, String.class, PhysicalAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "City", scope = PhysicalAddress.class)
    public JAXBElement<String> createPhysicalAddressCity(String value) {
        return new JAXBElement<String>(_PhysicalAddressCity_QNAME, String.class, PhysicalAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "TypeCodeValue", scope = PhysicalAddress.class)
    public JAXBElement<String> createPhysicalAddressTypeCodeValue(String value) {
        return new JAXBElement<String>(_PhysicalAddressTypeCodeValue_QNAME, String.class, PhysicalAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCommunicationParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Results", scope = SearchResult.class)
    public JAXBElement<ArrayOfCommunicationParty> createSearchResultResults(ArrayOfCommunicationParty value) {
        return new JAXBElement<ArrayOfCommunicationParty>(_SearchResultResults_QNAME, ArrayOfCommunicationParty.class, SearchResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSearchFacet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Facets", scope = SearchResult.class)
    public JAXBElement<ArrayOfSearchFacet> createSearchResultFacets(ArrayOfSearchFacet value) {
        return new JAXBElement<ArrayOfSearchFacet>(_SearchResultFacets_QNAME, ArrayOfSearchFacet.class, SearchResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "org", scope = UpdateOrganizationDetails.class)
    public JAXBElement<OrganizationUpdate> createUpdateOrganizationDetailsOrg(OrganizationUpdate value) {
        return new JAXBElement<OrganizationUpdate>(_UpdateOrganizationDetailsOrg_QNAME, OrganizationUpdate.class, UpdateOrganizationDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetCertificateForEncryptionResult", scope = GetCertificateForEncryptionResponse.class)
    public JAXBElement<byte[]> createGetCertificateForEncryptionResponseGetCertificateForEncryptionResult(byte[] value) {
        return new JAXBElement<byte[]>(_GetCertificateForEncryptionResponseGetCertificateForEncryptionResult_QNAME, byte[].class, GetCertificateForEncryptionResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Type", scope = Speciality.class)
    public JAXBElement<Code> createSpecialityType(Code value) {
        return new JAXBElement<Code>(_SpecialityType_QNAME, Code.class, Speciality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Valid", scope = Speciality.class)
    public JAXBElement<Period> createSpecialityValid(Period value) {
        return new JAXBElement<Period>(_SpecialityValid_QNAME, Period.class, Speciality.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "HPRNo", scope = HPRInformation.class)
    public JAXBElement<String> createHPRInformationHPRNo(String value) {
        return new JAXBElement<String>(_HPRInformationHPRNo_QNAME, String.class, HPRInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfAuthorization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Authorizations", scope = HPRInformation.class)
    public JAXBElement<ArrayOfAuthorization> createHPRInformationAuthorizations(ArrayOfAuthorization value) {
        return new JAXBElement<ArrayOfAuthorization>(_HPRInformationAuthorizations_QNAME, ArrayOfAuthorization.class, HPRInformation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = DepartmentCreate.class)
    public JAXBElement<ArrayOfElectronicAddress> createDepartmentCreateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, DepartmentCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessType", scope = DepartmentCreate.class)
    public JAXBElement<Code> createDepartmentCreateBusinessType(Code value) {
        return new JAXBElement<Code>(_OrganizationUpdateBusinessType_QNAME, Code.class, DepartmentCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = DepartmentCreate.class)
    public JAXBElement<Period> createDepartmentCreateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, DepartmentCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = DepartmentCreate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createDepartmentCreatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, DepartmentCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "DisplayName", scope = DepartmentCreate.class)
    public JAXBElement<String> createDepartmentCreateDisplayName(String value) {
        return new JAXBElement<String>(_OrganizationUpdateDisplayName_QNAME, String.class, DepartmentCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetCertificateForValidatingSignatureResult", scope = GetCertificateForValidatingSignatureResponse.class)
    public JAXBElement<byte[]> createGetCertificateForValidatingSignatureResponseGetCertificateForValidatingSignatureResult(byte[] value) {
        return new JAXBElement<byte[]>(_GetCertificateForValidatingSignatureResponseGetCertificateForValidatingSignatureResult_QNAME, byte[].class, GetCertificateForValidatingSignatureResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "t", scope = UpdateServiceDetails.class)
    public JAXBElement<ServiceUpdate> createUpdateServiceDetailsT(ServiceUpdate value) {
        return new JAXBElement<ServiceUpdate>(_UpdateServiceDetailsT_QNAME, ServiceUpdate.class, UpdateServiceDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServiceCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "t", scope = CreateService.class)
    public JAXBElement<ServiceCreate> createCreateServiceT(ServiceCreate value) {
        return new JAXBElement<ServiceCreate>(_UpdateServiceDetailsT_QNAME, ServiceCreate.class, CreateService.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessType", scope = Organization.class)
    public JAXBElement<Code> createOrganizationBusinessType(Code value) {
        return new JAXBElement<Code>(_OrganizationUpdateBusinessType_QNAME, Code.class, Organization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Services", scope = Organization.class)
    public JAXBElement<ArrayOfService> createOrganizationServices(ArrayOfService value) {
        return new JAXBElement<ArrayOfService>(_OrganizationServices_QNAME, ArrayOfService.class, Organization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDepartment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Departments", scope = Organization.class)
    public JAXBElement<ArrayOfDepartment> createOrganizationDepartments(ArrayOfDepartment value) {
        return new JAXBElement<ArrayOfDepartment>(_OrganizationPersonDepartments_QNAME, ArrayOfDepartment.class, Organization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "IndustryCodes", scope = Organization.class)
    public JAXBElement<ArrayOfCode> createOrganizationIndustryCodes(ArrayOfCode value) {
        return new JAXBElement<ArrayOfCode>(_OrganizationIndustryCodes_QNAME, ArrayOfCode.class, Organization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOrganizationPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "People", scope = Organization.class)
    public JAXBElement<ArrayOfOrganizationPerson> createOrganizationPeople(ArrayOfOrganizationPerson value) {
        return new JAXBElement<ArrayOfOrganizationPerson>(_OrganizationPeople_QNAME, ArrayOfOrganizationPerson.class, Organization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetCertificateDetailsForValidatingSignatureResult", scope = GetCertificateDetailsForValidatingSignatureResponse.class)
    public JAXBElement<CertificateDetails> createGetCertificateDetailsForValidatingSignatureResponseGetCertificateDetailsForValidatingSignatureResult(CertificateDetails value) {
        return new JAXBElement<CertificateDetails>(_GetCertificateDetailsForValidatingSignatureResponseGetCertificateDetailsForValidatingSignatureResult_QNAME, CertificateDetails.class, GetCertificateDetailsForValidatingSignatureResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetOrganizationPersonDetailsResult", scope = GetOrganizationPersonDetailsResponse.class)
    public JAXBElement<OrganizationPerson> createGetOrganizationPersonDetailsResponseGetOrganizationPersonDetailsResult(OrganizationPerson value) {
        return new JAXBElement<OrganizationPerson>(_GetOrganizationPersonDetailsResponseGetOrganizationPersonDetailsResult_QNAME, OrganizationPerson.class, GetOrganizationPersonDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Type", scope = Requisition.class)
    public JAXBElement<Code> createRequisitionType(Code value) {
        return new JAXBElement<Code>(_SpecialityType_QNAME, Code.class, Requisition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Valid", scope = Requisition.class)
    public JAXBElement<Period> createRequisitionValid(Period value) {
        return new JAXBElement<Period>(_SpecialityValid_QNAME, Period.class, Requisition.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CertificateDetails", name = "Certificate", scope = CertificateDetails.class)
    public JAXBElement<byte[]> createCertificateDetailsCertificate(byte[] value) {
        return new JAXBElement<byte[]>(_CertificateDetailsCertificate_QNAME, byte[].class, CertificateDetails.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CertificateDetails", name = "LdapUrl", scope = CertificateDetails.class)
    public JAXBElement<String> createCertificateDetailsLdapUrl(String value) {
        return new JAXBElement<String>(_CertificateDetailsLdapUrl_QNAME, String.class, CertificateDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "org", scope = CreateOrganization.class)
    public JAXBElement<OrganizationCreate> createCreateOrganizationOrg(OrganizationCreate value) {
        return new JAXBElement<OrganizationCreate>(_UpdateOrganizationDetailsOrg_QNAME, OrganizationCreate.class, CreateOrganization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = DepartmentUpdate.class)
    public JAXBElement<ArrayOfElectronicAddress> createDepartmentUpdateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, DepartmentUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessType", scope = DepartmentUpdate.class)
    public JAXBElement<Code> createDepartmentUpdateBusinessType(Code value) {
        return new JAXBElement<Code>(_OrganizationUpdateBusinessType_QNAME, Code.class, DepartmentUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = DepartmentUpdate.class)
    public JAXBElement<Period> createDepartmentUpdateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, DepartmentUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = DepartmentUpdate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createDepartmentUpdatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, DepartmentUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "DisplayName", scope = DepartmentUpdate.class)
    public JAXBElement<String> createDepartmentUpdateDisplayName(String value) {
        return new JAXBElement<String>(_OrganizationUpdateDisplayName_QNAME, String.class, DepartmentUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Department }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetDepartmentDetailsResult", scope = GetDepartmentDetailsResponse.class)
    public JAXBElement<Department> createGetDepartmentDetailsResponseGetDepartmentDetailsResult(Department value) {
        return new JAXBElement<Department>(_GetDepartmentDetailsResponseGetDepartmentDetailsResult_QNAME, Department.class, GetDepartmentDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessType", scope = BusinessTypeStatistics.class)
    public JAXBElement<String> createBusinessTypeStatisticsBusinessType(String value) {
        return new JAXBElement<String>(_OrganizationUpdateBusinessType_QNAME, String.class, BusinessTypeStatistics.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCertificateDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchCertificatesForEncryptionResult", scope = SearchCertificatesForEncryptionResponse.class)
    public JAXBElement<ArrayOfCertificateDetails> createSearchCertificatesForEncryptionResponseSearchCertificatesForEncryptionResult(ArrayOfCertificateDetails value) {
        return new JAXBElement<ArrayOfCertificateDetails>(_SearchCertificatesForEncryptionResponseSearchCertificatesForEncryptionResult_QNAME, ArrayOfCertificateDetails.class, SearchCertificatesForEncryptionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = ServiceCreate.class)
    public JAXBElement<ArrayOfElectronicAddress> createServiceCreateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, ServiceCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "LocationDescription", scope = ServiceCreate.class)
    public JAXBElement<String> createServiceCreateLocationDescription(String value) {
        return new JAXBElement<String>(_ServiceLocationDescription_QNAME, String.class, ServiceCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ServiceCode", scope = ServiceCreate.class)
    public JAXBElement<Code> createServiceCreateServiceCode(Code value) {
        return new JAXBElement<Code>(_ServiceCreateServiceCode_QNAME, Code.class, ServiceCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = ServiceCreate.class)
    public JAXBElement<Period> createServiceCreateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, ServiceCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = ServiceCreate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createServiceCreatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, ServiceCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "sn", scope = SearchCertificatesForValidatingSignature.class)
    public JAXBElement<String> createSearchCertificatesForValidatingSignatureSn(String value) {
        return new JAXBElement<String>(_SearchCertificatesForValidatingSignatureSn_QNAME, String.class, SearchCertificatesForValidatingSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "cn", scope = SearchCertificatesForValidatingSignature.class)
    public JAXBElement<String> createSearchCertificatesForValidatingSignatureCn(String value) {
        return new JAXBElement<String>(_SearchCertificatesForValidatingSignatureCn_QNAME, String.class, SearchCertificatesForValidatingSignature.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Service }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CreateServiceResult", scope = CreateServiceResponse.class)
    public JAXBElement<Service> createCreateServiceResponseCreateServiceResult(Service value) {
        return new JAXBElement<Service>(_CreateServiceResponseCreateServiceResult_QNAME, Service.class, CreateServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Name", scope = EntityLogEntryData.class)
    public JAXBElement<String> createEntityLogEntryDataName(String value) {
        return new JAXBElement<String>(_EntityLogEntryDataName_QNAME, String.class, EntityLogEntryData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "NewValue", scope = EntityLogEntryData.class)
    public JAXBElement<String> createEntityLogEntryDataNewValue(String value) {
        return new JAXBElement<String>(_EntityLogEntryDataNewValue_QNAME, String.class, EntityLogEntryData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "OldValue", scope = EntityLogEntryData.class)
    public JAXBElement<String> createEntityLogEntryDataOldValue(String value) {
        return new JAXBElement<String>(_EntityLogEntryDataOldValue_QNAME, String.class, EntityLogEntryData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfValidationError }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Errors", scope = ValidationFault.class)
    public JAXBElement<ArrayOfValidationError> createValidationFaultErrors(ArrayOfValidationError value) {
        return new JAXBElement<ArrayOfValidationError>(_ValidationFaultErrors_QNAME, ArrayOfValidationError.class, ValidationFault.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CreateOrganizationPersonResult", scope = CreateOrganizationPersonResponse.class)
    public JAXBElement<OrganizationPerson> createCreateOrganizationPersonResponseCreateOrganizationPersonResult(OrganizationPerson value) {
        return new JAXBElement<OrganizationPerson>(_CreateOrganizationPersonResponseCreateOrganizationPersonResult_QNAME, OrganizationPerson.class, CreateOrganizationPersonResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = CommunicationParty.class)
    public JAXBElement<ArrayOfElectronicAddress> createCommunicationPartyElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ParentName", scope = CommunicationParty.class)
    public JAXBElement<String> createCommunicationPartyParentName(String value) {
        return new JAXBElement<String>(_CommunicationPartyParentName_QNAME, String.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ParentBusinessType", scope = CommunicationParty.class)
    public JAXBElement<Code> createCommunicationPartyParentBusinessType(Code value) {
        return new JAXBElement<Code>(_CommunicationPartyParentBusinessType_QNAME, Code.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Name", scope = CommunicationParty.class)
    public JAXBElement<String> createCommunicationPartyName(String value) {
        return new JAXBElement<String>(_CommunicationPartyName_QNAME, String.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Municipality", scope = CommunicationParty.class)
    public JAXBElement<Code> createCommunicationPartyMunicipality(Code value) {
        return new JAXBElement<Code>(_CommunicationPartyMunicipality_QNAME, Code.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = CommunicationParty.class)
    public JAXBElement<Period> createCommunicationPartyValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = CommunicationParty.class)
    public JAXBElement<ArrayOfPhysicalAddress> createCommunicationPartyPhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "DisplayName", scope = CommunicationParty.class)
    public JAXBElement<String> createCommunicationPartyDisplayName(String value) {
        return new JAXBElement<String>(_OrganizationUpdateDisplayName_QNAME, String.class, CommunicationParty.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Value", scope = SearchResultFacetEntry.class)
    public JAXBElement<String> createSearchResultFacetEntryValue(String value) {
        return new JAXBElement<String>(_SearchResultFacetEntryValue_QNAME, String.class, SearchResultFacetEntry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Text", scope = SearchResultFacetEntry.class)
    public JAXBElement<String> createSearchResultFacetEntryText(String value) {
        return new JAXBElement<String>(_SearchResultFacetEntryText_QNAME, String.class, SearchResultFacetEntry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Type", scope = SearchResultFacetEntry.class)
    public JAXBElement<String> createSearchResultFacetEntryType(String value) {
        return new JAXBElement<String>(_SearchResultFacetEntryType_QNAME, String.class, SearchResultFacetEntry.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationPersonUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "p", scope = UpdateOrganizationPersonDetails.class)
    public JAXBElement<OrganizationPersonUpdate> createUpdateOrganizationPersonDetailsP(OrganizationPersonUpdate value) {
        return new JAXBElement<OrganizationPersonUpdate>(_UpdateOrganizationPersonDetailsP_QNAME, OrganizationPersonUpdate.class, UpdateOrganizationPersonDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "Address", scope = ElectronicAddress.class)
    public JAXBElement<String> createElectronicAddressAddress(String value) {
        return new JAXBElement<String>(_ElectronicAddressAddress_QNAME, String.class, ElectronicAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/Common", name = "TypeCodeValue", scope = ElectronicAddress.class)
    public JAXBElement<String> createElectronicAddressTypeCodeValue(String value) {
        return new JAXBElement<String>(_PhysicalAddressTypeCodeValue_QNAME, String.class, ElectronicAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "sn", scope = SearchCertificatesForEncryption.class)
    public JAXBElement<String> createSearchCertificatesForEncryptionSn(String value) {
        return new JAXBElement<String>(_SearchCertificatesForValidatingSignatureSn_QNAME, String.class, SearchCertificatesForEncryption.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "cn", scope = SearchCertificatesForEncryption.class)
    public JAXBElement<String> createSearchCertificatesForEncryptionCn(String value) {
        return new JAXBElement<String>(_SearchCertificatesForValidatingSignatureCn_QNAME, String.class, SearchCertificatesForEncryption.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfKeyValuePairOfstringstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchConstraints", scope = CommunicationPartySearch.class)
    public JAXBElement<ArrayOfKeyValuePairOfstringstring> createCommunicationPartySearchSearchConstraints(ArrayOfKeyValuePairOfstringstring value) {
        return new JAXBElement<ArrayOfKeyValuePairOfstringstring>(_CommunicationPartySearchSearchConstraints_QNAME, ArrayOfKeyValuePairOfstringstring.class, CommunicationPartySearch.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartmentUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "org", scope = UpdateDepartmentDetails.class)
    public JAXBElement<DepartmentUpdate> createUpdateDepartmentDetailsOrg(DepartmentUpdate value) {
        return new JAXBElement<DepartmentUpdate>(_UpdateOrganizationDetailsOrg_QNAME, DepartmentUpdate.class, UpdateDepartmentDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CodeValue", scope = SearchFacet.class)
    public JAXBElement<String> createSearchFacetCodeValue(String value) {
        return new JAXBElement<String>(_SearchFacetCodeValue_QNAME, String.class, SearchFacet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Name", scope = SearchFacet.class)
    public JAXBElement<String> createSearchFacetName(String value) {
        return new JAXBElement<String>(_CommunicationPartyName_QNAME, String.class, SearchFacet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSearchResultFacetEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Entries", scope = SearchFacet.class)
    public JAXBElement<ArrayOfSearchResultFacetEntry> createSearchFacetEntries(ArrayOfSearchResultFacetEntry value) {
        return new JAXBElement<ArrayOfSearchResultFacetEntry>(_SearchFacetEntries_QNAME, ArrayOfSearchResultFacetEntry.class, SearchFacet.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "id", scope = SearchById.class)
    public JAXBElement<String> createSearchByIdId(String value) {
        return new JAXBElement<String>(_SearchByIdId_QNAME, String.class, SearchById.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfEntityLogEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetChangeLogResult", scope = GetChangeLogResponse.class)
    public JAXBElement<ArrayOfEntityLogEntry> createGetChangeLogResponseGetChangeLogResult(ArrayOfEntityLogEntry value) {
        return new JAXBElement<ArrayOfEntityLogEntry>(_GetChangeLogResponseGetChangeLogResult_QNAME, ArrayOfEntityLogEntry.class, GetChangeLogResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = OrganizationPersonCreate.class)
    public JAXBElement<ArrayOfElectronicAddress> createOrganizationPersonCreateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "HprNumber", scope = OrganizationPersonCreate.class)
    public JAXBElement<String> createOrganizationPersonCreateHprNumber(String value) {
        return new JAXBElement<String>(_OrganizationPersonCreateHprNumber_QNAME, String.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Professions", scope = OrganizationPersonCreate.class)
    public JAXBElement<ArrayOfstring> createOrganizationPersonCreateProfessions(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_OrganizationPersonCreateProfessions_QNAME, ArrayOfstring.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = OrganizationPersonCreate.class)
    public JAXBElement<Period> createOrganizationPersonCreateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Title", scope = OrganizationPersonCreate.class)
    public JAXBElement<String> createOrganizationPersonCreateTitle(String value) {
        return new JAXBElement<String>(_OrganizationPersonTitle_QNAME, String.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Specialities", scope = OrganizationPersonCreate.class)
    public JAXBElement<ArrayOfstring> createOrganizationPersonCreateSpecialities(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_OrganizationPersonCreateSpecialities_QNAME, ArrayOfstring.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Properties", scope = OrganizationPersonCreate.class)
    public JAXBElement<ArrayOfCode> createOrganizationPersonCreateProperties(ArrayOfCode value) {
        return new JAXBElement<ArrayOfCode>(_OrganizationPersonProperties_QNAME, ArrayOfCode.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = OrganizationPersonCreate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createOrganizationPersonCreatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, OrganizationPersonCreate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetCertificateDetailsForEncryptionResult", scope = GetCertificateDetailsForEncryptionResponse.class)
    public JAXBElement<CertificateDetails> createGetCertificateDetailsForEncryptionResponseGetCertificateDetailsForEncryptionResult(CertificateDetails value) {
        return new JAXBElement<CertificateDetails>(_GetCertificateDetailsForEncryptionResponseGetCertificateDetailsForEncryptionResult_QNAME, CertificateDetails.class, GetCertificateDetailsForEncryptionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Requisition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Requisition", scope = Authorization.class)
    public JAXBElement<Requisition> createAuthorizationRequisition(Requisition value) {
        return new JAXBElement<Requisition>(_Requisition_QNAME, Requisition.class, Authorization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Type", scope = Authorization.class)
    public JAXBElement<Code> createAuthorizationType(Code value) {
        return new JAXBElement<Code>(_SpecialityType_QNAME, Code.class, Authorization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Profession", scope = Authorization.class)
    public JAXBElement<Code> createAuthorizationProfession(Code value) {
        return new JAXBElement<Code>(_AuthorizationProfession_QNAME, Code.class, Authorization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Valid", scope = Authorization.class)
    public JAXBElement<Period> createAuthorizationValid(Period value) {
        return new JAXBElement<Period>(_SpecialityValid_QNAME, Period.class, Authorization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSpeciality }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/HPR", name = "Specialities", scope = Authorization.class)
    public JAXBElement<ArrayOfSpeciality> createAuthorizationSpecialities(ArrayOfSpeciality value) {
        return new JAXBElement<ArrayOfSpeciality>(_AuthorizationSpecialities_QNAME, ArrayOfSpeciality.class, Authorization.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = OrganizationPersonUpdate.class)
    public JAXBElement<ArrayOfElectronicAddress> createOrganizationPersonUpdateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, OrganizationPersonUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Professions", scope = OrganizationPersonUpdate.class)
    public JAXBElement<ArrayOfstring> createOrganizationPersonUpdateProfessions(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_OrganizationPersonCreateProfessions_QNAME, ArrayOfstring.class, OrganizationPersonUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = OrganizationPersonUpdate.class)
    public JAXBElement<Period> createOrganizationPersonUpdateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, OrganizationPersonUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Title", scope = OrganizationPersonUpdate.class)
    public JAXBElement<String> createOrganizationPersonUpdateTitle(String value) {
        return new JAXBElement<String>(_OrganizationPersonTitle_QNAME, String.class, OrganizationPersonUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Specialities", scope = OrganizationPersonUpdate.class)
    public JAXBElement<ArrayOfstring> createOrganizationPersonUpdateSpecialities(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_OrganizationPersonCreateSpecialities_QNAME, ArrayOfstring.class, OrganizationPersonUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Properties", scope = OrganizationPersonUpdate.class)
    public JAXBElement<ArrayOfCode> createOrganizationPersonUpdateProperties(ArrayOfCode value) {
        return new JAXBElement<ArrayOfCode>(_OrganizationPersonProperties_QNAME, ArrayOfCode.class, OrganizationPersonUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = OrganizationPersonUpdate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createOrganizationPersonUpdatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, OrganizationPersonUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommunicationParty }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetCommunicationPartyDetailsResult", scope = GetCommunicationPartyDetailsResponse.class)
    public JAXBElement<CommunicationParty> createGetCommunicationPartyDetailsResponseGetCommunicationPartyDetailsResult(CommunicationParty value) {
        return new JAXBElement<CommunicationParty>(_GetCommunicationPartyDetailsResponseGetCommunicationPartyDetailsResult_QNAME, CommunicationParty.class, GetCommunicationPartyDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepartmentCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "org", scope = CreateDepartment.class)
    public JAXBElement<DepartmentCreate> createCreateDepartmentOrg(DepartmentCreate value) {
        return new JAXBElement<DepartmentCreate>(_UpdateOrganizationDetailsOrg_QNAME, DepartmentCreate.class, CreateDepartment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "period", scope = SetCommunicationPartyValid.class)
    public JAXBElement<Period> createSetCommunicationPartyValidPeriod(Period value) {
        return new JAXBElement<Period>(_SetCommunicationPartyValidPeriod_QNAME, Period.class, SetCommunicationPartyValid.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "SearchResult", scope = SearchResponse.class)
    public JAXBElement<SearchResult> createSearchResponseSearchResult(SearchResult value) {
        return new JAXBElement<SearchResult>(_SearchResult_QNAME, SearchResult.class, SearchResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrganizationPersonCreate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "p", scope = CreateOrganizationPerson.class)
    public JAXBElement<OrganizationPersonCreate> createCreateOrganizationPersonP(OrganizationPersonCreate value) {
        return new JAXBElement<OrganizationPersonCreate>(_UpdateOrganizationPersonDetailsP_QNAME, OrganizationPersonCreate.class, CreateOrganizationPerson.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommunicationPartyStatistics }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetCommunicationPartyStatisticsResult", scope = GetCommunicationPartyStatisticsResponse.class)
    public JAXBElement<CommunicationPartyStatistics> createGetCommunicationPartyStatisticsResponseGetCommunicationPartyStatisticsResult(CommunicationPartyStatistics value) {
        return new JAXBElement<CommunicationPartyStatistics>(_GetCommunicationPartyStatisticsResponseGetCommunicationPartyStatisticsResult_QNAME, CommunicationPartyStatistics.class, GetCommunicationPartyStatisticsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfElectronicAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "ElectronicAddresses", scope = ServiceUpdate.class)
    public JAXBElement<ArrayOfElectronicAddress> createServiceUpdateElectronicAddresses(ArrayOfElectronicAddress value) {
        return new JAXBElement<ArrayOfElectronicAddress>(_OrganizationUpdateElectronicAddresses_QNAME, ArrayOfElectronicAddress.class, ServiceUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "LocationDescription", scope = ServiceUpdate.class)
    public JAXBElement<String> createServiceUpdateLocationDescription(String value) {
        return new JAXBElement<String>(_ServiceLocationDescription_QNAME, String.class, ServiceUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Period }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "Valid", scope = ServiceUpdate.class)
    public JAXBElement<Period> createServiceUpdateValid(Period value) {
        return new JAXBElement<Period>(_OrganizationUpdateValid_QNAME, Period.class, ServiceUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfPhysicalAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PhysicalAddresses", scope = ServiceUpdate.class)
    public JAXBElement<ArrayOfPhysicalAddress> createServiceUpdatePhysicalAddresses(ArrayOfPhysicalAddress value) {
        return new JAXBElement<ArrayOfPhysicalAddress>(_OrganizationUpdatePhysicalAddresses_QNAME, ArrayOfPhysicalAddress.class, ServiceUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "PingResult", scope = PingResponse.class)
    public JAXBElement<String> createPingResponsePingResult(String value) {
        return new JAXBElement<String>(_PingResponsePingResult_QNAME, String.class, PingResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Department }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "CreateDepartmentResult", scope = CreateDepartmentResponse.class)
    public JAXBElement<Department> createCreateDepartmentResponseCreateDepartmentResult(Department value) {
        return new JAXBElement<Department>(_CreateDepartmentResponseCreateDepartmentResult_QNAME, Department.class, CreateDepartmentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Code }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "BusinessType", scope = Department.class)
    public JAXBElement<Code> createDepartmentBusinessType(Code value) {
        return new JAXBElement<Code>(_OrganizationUpdateBusinessType_QNAME, Code.class, Department.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "IndustryCodes", scope = Department.class)
    public JAXBElement<ArrayOfCode> createDepartmentIndustryCodes(ArrayOfCode value) {
        return new JAXBElement<ArrayOfCode>(_OrganizationIndustryCodes_QNAME, ArrayOfCode.class, Department.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfOrganizationPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "People", scope = Department.class)
    public JAXBElement<ArrayOfOrganizationPerson> createDepartmentPeople(ArrayOfOrganizationPerson value) {
        return new JAXBElement<ArrayOfOrganizationPerson>(_OrganizationPeople_QNAME, ArrayOfOrganizationPerson.class, Department.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://register.nhn.no/CommunicationParty", name = "GetOrganizationDetailsResult", scope = GetOrganizationDetailsResponse.class)
    public JAXBElement<Organization> createGetOrganizationDetailsResponseGetOrganizationDetailsResult(Organization value) {
        return new JAXBElement<Organization>(_GetOrganizationDetailsResponseGetOrganizationDetailsResult_QNAME, Organization.class, GetOrganizationDetailsResponse.class, value);
    }

}
