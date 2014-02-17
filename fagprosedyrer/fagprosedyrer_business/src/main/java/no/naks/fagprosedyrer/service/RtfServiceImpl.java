package no.naks.skjemabank.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import no.naks.skjemabank.model.AnswerLine;
import no.naks.skjemabank.model.Question;
import no.naks.skjemabank.model.QuestionImpl;
import no.naks.skjemabank.model.Questionare;
import no.naks.skjemabank.service.QuestionareService;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.rtf.RtfWriter2;

public class RtfServiceImpl implements RtfService {
	private String imagePath;
	private QuestionareService questionareService;
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public void setQuestionareWebService(QuestionareService questionareService) {
		this.questionareService = questionareService;
	}
	
	public Document createRtfDocument(ServletOutputStream outputStream, Questionare actualQuestionare) {
	// todo: concider using services for this, not backingbeans
		return new Document();
	}
}
