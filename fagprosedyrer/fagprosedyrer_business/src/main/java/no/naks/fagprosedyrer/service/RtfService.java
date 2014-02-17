package no.naks.skjemabank.service;

import javax.servlet.ServletOutputStream;

import no.naks.skjemabank.model.Questionare;

import com.lowagie.text.Document;

public interface RtfService {
	public Document createRtfDocument(ServletOutputStream outputStream, Questionare actualQuestionare);
}
