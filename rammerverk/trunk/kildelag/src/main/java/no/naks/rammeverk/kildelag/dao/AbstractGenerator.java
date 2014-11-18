package no.naks.rammeverk.kildelag.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.Properties;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author spledger
 * @version 3.00.00
 * 
 * @description This class generates random passwords based on user-specified preferences.
 */
public abstract class AbstractGenerator implements Serializable {

	protected double nTimesDbl,  nCharsDbl;
	protected int progress;
	protected String format,  alphaChars,  numerChars,  symblChars,  explChars,  incChars,  excChars;
	public String generatedPwords;
	public String[] passwords;
	protected boolean formatted;
	public boolean readable;
	public int readableMinSize;
	public int readableMaxSize;
	public BufferedReader dictionaryIO;
	public ArrayList wordList;
	public boolean running;
	public int statusInt = 0;
	public String statusStr = "";
	public boolean allowDuplicatePasswords = false;
	public String systemLineSeparator = java.lang.System.getProperty("line.separator");
	public static String alphaCharSet = "abcdefghijklmnopqrstuvwxyz";
	public static String numerCharSet = "0123456789";
	public static String easyReadAlphaCharSet = "abcdefghjkmnpqrstuvwxyz";
	public static String easyReadNumerCharSet = "23456789";
	public static String symblCharSet = "!@#$%^&*()_-+={}[]|\\;:\'\"<>.?/~`";
	public static String genVersion = "4.00.00";
	public Properties genOptions = new Properties();
	private long startTime;
	private long stopTime;
	private int pwordCount;
	private boolean dictionaryInitd=false;

	/** Creates a new instance of generator with the default settings. */
	public AbstractGenerator() {
		this.resetToDefaults();
	}
	/** Resets the generator's preferences to the default settings. */
	public void resetToDefaults() {
		formatted = false;
		readable=false;
		readableMinSize=0;
		readableMaxSize=0;
		nTimesDbl = 1;
		nCharsDbl = 8;
		format = "";
		alphaChars = "";
		numerChars = "";
		symblChars = "";
		explChars = "";
		incChars = "";
		excChars = "";
		generatedPwords = "";
		progress = 0;

	}
	
	/** Puts the generator's options into a Properties object and returns it.
	 * @return Returns a Properties object containing the generator's options.
	 */
	public Properties writeAllProperties() {
		genOptions.setProperty("gen.version", AbstractGenerator.genVersion);
		genOptions.setProperty("gen.nTimesStr", "" + nTimesDbl);
		genOptions.setProperty("gen.nCharsStr", "" + nCharsDbl);
		genOptions.setProperty("gen.formatted", "" + this.formatted);
		genOptions.setProperty("gen.format", format);
		genOptions.setProperty("gen.alphaChars", alphaChars);
		genOptions.setProperty("gen.numerChars", numerChars);
		genOptions.setProperty("gen.symblChars", symblChars);
		genOptions.setProperty("gen.explChars", explChars);
		genOptions.setProperty("gen.incChars", incChars);
		genOptions.setProperty("gen.excChars", excChars);
		genOptions.setProperty("gen.generatedPwords", generatedPwords);
		genOptions.setProperty("gen.allowDuplicatePasswords", "" + this.allowDuplicatePasswords);
		genOptions.setProperty("gen.readable", ""+readable);
		genOptions.setProperty("gen.readableMaxSize",""+readableMaxSize);
		genOptions.setProperty("gen.readableMinSize",""+readableMinSize);
		return this.genOptions;
	}
	
	/** Specifies the generator's preferences based on a given Properties object. 
	 * @param newOpts The new Propertiles object to read.
	 */
	public void readNewProperties(Properties newOpts) {
		this.nTimesDbl = Double.parseDouble(genOptions.getProperty("gen.nTimesStr", "" + nTimesDbl));
		this.nCharsDbl = Double.parseDouble(genOptions.getProperty("gen.nCharsStr", "" + nCharsDbl));
		this.formatted = Boolean.parseBoolean(genOptions.getProperty("gen.formatted", "" + this.formatted));
		this.format = genOptions.getProperty("gen.format", format);
		this.alphaChars = genOptions.getProperty("gen.alphaChars", alphaChars);
		this.numerChars = genOptions.getProperty("gen.numerChars", numerChars);
		this.symblChars = genOptions.getProperty("gen.symblChars", symblChars);
		this.explChars = genOptions.getProperty("gen.explChars", explChars);
		this.incChars = genOptions.getProperty("gen.incChars", incChars);
		this.excChars = genOptions.getProperty("gen.excChars", excChars);
		this.generatedPwords = genOptions.getProperty("gen.generatedPwords", generatedPwords);
		this.allowDuplicatePasswords = Boolean.parseBoolean(genOptions.getProperty("gen.allowDuplicatePasswords", "" + this.allowDuplicatePasswords));
		this.readable=Boolean.parseBoolean(genOptions.getProperty("gen.readable",""+this.readable));
		this.readableMaxSize=Integer.parseInt(genOptions.getProperty("gen.readableMaxSize",""+readableMaxSize));
		this.readableMinSize=Integer.parseInt(genOptions.getProperty("gen.readableMinSize",""+readableMinSize));
	}

	/**This sets all of the Generator's options in one command when a Properties object is not readily available.
	 * 
	 * 
	 * @param L_nTimesInt The number of passwords to generate.
	 * @param L_nCharsInt The length of each password.
	 * @param L_format The formatting string to use.  Leave blank if you don't want to generate formatted passwords.
	 * @param L_alphaChars If not formatted, the explicit Characters to use.  If formatted, the alpha Characters to use.
	 * @param L_numerChars If not formatted, the include Characters to use.  If formatted, the numeric Characters to use.
	 * @param L_symblChars If not formatted, the exclude Characters to use.  If formatted, the symbol Characters to use.
	 */
	public void setOptions(double L_nTimesInt, int L_nCharsInt, String L_format, String L_alphaChars, String L_numerChars, String L_symblChars) {
		this.nTimesDbl = L_nTimesInt;
		this.nCharsDbl = L_nCharsInt;
		this.format = L_format;
		this.alphaChars = L_alphaChars;
		this.numerChars = L_numerChars;
		this.symblChars = L_symblChars;
		this.explChars = L_alphaChars;
		this.incChars = L_numerChars;
		this.excChars = L_symblChars;
	}
	/**This sets all of the Generator's options in one command when a Properties object is not readily available.
	 *
	 *
	 * @param L_nTimesInt The number of passwords to generate.
	 * @param L_nCharsInt The length of each password.
	 * @param L_format The formatting string to use.  Leave blank if you don't want to generate formatted passwords.
	 * @param L_alphaChars If not formatted, the explicit Characters to use.  If formatted, the alpha Characters to use.
	 * @param L_numerChars If not formatted, the include Characters to use.  If formatted, the numeric Characters to use.
	 * @param L_symblChars If not formatted, the exclude Characters to use.  If formatted, the symbol Characters to use.
	 */
	public void setOptions(double L_nTimesInt, int L_nCharsInt, String L_format, String L_alphaChars, String L_numerChars, String L_symblChars, boolean pronouncable, int minWordLen, int maxWordLen) {
		setOptions( L_nTimesInt,  L_nCharsInt,  L_format,  L_alphaChars,  L_numerChars,  L_symblChars);
		this.readable=pronouncable;
		this.readableMinSize=minWordLen;
		this.readableMaxSize=maxWordLen;

	}


	/**
	 * Generates passwords using a format string.
	 * @param L_nTimesInt Number of passwords to generate.
	 * @param L_format Formatting string to use.
	 * @param L_alphaChars Alpha Characters to use.
	 * @param L_numerChars Numeric Characters to use.
	 * @param L_symblChars Symbol Characters to use.
	 * @return Returns a string of all of the passwords separated by newline (\n) characters.
	 */
	public String generatePasswords(int L_nTimesInt, String L_format, String L_alphaChars, String L_numerChars, String L_symblChars, boolean pronounceable, int minWordLen, int MaxWordLen) {
		this.setOptions(L_nTimesInt, 0, L_format, L_alphaChars, L_numerChars, L_symblChars, pronounceable, minWordLen, MaxWordLen);
		this.formatted = true;
		return this.generatePasswords();
	}
	
	/**
	 * Generates passwords without using a format string.
	 * @param L_nTimesInt Number of passwords to generate.
	 * @param L_nCharsInt Number of characters per password.
	 * @param L_explChars Explicit characters to use.
	 * @param L_incChars Include characters to use.
	 * @param L_excChars Exclude characters to use.
	 * @return Returns a string of all of the passwords separated by newline (\n) characters.
	 */
	public String generatePasswords(long L_nTimesInt, int L_nCharsInt, String L_explChars, String L_incChars, String L_excChars, boolean pronounceable, int minWordLen, int MaxWordLen) {
		this.setOptions(L_nTimesInt, L_nCharsInt, "", L_explChars, L_incChars, L_excChars, pronounceable, minWordLen, MaxWordLen);
		this.formatted = false;
		return this.generatePasswords();
	}
	
	/**
	 * Generates passwords using the generator's current settings.
	 * @return Returns a string of all of the passwords separated by newline (\n) characters.
	 */
	public String generatePasswords() {
		this.running = true;
		this.statusInt = -1;
		Date now = new Date();
		Random randInt = new Random(9123847 + now.getTime());
		
		if(this.readable && this.readableMaxSize>this.nCharsDbl){
			this.readableMaxSize=(int) this.nCharsDbl;
		}
		if(this.readable && this.readableMinSize<0){
			this.readableMinSize=(int) this.nCharsDbl/2;
			if(this.readableMinSize>this.readableMaxSize){
				this.readableMinSize=this.readableMaxSize;
			}
		}
		
		double totalPossiblePwords = 1;
		String totalChars = alphaChars + numerChars + symblChars;

		if (this.formatted) {
			for (int j = 0; j < format.length(); j++) {
				if (format.charAt(j) == "\\".charAt(0)) {
					j++;
				} else if (format.charAt(j) == "^".charAt(0)) {
					readable=true;
				} else if (format.charAt(j) == "#".charAt(0)) {
					totalPossiblePwords = totalPossiblePwords * numerChars.length();
				} else if (format.charAt(j) == "@".charAt(0)) {
					totalPossiblePwords = totalPossiblePwords * alphaChars.length();
				} else if (format.charAt(j) == "%".charAt(0)) {
					totalPossiblePwords = totalPossiblePwords * symblChars.length();
				} else if (format.charAt(j) == "*".charAt(0)) {
					totalPossiblePwords = totalPossiblePwords * totalChars.length();
				}
			}
		} else {
			String preUseChars = explChars + incChars;
			String useChars = "";
			for (int i = 0; i < preUseChars.length(); i++) {
				if (!excChars.contains(preUseChars.charAt(i) + "")) {
					useChars += preUseChars.charAt(i);
				}
			}
			totalPossiblePwords = java.lang.Math.pow(useChars.length(), this.nCharsDbl);


		}
		if(readable){
			try {
				this.loadDictionary();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		if ((nTimesDbl >= totalPossiblePwords) && (!this.allowDuplicatePasswords)) {
			
			this.setStatus("NOT POSSIBLE.  Allowing Duplicate Passwords.");
	//		this.onProgressUpdate();
			this.allowDuplicatePasswords = true;

		}
		this.generatedPwords = "";
		this.passwords = new String[(int)nTimesDbl];
		this.setStatus("Generating passwords...");
		pwordCount=0;
		this.statusInt=0;
		if (((this.formatted) || (this.nCharsDbl == 0)) && (!this.format.isEmpty())) {
			nCharsDbl = format.length();

			this.statusInt = 0;
			//String preUseChars = explChars + incChars;
			
			for (int i = 0; (i < nTimesDbl); i++) {
				int thisone = i + 1;
				this.statusInt = (java.lang.Math.round(java.lang.Math.round((thisone / this.nTimesDbl) * 100)));
				this.setStatus("Generating Passwords... (" + i + "/" + this.nTimesDbl + ")");
	//			this.onProgressUpdate();
				String thisPword = "";

				do {
					// build the password, character by character.
					for (int j = 0; (j < format.length()); j++) {

						randInt.setSeed(java.lang.Math.round(i * nCharsDbl * j + now.getTime() + randInt.nextInt()));
						if (format.charAt(j) == "\\".charAt(0)) {
							j++;
							thisPword += format.charAt(j);
						} else if (format.charAt(j) == "^".charAt(0)) {
							
							try{
								thisPword=thisPword.toLowerCase();
								int specialSize=this.readableMinSize+randInt.nextInt(this.readableMaxSize-this.readableMinSize);
								String word=getWordOfLength(randInt.nextInt(wordList.size()),specialSize);

								String thisChar=""+word.charAt(0);
								thisPword+=thisChar.toUpperCase();
								thisPword+=word.substring(1).toLowerCase();
								//j=thisPword.length()-1;
							}catch (Exception e){
								e.printStackTrace();
							}

						} else if (format.charAt(j) == "#".charAt(0)) {

							thisPword += numerChars.charAt(randInt.nextInt(numerChars.length()));

						} else if (format.charAt(j) == "@".charAt(0)) {
							thisPword += alphaChars.charAt(randInt.nextInt(alphaChars.length()));
						} else if (format.charAt(j) == "%".charAt(0)) {
							thisPword += symblChars.charAt(randInt.nextInt(symblChars.length()));
						} else if (format.charAt(j) == "*".charAt(0)) {
							thisPword += totalChars.charAt(randInt.nextInt(totalChars.length()));
						} else {
							thisPword += format.charAt(j);
						}
					}
				} while ((generatedPwords.contains(thisPword + "\n")) && (!this.allowDuplicatePasswords));
				generatedPwords += thisPword + "\n";
				this.passwords[i] = thisPword;
				pwordCount++;
	//			this.onProgressUpdate();
			}
		} else {
			// Not formatted.
			String preUseChars = explChars + incChars;
			String useChars = "";
			for (int i = 0; i < preUseChars.length(); i++) {
				if (!excChars.contains(preUseChars.charAt(i) + "")) {
					useChars += preUseChars.charAt(i);
				}
			}
			this.statusInt = 0;
			
			for (int i = 0; (i < nTimesDbl) && (this.running); i++) {
				
				int thisone = i + 1;
				this.statusInt = (java.lang.Math.round(java.lang.Math.round((thisone / this.nTimesDbl) * 100)));
				this.setStatus("Generating Passwords... (" + i + "/" + this.nTimesDbl + ")");
	//			this.onProgressUpdate();
				
				String thisPword = "";
				do {
					thisPword = "";
					int specialPoint=0;
					int specialSize=0;
					if(this.readable){
						//int smMaxOffset=(int)nCharsDbl - this.readableMaxSize;
						int lgMaxOffset=(int)nCharsDbl - this.readableMinSize;
						specialPoint=randInt.nextInt(lgMaxOffset);
						specialSize=readableMinSize;
						if(readableMinSize<readableMaxSize){
							specialSize+=randInt.nextInt(readableMaxSize-readableMinSize);
						}
					}
					for (int j = 0; (j < nCharsDbl) && (this.running); j++) {
						if(readable && j==specialPoint){
							thisPword=thisPword.toLowerCase();
							String word=getWordOfLength(randInt.nextInt(wordList.size()),specialSize);
							
							String thisChar=""+word.charAt(0);
							thisPword+=thisChar.toUpperCase();
							thisPword+=word.substring(1).toLowerCase();



							j=thisPword.length()-1;

						}
						randInt.setSeed(java.lang.Math.round(i * nCharsDbl * j + now.getTime() + randInt.nextInt()));
						thisPword += useChars.charAt(randInt.nextInt(useChars.length()));
					}
				} while ((generatedPwords.contains(thisPword + "\n")) && (!this.allowDuplicatePasswords) && (this.running));

				generatedPwords += thisPword + "\n";
				
				pwordCount++;
				this.passwords[pwordCount-1] = thisPword;
		//		this.onProgressUpdate();
			}
		}
		this.stopTime = new java.util.Date().getTime();

		String totalTimeStr = this.getGenTime();


		if (!this.running) {
			this.setStatus("Password Generation Aborted!  Took " + totalTimeStr + ", but only generated "+ pwordCount +" passwords.");
		} else {
			this.setStatus("Done Generating Passwords!  Took " + totalTimeStr + ".");
		}
		

		this.running = false;
	//	this.onProgressUpdate();
		return generatedPwords;
	}

	public String getWordOfLength(int offset,int size){
		String word="";
		boolean found=false;
		int count=0;
		do{
			word=(String) wordList.get(offset+count);
			if(word.length()==size){
				found=true;
			} else {
				count++;
				if(offset+count>=wordList.size()){
					count=offset-wordList.size();
				}
			}
		}while(!found);
		return word;
	}

	public void loadDictionary() throws IOException{
		if(!this.dictionaryInitd){
			File thisDir = new File("");
			String filename="dictionary.txt";
			thisDir=thisDir.getAbsoluteFile();
			if(!thisDir.isDirectory()){
				thisDir=thisDir.getParentFile();
			}
			int parentsLeft=5;
			File dictFile;

			do{
				dictFile=checkDirForFile(thisDir,filename);
				if(dictFile==null){
					parentsLeft--;
					thisDir=thisDir.getParentFile();
				} else {
					parentsLeft=-1;
				}
			}while(parentsLeft>0);
			if(dictFile!=null){

				this.dictionaryIO=new BufferedReader(new FileReader(dictFile.toURI().toURL().getPath()));
				this.setStatus("Initializing the Dictionary.");
		//		this.onProgressUpdate();
				String thisWord="";
				wordList=new ArrayList();
				while((thisWord=dictionaryIO.readLine())!=null){
					if((thisWord.length()<=readableMaxSize)&&(thisWord.length()>=readableMinSize)){
						wordList.add(thisWord);
						System.out.println("Dictionary added "+thisWord);
					}

				}

				dictionaryIO.close();
			}
			this.dictionaryInitd=true;
		}

	}

	public void setStatus(String newStat){
		this.statusStr=newStat;
		System.out.println(newStat);
	//	this.onProgressUpdate();

	}



	private File checkDirForFile(File dir,String filename){

		File[] fileList=dir.listFiles();
		for(int i=0;i<fileList.length;i++){
			if(fileList[i].isDirectory()){
				File retFile=null;
				retFile=checkDirForFile(fileList[i],filename);
				if(retFile!=null){
					return retFile;
				}
			}
			if(fileList[i].getName().compareToIgnoreCase(filename)==0){
				this.setStatus("Found requested file at "+fileList[i].getAbsolutePath()+".");

				return fileList[i];
			}

		}
		return null;
	}


	/**
	 * Stops generating the passwords.
	 */
	public void stopGenerating() {
		this.running = false;
	//	this.onProgressUpdate();
	//	this.onComplete();
	}
	/**
	 * Sets whether or not the generator should generate formatted passwords or not.
	 * @param newVal True if it should generate formatted passwords, false if it should not.
	 * @return Returns a boolean value of whether or not setting the value was successful.
	 */
	public boolean setFormatted(boolean newVal) {
		boolean retVal = false;
		this.formatted = newVal;
		if (this.formatted == newVal) {
			retVal = true;
		} else {
			retVal = false;
		}
		return retVal;
	}
	
	/**
	 * Gets the generated passwords.
	 * @return Returns the generated passwords as a String separated by newline (\n) characters.
	 */
	public String getGeneratedPasswords() {
		return this.generatedPwords;
	}
	public String[] getGeneratedPasswordsArray(){
	    return this.passwords;
	}
	
	public String getLastGeneratedPassword() throws Exception{
	    
	    return this.passwords[this.pwordCount-1];
	}
	/**
	 * Runs the password generator -- used by Threading.
	 */
/*	@Override
	public void run() {
		this.startTime = new java.util.Date().getTime();
		generatePasswords();
	//	this.onComplete();
	}
*/
	/**
	 * Calculates the amount of time it took for the generator to run.
	 * @return Returns a human-readable string of how long it took to generate the passwords.
	 */
	public String getGenTime() {
		long totalMs = this.stopTime - this.startTime;
		String retStr = "";
                
                int days,hours,minutes,seconds;
                days= (int) (totalMs / 86400000);
                long remainingMs=totalMs-(days*86400000);
                hours=(int) (remainingMs/3600000);
                remainingMs=remainingMs-(hours*3600000);
                minutes=(int) (remainingMs/60000);
                remainingMs=remainingMs-(minutes*60000);
                seconds=(int) (remainingMs/1000);
                remainingMs=remainingMs-(seconds*1000);
                if(days>0){
                    retStr+=days+" days, ";
                }
                if(hours>0){
                    retStr+=hours+" hours, ";
                }
                if(minutes>0){
                    retStr+=minutes+" minutes, ";
                }
                if(seconds>0){
                    retStr+=seconds+ " seconds, ";
                }
                retStr += remainingMs+" milliseconds";
		
		return retStr;
	}

	
}

