package no.helsebiblioteket.admin.request;

import java.util.List;

public class PageResult<T> {
	public List<T> result;
	public int total;
	public int from;
}
