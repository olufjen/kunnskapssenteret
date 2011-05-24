package no.helsebiblioteket.evs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Import {
	private static final Pattern ARTICLE_PATTERN = Pattern.compile("<.*ArtRecordTbl id=\".+\" rowOrder=\"\\d+\">");
	private static final Pattern COMMENT_PATTERN = Pattern.compile("<.*ArticleCommentsTbl id=\".+\" rowOrder=\"\\d+\">");
	private static final Pattern DISC_AND_RATINGS_PATTERN = Pattern.compile("<.*DisciplineAndRatingsTbl id=\".+\" rowOrder=\"\\d+\">");
	private static final Pattern PATIENT_POPULATION_PATTERN = Pattern.compile("<.*ArticlePatientPopulationTbl id=\".+\" rowOrder=\"\\d+\">");
	
	public static boolean hasContent(String xmlContent) {
		return xmlContent != null && !xmlContent.contains("<NewDataSet><ZeroArticles id=\"ZeroArticles1\" rowOrder=\"0\"><Column1>ZeroArticles</Column1></ZeroArticles></NewDataSet>");
	}
	
	public static boolean hasArticles(String xmlContent) {
		Matcher matcher = ARTICLE_PATTERN.matcher(xmlContent);
		return matcher.find();
	}

	public static boolean hasComments(String xmlContent) {
		Matcher matcher = COMMENT_PATTERN.matcher(xmlContent);
		return matcher.find();
	}

	public static boolean hasDiscAndRatings(String xmlContent) {
		Matcher matcher = DISC_AND_RATINGS_PATTERN.matcher(xmlContent);
		return matcher.find();
	}

	public static boolean hasPatientPopulations(String xmlContent) {
		Matcher matcher = PATIENT_POPULATION_PATTERN.matcher(xmlContent);
		return matcher.find();
	}
}
