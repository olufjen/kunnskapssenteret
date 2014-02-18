package no.naks.fagprosedyrer.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;



import no.naks.fagprosedyrer.model.Fagprosedyre;

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
	
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	


	@Override
	public Document createRtfDocument(ServletOutputStream outputStream,
			Fagprosedyre fagprosedyre) {
		// TODO Auto-generated method stub
		return null;
	}
}
