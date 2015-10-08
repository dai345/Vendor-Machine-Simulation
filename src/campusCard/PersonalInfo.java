package campusCard;

public class PersonalInfo {

	String fName, lName;

	public PersonalInfo() {
		this("No", "Name");
	}

	public PersonalInfo(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
	}

	public String getFName() {
		return this.fName;
	}

	public String getLName() {
		return this.lName;
	}
	public String getName() {
		return this.getFName() + " " + this.getLName();
	}

}
