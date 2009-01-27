package no.helsebiblioteket.admin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import no.helsebiblioteket.admin.domain.Unique;

public class ModifiedListHelper<T extends Unique> {
	public List<T> getDeleteList(List<T> changedList, List<T> originalList) {
		List<T> deleteList = new ArrayList<T>(originalList);
		deleteList = removeAll(deleteList, changedList);
		return deleteList;
	}
	
	public List<T> getInsertList(List<T> changedList, List<T> originalList) {
		List<T> insertList = new ArrayList<T>(changedList);
		insertList = removeAll(insertList, originalList);
		return insertList;
	}
	public List<T> getUpdateList(List<T> changedList, List<T> originalList) {
		List<T> insertList = getInsertList(changedList, originalList);
		List<T> updateList = new ArrayList<T>(changedList);
		updateList = removeAll(updateList, insertList);
		return updateList;
		// OLD:
		// If they are equal, why update?
//		return new ArrayList<T>();
	}
	public List<T> removeAll(List<T> from, List<T> remove) {
		// TODO: This must be rewritten to use Id.
		HashSet<Integer> ids = new HashSet<Integer>();
		for (T unique : remove) { ids.add(unique.getId()); }
		List<T> result = new ArrayList<T>();
		for (T unique : from) {
			if( ! ids.contains(unique)){
				result.add(unique);
			}
		}
		return result;
	}
}
