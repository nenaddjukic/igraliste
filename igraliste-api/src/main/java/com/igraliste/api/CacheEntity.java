package com.igraliste.api;

import java.io.Serializable;

public class CacheEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8136558436585732058L;
	private String id;
	private String value;
	public CacheEntity(final String id,final  String value) {
		super();
		this.id = id;
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheEntity other = (CacheEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CacheEntity [id=" + id + ", value=" + value + "]";
	}
}
