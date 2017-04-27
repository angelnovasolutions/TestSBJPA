package netgloo.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ADB_FileFields database table.
 * 
 */
@Embeddable
public class ADB_FileFieldPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int IDField;

	@Column(name="ValidationType")
	private String validationType;

	public ADB_FileFieldPK() {
	}
	public int getIDField() {
		return this.IDField;
	}
	public void setIDField(int IDField) {
		this.IDField = IDField;
	}
	public String getValidationType() {
		return this.validationType;
	}
	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ADB_FileFieldPK)) {
			return false;
		}
		ADB_FileFieldPK castOther = (ADB_FileFieldPK)other;
		return 
			(this.IDField == castOther.IDField)
			&& this.validationType.equals(castOther.validationType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.IDField;
		hash = hash * prime + this.validationType.hashCode();
		
		return hash;
	}
}