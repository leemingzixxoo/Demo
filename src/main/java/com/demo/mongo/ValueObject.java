package com.demo.mongo;

public class ValueObject {
	private String _id;
	private String value;
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "_id:" + this._id + ", value:" + this.value;
	}
}
