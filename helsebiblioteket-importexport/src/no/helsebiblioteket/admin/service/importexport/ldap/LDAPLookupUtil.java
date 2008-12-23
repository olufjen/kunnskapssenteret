package no.helsebiblioteket.admin.service.importexport.ldap;

import no.helsebiblioteket.admin.service.importexport.ldap.domain.LDAPUser;
import no.helsebiblioteket.admin.service.importexport.ldap.exception.LDAPAuthenticationException;
import no.helsebiblioteket.admin.service.importexport.ldap.exception.LDAPUserAlreadyExistException;
import no.helsebiblioteket.admin.service.importexport.ldap.exception.LDAPUserWithEmailAlreadyExistException;
import no.helsebiblioteket.admin.service.importexport.ldap.text.TextResourceUtil;

import javax.naming.directory.Attribute;

import javax.naming.*;
import javax.naming.directory.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Array;
import java.io.UnsupportedEncodingException;


/**
 * Utility for LDAP lookup
 *
 * @author Marius Jakobsen <a href="mailto:majks@wmdata.no">majks@wmdata.no</a>
 * @since 2006.06.21
 */
public class LDAPLookupUtil {
    private static final Logger LOG = Logger.getLogger(LDAPLookupUtil.class.toString());

    private static final LDAPLookupUtil INSTANCE = new LDAPLookupUtil();

    private static final String S_INITIAL_CONTEXT_FACTORY = "initialContextFactory";
    private static final String S_PROVIDER_URL = "providerURL";
    private static final String S_SECURITY_PRINCIPAL = "securityPrincipal";
    private static final String S_SECURITY_CREDENTIALS = "securityCredentials";

    private static final String S_INITIAL_USER_CTX = "initialUserContext";

    private static final String S_ATR_GIVENNAME = "attribute.givenname";
    private static final String S_ATR_MAIL = "attribute.mail";
    private static final String S_ATR_SN = "attribute.sn";
    private static final String S_ATR_UID = "attribute.uid";
    private static final String S_ATR_USER_PASSWORD = "attribute.userpassword";
    private static final String S_ATR_O = "attribute.o";
    private static final String S_ATR_EMPLOYEE_TYPE = "attribute.employeetype";
    private static final String S_ATR_EMPLOYEE_NUMBER = "attribute.employeenumber";
    private static final String S_ATR_MOBILE = "attribute.mobile";
    private static final String S_ATR_PAGER = "attribute.pager";

    private static final String INITIAL_CONTEXT_FACTORY = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_INITIAL_CONTEXT_FACTORY);
    private static final String PROVIDER_URL = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_PROVIDER_URL);
    private static final String SECURITY_PRINCIPAL = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_SECURITY_PRINCIPAL);
    private static final String SECURITY_CREDENTIALS = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_SECURITY_CREDENTIALS);

    private static final String INITIAL_USER_CTX = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_INITIAL_USER_CTX);

    private static final String OBJECT_CLASS = "objectClass";
    private static final String INET_ORG_PERSON = "inetOrgPerson";
    private static final String ATR_CN = "cn";
    private static final String ATR_GIVENNAME = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_GIVENNAME);
    private static final String ATR_MAIL = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_MAIL);
    private static final String ATR_SN = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_SN);
    private static final String ATR_UID = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_UID);
    private static final String ATR_USER_PASSWORD = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_USER_PASSWORD);
    private static final String ATR_O = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_O);
    private static final String ATR_EMPLOYEE_TYPE = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_EMPLOYEE_TYPE);
    private static final String ATR_EMPLOYEE_NUMBER = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_EMPLOYEE_NUMBER);
    private static final String ATR_MOBILE = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_MOBILE);
    private static final String ATR_PAGER = TextResourceUtil.getInstance().getString(LDAPLookupUtil.class, S_ATR_PAGER);

    private static final String[] ATTRIBUTES = new String[]{ATR_CN, ATR_GIVENNAME, ATR_MAIL, ATR_SN, ATR_UID, ATR_USER_PASSWORD, ATR_O, ATR_EMPLOYEE_TYPE, ATR_EMPLOYEE_NUMBER, ATR_MOBILE, ATR_PAGER};

    private DirContext ctx = null;
    private boolean isLoggedIn = false;

    public static LDAPLookupUtil getInstance() {
        return INSTANCE;
    }

    private LDAPLookupUtil() {
    }

    /**
     * Lookup users by full dn in LDAP and return a LDAPUser Collection
     *
     * @param colDn Collection of dn's as String to find in LDAP
     * @return Collection of LDAPUser objects return from LDAP server
     * @throws java.lang.Exception
     * @throws javax.naming.NamingException
     * @since 2006.06.21
     */
    public Collection getLDAPUsers(Collection colDn) throws NamingException, Exception {
        Collection usrs = new ArrayList();
        try {
            doLogIn();

            // TODO will result in multiple lookups in LDAP - can we do better?
            Iterator itr = colDn.iterator();
            while (itr.hasNext()) {
                String dn = (String) itr.next();
                usrs.add(populateLDAPUser(dn, getAttributesFromLDAP(dn)));

            }
        } catch (NamingException e1) {
            LOG.log(Level.SEVERE, e1.getMessage(), e1);
            throw e1;
        } catch (Exception e2) {
            LOG.log(Level.SEVERE, e2.getMessage(), e2);
            throw e2;
        } finally {
            doLogOut();
        }
        return usrs;
    }

    /**
     * Lookup users by givenname and surename in LDAP and return a LDAPUser Collection
     * Will use ending wildcard for each parameter (givenname=abc*)
     *
     * @param sureName  for user in LDAP
     * @param givenName for user in LDAP
     * @return Collection of LDAPUser objects return from LDAP server
     * @throws javax.naming.NamingException
     * @throws com.wmdata.util.ldap.exception.LDAPAuthenticationException
     *
     * @since 2006.06.22
     */
    public Collection getAllLDAPUsers() throws NamingException, LDAPAuthenticationException {
        Collection usrs = new ArrayList();
        // filtermask (&(sn=*)(givenName=*))
        String filter = "(&(sn=*)(givenName=*))";

        // TODO this should either be a parameter or a property
        int limit = 40000;

        try {
            doLogIn();

            Collection colObj = ldapSearch(ATTRIBUTES, filter, INITIAL_USER_CTX, limit);
            if (null != colObj) {
                Iterator itr = colObj.iterator();
                while (itr.hasNext()) {
                    usrs.add(populateLDAPUser((SearchResult) itr.next()));
                }
            }
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPAuthenticationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            doLogOut();
        }
        return usrs;
    }
    
    /**
     * Lookup users by givenname and surename in LDAP and return a LDAPUser Collection
     * Will use ending wildcard for each parameter (givenname=abc*)
     *
     * @param sureName  for user in LDAP
     * @param givenName for user in LDAP
     * @return Collection of LDAPUser objects return from LDAP server
     * @throws javax.naming.NamingException
     * @throws com.wmdata.util.ldap.exception.LDAPAuthenticationException
     *
     * @since 2006.06.22
     */
    public Collection getLDAPUsers(String givenName, String sureName) throws NamingException, LDAPAuthenticationException {
        Collection usrs = new ArrayList();
        // filtermask (&(sn=*)(givenName=*))
        String filter = "(&(" + ATR_SN + "=" + sureName + "*)(" + ATR_GIVENNAME + "=" + givenName + "*))";

        // TODO this should either be a parameter or a property
        int limit = 40000;

        try {
            doLogIn();

            Collection colObj = ldapSearch(ATTRIBUTES, filter, INITIAL_USER_CTX, limit);
            if (null != colObj) {
                Iterator itr = colObj.iterator();
                while (itr.hasNext()) {
                    usrs.add(populateLDAPUser((SearchResult) itr.next()));
                }
            }
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPAuthenticationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            doLogOut();
        }
        return usrs;
    }

    /**
     * Lookup a user by username/uid and return a LDAPUser object
     *
     * @param dn for a user in LDAP
     * @return LDAPUser object populated with LDAP data
     * @throws javax.naming.NamingException
     * @throws com.wmdata.util.ldap.exception.LDAPAuthenticationException
     *
     * @since 2006.06.21
     */
    public LDAPUser getLDAPUserByDN(String dn) throws NamingException, LDAPAuthenticationException {
        LDAPUser usr = null;
        try {
            doLogIn();

            usr = populateLDAPUser(dn, getAttributesFromLDAP(dn));

        } catch (NamingException e1) {
            LOG.log(Level.SEVERE, e1.getMessage(), e1);
            throw e1;
        } catch (LDAPAuthenticationException e2) {
            LOG.log(Level.SEVERE, e2.getMessage(), e2);
            throw e2;
        } finally {
            doLogOut();
        }
        return usr;
    }

    /**
     * Lookup a user by username/uid and return a LDAPUser object
     *
     * @param uid username/uid for a user in LDAP
     * @return LDAPUser object populated with LDAP data
     * @throws javax.naming.NamingException
     * @throws com.wmdata.util.ldap.exception.LDAPAuthenticationException
     *
     * @since 2006.06.21
     */
    public LDAPUser getLDAPUserByUid(String uid) throws NamingException, LDAPAuthenticationException {
        LDAPUser usr = null;

        try {
            doLogIn();

            Collection colObj = searchForUserByUid(uid);
            if (null != colObj) {
                Iterator itr = colObj.iterator();
                while (itr.hasNext()) {
                    usr = populateLDAPUser((SearchResult) itr.next());
                    break;
                }
            }
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPAuthenticationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            doLogOut();
        }
        return usr;
    }


    public LDAPUser getLDAPUserByEmail(String email) throws NamingException, LDAPAuthenticationException {
        LDAPUser usr = null;

        try {
            doLogIn();

            Collection colObj = searchForUserByEmail(email);
            if (null != colObj) {
                Iterator itr = colObj.iterator();
                while (itr.hasNext()) {
                    usr = populateLDAPUser((SearchResult) itr.next());
                    break;
                }
            }
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPAuthenticationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            doLogOut();
        }
        return usr;
    }

    public void createLDAPUser(LDAPUser user) throws LDAPUserWithEmailAlreadyExistException, LDAPUserAlreadyExistException, LDAPAuthenticationException, NamingException {
        try {
            doLogIn();

            String uid = user.getUid();

            Collection colObj = searchForUserByUid(uid);
            if (null != colObj) {
                LOG.log(Level.FINEST, "User alredy exists");
                throw new LDAPUserAlreadyExistException();
            } else {
                colObj = searchForUserByEmail(user.getEmail());
                if (null != colObj) {
                    LOG.log(Level.FINEST, "User with email alredy exists");
                    throw new LDAPUserWithEmailAlreadyExistException();
                } else {
                    createUser(user);
                }
            }
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPAuthenticationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPUserAlreadyExistException e) {
            throw e;
        } catch (LDAPUserWithEmailAlreadyExistException e) {
            throw e;
        } finally {
            doLogOut();
        }
    }

    public void updateLDAPUser(LDAPUser user) throws LDAPUserWithEmailAlreadyExistException, LDAPAuthenticationException, NamingException {
        try {
            doLogIn();

            String uid = user.getUid();

            Collection colObj = searchForUserByUid(uid);

            if (null == colObj) {
                throw new NameNotFoundException();
            } else {
                LDAPUser oldUser = null;
                Iterator itr = colObj.iterator();

                while (itr.hasNext()) {
                    oldUser = populateLDAPUser((SearchResult) itr.next());
                    break;
                }
                
                // check if email exists
                colObj = searchForUserByEmail(user.getEmail());

                if (null != colObj) {
                    itr = colObj.iterator();
                    while (itr.hasNext()) {
                        LDAPUser testUser = populateLDAPUser((SearchResult) itr.next());
                        if (testUser != null && !uid.equals(testUser.getUid())) {
                            LOG.log(Level.FINEST, "User with email alredy exists");
                            throw new LDAPUserWithEmailAlreadyExistException();
                        }
                    }
                }

                if (oldUser != null) {
                    updateUser(user, oldUser);
                } else {
                    throw new NameNotFoundException();
                }
            }
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPAuthenticationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } catch (LDAPUserWithEmailAlreadyExistException e) {
            throw e;
        } finally {
            doLogOut();
        }

    }

    public void deleteLDAPUser(LDAPUser user) throws NamingException, LDAPAuthenticationException {
        try {
            doLogIn();
            deleteUser(user);
        } catch (LDAPAuthenticationException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        } finally {
            doLogOut();
        }
    }

    /**
     * Search for a user by uid by doing an ldapSearch.
     * Login is required
     *
     * @param uid The uid of the user to search for
     * @return Collection All objects found with the given uid
     * @throws NamingException If the search fails an exception is thrown
     */
    private Collection searchForUserByUid(String uid) throws NamingException {
        String filter = "(" + ATR_UID + "=" + uid + ")";
        int limit = 1;

        return ldapSearch(ATTRIBUTES, filter, INITIAL_USER_CTX, limit);
    }

    /**
     * Search for a user by email by doing an ldapSearch.
     * Login is required
     *
     * @param email The email of the user to search for
     * @return Collection All objects found with the given email
     * @throws NamingException If the search fails an exception is thrown
     */
    private Collection searchForUserByEmail(String email) throws NamingException {
        String filter = "(" + ATR_MAIL + "=" + email + ")";
        int limit = 1;

        return ldapSearch(ATTRIBUTES, filter, INITIAL_USER_CTX, limit);
    }

    /**
     * Get attibutes from LDAP by full dn
     *
     * @param dn full dn to data in LDAP
     * @return Attributes object found
     * @throws javax.naming.NamingException
     * @since 2006.06.21
     */
    private Attributes getAttributesFromLDAP(String dn) throws NamingException {
        Attributes atrs;
        try {
            atrs = this.ctx.getAttributes(dn);
        }
        catch (NamingException e) {
            throw e;
        }
        return atrs;
    }

    /**
     * Search the directory for objects with free LDAP input
     *
     * @param attribute - Attributes to return, for ie givenName and sn
     * @param filter    - LDAP filter used in the search ie: (uid=mar*)
     * @param initCtx   - Start searching from this initial context.
     * @param limit     - limitation of returning objects from LDAP
     * @return Collection - Collection of SearchResult objects from LDAP
     * @throws javax.naming.NamingException
     */
    private Collection ldapSearch(String[] attribute, String filter, String initCtx, int limit) throws NamingException {
        NamingEnumeration nEnum;
        SearchResult sr;
        SearchControls sc = new SearchControls();

        sc.setSearchScope(SearchControls.ONELEVEL_SCOPE);
        sc.setReturningAttributes(attribute);
        if (limit > 0) {
            sc.setCountLimit(limit);
        }

        Collection tmp = new ArrayList();

        try {
            nEnum = ctx.search(initCtx, filter, sc);

            while (nEnum.hasMore()) {
                sr = (SearchResult) nEnum.next();
                tmp.add(sr);
            }
        } catch (SizeLimitExceededException e) {
            // ok - as should be
            LOG.log(Level.FINEST, e.getMessage(), e);
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }

        if (0 == tmp.size()) tmp = null;
        return tmp;
    }

    /**
     * Create a user.
     * Login is required
     *
     * @param ldapUser The user to create
     * @throws NamingException If the creation fails an exception is thrown
     */
    private void createUser(LDAPUser ldapUser) throws NamingException {

        Attributes attrs = new BasicAttributes(true);

        Attribute objClass = new BasicAttribute(OBJECT_CLASS);

        Attribute cn = new BasicAttribute(ATR_CN);
        Attribute givenName = new BasicAttribute(ATR_GIVENNAME);
        Attribute mail = new BasicAttribute(ATR_MAIL);
        Attribute surName = new BasicAttribute(ATR_SN);
        Attribute uid = new BasicAttribute(ATR_UID);
        Attribute pwd = new BasicAttribute(ATR_USER_PASSWORD);
        Attribute org = new BasicAttribute(ATR_O);
        Attribute empType = new BasicAttribute(ATR_EMPLOYEE_TYPE);
        Attribute empNumber = new BasicAttribute(ATR_EMPLOYEE_NUMBER);
        Attribute pager = new BasicAttribute(ATR_MOBILE);
        Attribute seeAlso = new BasicAttribute(ATR_PAGER);

        String ldapUserUid = ldapUser.getUid();

        LOG.log(Level.FINEST, "Attempt to create user:" + ldapUserUid);

        addToAttributes(attrs, objClass, INET_ORG_PERSON);

        addToAttributes(attrs, cn, ldapUser.getCn());
        addToAttributes(attrs, givenName, ldapUser.getGivenName());
        addToAttributes(attrs, mail, ldapUser.getEmail());
        addToAttributes(attrs, surName, ldapUser.getSureName());
        addToAttributes(attrs, uid, ldapUserUid);
        addToAttributes(attrs, pwd, ldapUser.getUserPassword());
        addToAttributes(attrs, org, ldapUser.getO());
        addToAttributes(attrs, empType, ldapUser.getEmployeeType());
        addToAttributes(attrs, empNumber, ldapUser.getEmployeeNumber());
        
        addToAttributes(attrs, pager, ldapUser.getMobile());
        addToAttributes(attrs, seeAlso, ldapUser.getPager());

        DirContext pCtx = ctx.createSubcontext(getFullUserContextString(ldapUserUid), attrs);

        String dn = pCtx != null ? pCtx.getNameInNamespace() : null;

        LOG.log(Level.FINEST, "User created: " + dn);

        ldapUser.setDn(dn);
    }

    private void addToAttributes(Attributes attrs, Attribute atr, String value) {
        if (value != null) {
            atr.add(value);
            attrs.put(atr);
        } else {
            LOG.log(Level.FINEST, "Attribute " + atr.getID() + " has no value");
        }
    }

    /**
     * Update a user.
     * Login is required
     *
     * @param ldapUser The user to update
     * @param oldUser  The existing user
     * @throws NamingException If the update fails an exception is thrown
     */
    private void updateUser(LDAPUser ldapUser, LDAPUser oldUser) throws NamingException {
        String cn = ldapUser.getCn();
        String givenName = ldapUser.getGivenName();
        String mail = ldapUser.getEmail();
        String surName = ldapUser.getSureName();
        String uid = ldapUser.getUid();
        String pwd = ldapUser.getUserPassword();
        String org = ldapUser.getO();
        String empType = ldapUser.getEmployeeType();
        String empNumber = ldapUser.getEmployeeNumber();
        String secretary = ldapUser.getMobile();
        String seeAlso = ldapUser.getPager();

        String o_cn = oldUser.getCn();
        String o_givenName = oldUser.getGivenName();
        String o_mail = oldUser.getEmail();
        String o_surName = oldUser.getSureName();
        String o_uid = oldUser.getUid();
        String o_pwd = oldUser.getUserPassword();
        String o_org = oldUser.getO();
        String o_empType = oldUser.getEmployeeType();
        String o_empNumber = oldUser.getEmployeeNumber();
        String o_secretary = oldUser.getMobile();
        String o_seeAlso = oldUser.getPager();

        List modAtrList = new ArrayList();

        addModifiedAttributeToList(ATR_CN, cn, o_cn, modAtrList);
        addModifiedAttributeToList(ATR_GIVENNAME, givenName, o_givenName, modAtrList);
        addModifiedAttributeToList(ATR_MAIL, mail, o_mail, modAtrList);
        addModifiedAttributeToList(ATR_SN, surName, o_surName, modAtrList);
        addModifiedAttributeToList(ATR_UID, uid, o_uid, modAtrList);
        addModifiedAttributeToList(ATR_USER_PASSWORD, pwd, o_pwd, modAtrList);
        addModifiedAttributeToList(ATR_O, org, o_org, modAtrList);
        addModifiedAttributeToList(ATR_EMPLOYEE_TYPE, empType, o_empType, modAtrList);
        addModifiedAttributeToList(ATR_EMPLOYEE_NUMBER, empNumber, o_empNumber, modAtrList);
        addModifiedAttributeToList(ATR_MOBILE, secretary, o_secretary, modAtrList);
        addModifiedAttributeToList(ATR_PAGER, seeAlso, o_seeAlso, modAtrList);

        if (modAtrList.size() > 0) {
            ModificationItem[] items = new ModificationItem[modAtrList.size()];
            Iterator itr = modAtrList.iterator();
            int i = 0;

            while (itr.hasNext()) {
                items[i++] = (ModificationItem) itr.next();
            }

            ctx.modifyAttributes(oldUser.getDn(), items);
        }
    }

    private void addModifiedAttributeToList(String attr, String s, String old, List list) {
        if (isAttributeChanged(s, old)) {
            list.add(new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(attr, s)));
        }
    }

    private boolean isAttributeChanged(String s, String old) {
        return ((s != null && !s.equals(old)) || (s == null && old != null));
    }

    /**
     * Delete a user.
     * Login is required
     *
     * @param ldapUser The user to delete
     * @throws NamingException If the deletion fails an exception is thrown
     */
    private void deleteUser(LDAPUser ldapUser) throws NamingException {
        ctx.destroySubcontext(getFullUserContextString(ldapUser.getUid()));
    }

    private String getFullUserContextString(String uid) {
        return ATR_UID + "=" + uid + ", " + INITIAL_USER_CTX;
    }

    /**
     * Login to LDAP
     *
     * @throws com.wmdata.util.ldap.exception.LDAPAuthenticationException
     *
     * @since 2006.06.21
     */
    private void doLogIn() throws LDAPAuthenticationException {
        if (!this.isLoggedIn) {
            try {
                LOG.log(Level.FINEST, "Login LDAP");
                doAuthenticate();
            } catch (LDAPAuthenticationException e) {
                LOG.log(Level.FINEST, e.getMessage(), e);
                throw e;
            }
        }
    }

    /**
     * Logout of LDAP
     *
     * @since 2006.06.21
     */
    private void doLogOut() {
        try {
            LOG.log(Level.FINEST, "Logout LDAP");
            ctx.close();
            this.isLoggedIn = false;
        } catch (Exception e) {
            LOG.log(Level.FINEST, e.getMessage(), e);
        }
    }

    /**
     * Authenticate against the LDAP server
     *
     * @throws com.wmdata.util.ldap.exception.LDAPAuthenticationException
     *
     * @since 2006.06.21
     */
    private void doAuthenticate() throws LDAPAuthenticationException {
        //Set to the environment
        Hashtable props = new Hashtable();
        props.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        props.put(Context.PROVIDER_URL, PROVIDER_URL);
        //props.put("java.naming.ldap.version", LDAP_VERSION);
        //props.put(Context.SECURITY_PROTOCOL ,securityProtocol);
        //props.put(Context.SECURITY_AUTHENTICATION,securityAuthentication);
        props.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL);
        props.put(Context.SECURITY_CREDENTIALS, SECURITY_CREDENTIALS);

        // Create the initial context
        try {
            this.ctx = new InitialDirContext(props);
        } catch (NamingException ne) {
            throw new LDAPAuthenticationException(ne);
        }

        this.isLoggedIn = true;
    }

    private String convertAttributeByteArrayToString(Attribute attr) {
        String ret = null;
        try {
            Object val = attr != null ? attr.get() : null;

            if (val != null && val.getClass().isArray() && Array.getLength(val) > 0) {
                ret = new String((byte[]) val, "UTF-8");
                LOG.log(Level.FINEST, "Attribute value converted from byte[] to String");
            }

        } catch (UnsupportedEncodingException e) {
            LOG.log(Level.SEVERE, "Failed to convert byte[] to String", e);
        } catch (NamingException e) {
            LOG.log(Level.SEVERE, "Failed to convert byte[] to String", e);
        }

        return ret;
    }

    private LDAPUser populateLDAPUser(SearchResult s) throws NamingException {
        return populateLDAPUser(s.getName() + ", " + INITIAL_USER_CTX, s.getAttributes());
    }

    private LDAPUser populateLDAPUser(String dn, Attributes atrs) throws NamingException {
        LDAPUser usr = new LDAPUser();
        usr.setDn(dn);
        usr.setCn(atrs.get(ATR_CN) != null ? (String) atrs.get(ATR_CN).get(0) : null);
        usr.setGivenName(atrs.get(ATR_GIVENNAME) != null ? (String) atrs.get(ATR_GIVENNAME).get(0) : null);
        usr.setSureName(atrs.get(ATR_SN) != null ? (String) atrs.get(ATR_SN).get(0) : null);
        usr.setEmail(atrs.get(ATR_MAIL) != null ? (String) atrs.get(ATR_MAIL).get(0) : null);
        usr.setUid(atrs.get(ATR_UID) != null ? (String) atrs.get(ATR_UID).get(0) : null);

        usr.setUserPassword(convertAttributeByteArrayToString(atrs.get(ATR_USER_PASSWORD) != null ? atrs.get(ATR_USER_PASSWORD) : null));

        usr.setO(atrs.get(ATR_O) != null ? (String) atrs.get(ATR_O).get(0) : null);
        usr.setEmployeeNumber(atrs.get(ATR_EMPLOYEE_NUMBER) != null ? (String) atrs.get(ATR_EMPLOYEE_NUMBER).get(0) : null);
        usr.setEmployeeType(atrs.get(ATR_EMPLOYEE_TYPE) != null ? (String) atrs.get(ATR_EMPLOYEE_TYPE).get(0) : null);
        
        usr.setMobile(atrs.get(ATR_MOBILE) != null ? (String) atrs.get(ATR_MOBILE).get(0) : null);
        usr.setPager(atrs.get(ATR_PAGER) != null ? (String) atrs.get(ATR_PAGER).get(0) : null);

        return usr;
    }
}