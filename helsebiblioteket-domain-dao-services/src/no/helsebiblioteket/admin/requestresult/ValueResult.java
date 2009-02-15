package no.helsebiblioteket.admin.requestresult;

public class ValueResult<T> extends SingleResult<T> {
	private T value;
	public ValueResult() { }
	public ValueResult(T value){
		this.value = value;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
}
