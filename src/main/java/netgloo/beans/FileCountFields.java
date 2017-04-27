package netgloo.beans;

import java.io.Serializable;
import java.util.List;

import netgloo.models.ADB_FileField;

public class FileCountFields implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ADB_FileField> adb_FileField;

	public List<ADB_FileField> getAdb_FileField() {
		return adb_FileField;
	}

	public void setAdb_FileField(List<ADB_FileField> adb_FileField) {
		this.adb_FileField = adb_FileField;
	}

}
