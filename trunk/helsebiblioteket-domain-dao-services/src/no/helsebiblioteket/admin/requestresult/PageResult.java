package no.helsebiblioteket.admin.requestresult;

import java.util.List;

public class PageResult<T> {
	public List<T> result;
	public int total;
	public int from;
}
