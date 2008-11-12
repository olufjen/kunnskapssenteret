package no.helsebiblioteket.domain;

import java.util.ArrayList;

public class User {
	protected Integer id = null;
	protected String username = null;
	protected String password = null;
	protected Organization organizaion = null;
	protected ArrayList<Role> roleList = null;
	protected ArrayList<Access> accessList = null;
}
