package no.helsebiblioteket.admin.sqlmapdao.helper;

import java.util.ArrayList;
import java.util.List;

public class ModifiedListHelper<T> {
	public ModifiedListHelper() {	
	}
	
	public List<T> getDeleteList(List<T> changedList, List<T> originalList) {
		List<T> deleteList = null;
		if (originalList != null) {
			deleteList = new ArrayList<T>(originalList);
			if (changedList != null) {
				deleteList.removeAll(changedList);
			}
		}
		return deleteList;
	}
	
	public List<T> getInsertAndUpdateList(List<T> changedList, List<T> originalList) {
		List<T> updateList = null;
		if (changedList != null) {
			updateList = new ArrayList<T>(changedList);
			if (originalList != null) {
				updateList.removeAll(originalList);
			}
		}
		return updateList;
	}
}