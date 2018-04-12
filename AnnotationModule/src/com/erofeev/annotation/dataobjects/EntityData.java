package com.erofeev.annotation.dataobjects;

import java.util.ArrayList;
import java.util.List;

public class EntityData {
	private String fileName;
	private String separator;
	private int id;
	private boolean statusRead = false;
	private List<FieldData> fields = new ArrayList<FieldData>();

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addField(FieldData field) {
		fields.add(field);
	}
	
	public void addFields(List<FieldData> fields){
		this.fields.addAll(fields);
	}

	public List<FieldData> getFields() {
		return fields;
	}

	public void setFields(List<FieldData> fields) {
		this.fields = fields;
	}
	
	

	public boolean isStatusRead() {
		return statusRead;
	}

	public void setStatusRead(boolean statusRead) {
		this.statusRead = statusRead;
	}

	@Override
	public String toString() {
		return "EntityData [fileName=" + fileName + ", separator=" + separator + ", id=" + id + ", fields=" + fields
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + id;
		result = prime * result + ((separator == null) ? 0 : separator.hashCode());
		result = prime * result + (statusRead ? 1231 : 1237);
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
		EntityData other = (EntityData) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (id != other.id)
			return false;
		if (separator == null) {
			if (other.separator != null)
				return false;
		} else if (!separator.equals(other.separator))
			return false;
		if (statusRead != other.statusRead)
			return false;
		return true;
	}
	
	
	
	

}
