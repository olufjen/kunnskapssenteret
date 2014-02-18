package no.naks.fagprosedyrer.service;

import javax.servlet.ServletOutputStream;

import no.naks.fagprosedyrer.model.Fagprosedyre;


import com.lowagie.text.Document;

public interface RtfService {
	public Document createRtfDocument(ServletOutputStream outputStream, Fagprosedyre fagprosedyre);
}
