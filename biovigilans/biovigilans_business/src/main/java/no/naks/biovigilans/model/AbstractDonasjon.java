package no.naks.biovigilans.model;

import java.util.Map;

import no.naks.rammeverk.kildelag.model.AbstractModel;


/**
 * 
 */

public abstract class AbstractDonasjon extends AbstractModel implements Donasjon{

	private Long donasjonsId;
	/**
	 * M� beskrives
	 */
	private String donasjonssted;
	/**
	 * Ja nei, vet ikke
	 */
	private String komplisertvenepunksjon;
	/**
	 * Tappetype vil v�re en av f�lgende:
	 * Fullblods
	 */
	private String tappetype;
	/**
	 * Tappevariighet
	 */
	private String tappevarighet;
	/**
	 * M� beskrives
	 */
	private String lokalisasjonvenepunksjon;
	/**
	 * M�ltid innen 3 timer f�r tapping (Ja, nei Vet ikke)
	 */
	private String maltidfortapping;
	
	private int giveId;
	
	protected Map<String,String> donasjonsFields;
	protected String[] keys;
	
	
	public Long getDonasjonsId() {
		return donasjonsId;
	}
	public void setDonasjonsId(Long donasjonsId) {
		this.donasjonsId = donasjonsId;
	}
	
	public String getDonasjonssted() {
		return donasjonssted;
	}
	public void setDonasjonssted(String donasjonssted) {
		if(donasjonssted == null)
			donasjonssted = donasjonsFields.get(keys[0]);
		this.donasjonssted = donasjonssted;
	}
	public String getKomplisertvenepunksjon() {
		return komplisertvenepunksjon;
	}
	public void setKomplisertvenepunksjon(String komplisertvenepunksjon) {
		if(komplisertvenepunksjon==null){
			komplisertvenepunksjon = donasjonsFields.get(keys[2]);
		}
		this.komplisertvenepunksjon = komplisertvenepunksjon;
	}
	public String getTappetype() {
		return tappetype;
	}
	public void setTappetype(String tappetype) {
		if(tappetype==null){
			tappetype = donasjonsFields.get(keys[3]);
			if(tappetype.equalsIgnoreCase("fullblod")){
				tappetype = donasjonsFields.get(keys[4]);
			}else{
				if(donasjonsFields.get(keys[5]).equalsIgnoreCase("aferese-ja")){
					tappetype = donasjonsFields.get(keys[5]) + " " + donasjonsFields.get(keys[6]);
				}else{
					tappetype = donasjonsFields.get(keys[5]);
				}
			}
		}
		this.tappetype = tappetype;
	}
	public String getTappevarighet() {
		return tappevarighet;
	}
	public void setTappevarighet(String tappevarighet) {
		this.tappevarighet = tappevarighet;
	}
	public String getLokalisasjonvenepunksjon() {
		return lokalisasjonvenepunksjon;
	}
	public void setLokalisasjonvenepunksjon(String lokalisasjonvenepunksjon) {
		this.lokalisasjonvenepunksjon = lokalisasjonvenepunksjon;
	}
	public String getMaltidfortapping() {
		return maltidfortapping;
	}
	public void setMaltidfortapping(String maltidfortapping) {
		if(maltidfortapping==null)
			maltidfortapping = donasjonsFields.get(keys[1]);
		this.maltidfortapping = maltidfortapping;
	}
	public Map<String, String> getDonasjonsFields() {
		return donasjonsFields;
	}
	public void setDonasjonsFields(Map<String, String> donasjonsFields) {
		this.donasjonsFields = donasjonsFields;
	}
	public String[] getKeys() {
		return keys;
	}
	public void setKeys(String[] keys) {
		this.keys = keys;
	}
	public int getGiveId() {
		return giveId;
	}
	public void setGiveId(int giveId) {
		this.giveId = giveId;
	}
	
	
}