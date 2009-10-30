package no.helsebiblioteket.admin.sqlmapdao.input;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UrlSearch {
    private List keywordList = new ArrayList();
    
    public UrlSearch(String keywords) {
    	StringTokenizer splitter = new StringTokenizer(keywords, " ", false);
    	while (splitter.hasMoreTokens()) {
    		keywordList.add(splitter.nextToken());
        }
    }

    public List getKeywordList() {
      return keywordList;
    }

}
