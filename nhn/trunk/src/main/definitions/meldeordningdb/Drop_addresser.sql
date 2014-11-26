-- ********************************************************************************
-- Generated by:   Select Database Schema File Generator                           
-- Model:          \\Enabler\srv-win-utv-02\Prosjekter\Meldesystem EMOK\0          
-- Filename:       Z:\jobb\sandbox\meldeordningdb\Drop_addresser.sql               
-- Database:       ORACLE                                                          
-- Mapping:        Default                                                         
-- Date:           20 February 2012                                                
-- ********************************************************************************

ALTER TABLE Requisition
    DROP CONSTRAINT authorisationrequistion;

ALTER TABLE Department
    DROP CONSTRAINT organisationdepartment;

ALTER TABLE Period
    DROP CONSTRAINT HPRInformationperiod;

ALTER TABLE Period
    DROP CONSTRAINT authorisationperiod;

ALTER TABLE Period
    DROP CONSTRAINT communicationpartyperiod;

ALTER TABLE Period
    DROP CONSTRAINT requistionperiod;

ALTER TABLE Period
    DROP CONSTRAINT spesialityperiod;

ALTER TABLE Organization
    DROP CONSTRAINT Relationship12;

ALTER TABLE Person
    DROP CONSTRAINT orgpersonperson;

ALTER TABLE HPRInformation
    DROP CONSTRAINT personhpr;

ALTER TABLE Speciality
    DROP CONSTRAINT authspes;

ALTER TABLE Organisationpersonline
    DROP CONSTRAINT organisationpersonorganisation;

ALTER TABLE Organisationpersonline
    DROP CONSTRAINT organisationtoperson;

ALTER TABLE Departmentline
    DROP CONSTRAINT departmentorganisasjonperson;

ALTER TABLE Departmentline
    DROP CONSTRAINT orgainsationpersondepartement;

ALTER TABLE Code
    DROP CONSTRAINT authorisationpfofession;

ALTER TABLE Code
    DROP CONSTRAINT communicationpartycode;

ALTER TABLE Code
    DROP CONSTRAINT departementcode;

ALTER TABLE Code
    DROP CONSTRAINT organisationcode;

ALTER TABLE Code
    DROP CONSTRAINT organisationpersoncode;

ALTER TABLE Code
    DROP CONSTRAINT requistiontype;

ALTER TABLE Code
    DROP CONSTRAINT spesialitytype;

ALTER TABLE CitizenId
    DROP CONSTRAINT personcitizen;

ALTER TABLE PhysicalAddress
    DROP CONSTRAINT communicationpartyphysicaladdress;

ALTER TABLE OrganizationPerson
    DROP CONSTRAINT Relationship11;

ALTER TABLE Authorization
    DROP CONSTRAINT HPRInformationauthorisation;

ALTER TABLE ElectronicAddress
    DROP CONSTRAINT communicationpartyelectronicaddress;

ALTER TABLE Service
    DROP CONSTRAINT organisationservice;



DROP TABLE Requisition;



DROP TABLE Department;



DROP TABLE Period;



DROP TABLE Organization;



DROP TABLE Person;



DROP TABLE HPRInformation;



DROP TABLE Speciality;



DROP TABLE Organisationpersonline;



DROP TABLE Departmentline;



DROP TABLE Code;



DROP TABLE CitizenId;



DROP TABLE CommunicationParty;



DROP TABLE PhysicalAddress;



DROP TABLE OrganizationPerson;



DROP TABLE Authorization;



DROP TABLE ElectronicAddress;



DROP TABLE Service;

