package netgloo.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ADB_FileLines database table.
 * 
 */
@Entity
@Table(name="ADB_FileLines")
@NamedQuery(name="ADB_FileLine.findAll", query="SELECT a FROM ADB_FileLine a")
public class ADB_FileLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IDLineType;

	@Column(name="EndIdentification")
	private int endIdentification;

	@Column(name="EndLine")
	private int endLine;

	@Column(name="IdentificationValue")
	private String identificationValue;

	@Column(name="LineType")
	private String lineType;

	@Column(name="StartIdentification")
	private int startIdentification;

	@Column(name="StartLine")
	private int startLine;

	@Column(name="IdentificationLine")
	private String IdentificationLine;
	
	//bi-directional many-to-one association to ADB_FileField
	@OneToMany(mappedBy="adbFileLine")
	private List<ADB_FileField> adbFileFields;

	//bi-directional many-to-one association to ADB_FileDefinition
	@ManyToOne
	@JoinColumn(name="IDFileType")
	private ADB_FileDefinition adbFileDefinition;

	public ADB_FileLine() {
	}

	public int getIDLineType() {
		return this.IDLineType;
	}

	public void setIDLineType(int IDLineType) {
		this.IDLineType = IDLineType;
	}

	public int getEndIdentification() {
		return this.endIdentification;
	}

	public void setEndIdentification(int endIdentification) {
		this.endIdentification = endIdentification;
	}

	public int getEndLine() {
		return this.endLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public String getIdentificationValue() {
		return this.identificationValue;
	}

	public void setIdentificationValue(String identificationValue) {
		this.identificationValue = identificationValue;
	}

	public String getLineType() {
		return this.lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public int getStartIdentification() {
		return this.startIdentification;
	}

	public void setStartIdentification(int startIdentification) {
		this.startIdentification = startIdentification;
	}

	public int getStartLine() {
		return this.startLine;
	}

	public void setStartLine(int startLine) {
		this.startLine = startLine;
	}

	public List<ADB_FileField> getAdbFileFields() {
		return this.adbFileFields;
	}

	public void setAdbFileFields(List<ADB_FileField> adbFileFields) {
		this.adbFileFields = adbFileFields;
	}

	public ADB_FileField addAdbFileField(ADB_FileField adbFileField) {
		getAdbFileFields().add(adbFileField);
		adbFileField.setAdbFileLine(this);

		return adbFileField;
	}

	public ADB_FileField removeAdbFileField(ADB_FileField adbFileField) {
		getAdbFileFields().remove(adbFileField);
		adbFileField.setAdbFileLine(null);

		return adbFileField;
	}

	public ADB_FileDefinition getAdbFileDefinition() {
		return this.adbFileDefinition;
	}

	public void setAdbFileDefinition(ADB_FileDefinition adbFileDefinition) {
		this.adbFileDefinition = adbFileDefinition;
	}

	public String getIdentificationLine() {
		return IdentificationLine;
	}

	public void setIdentificationLine(String identificationLine) {
		IdentificationLine = identificationLine;
	}

}