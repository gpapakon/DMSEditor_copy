package Matrices.Data;


import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class AbstractDSMDataProduct {
	private StringProperty title = new SimpleStringProperty("");
	private StringProperty projectName = new SimpleStringProperty("");
	private StringProperty customer = new SimpleStringProperty("");
	private StringProperty versionNumber = new SimpleStringProperty("");

	public StringProperty getTitle2() {
		return title;
	}

	public void setTitle2(StringProperty title) {
		this.title = title;
	}

	public StringProperty getProjectName2() {
		return projectName;
	}

	public void setProjectName2(StringProperty projectName) {
		this.projectName = projectName;
	}

	public StringProperty getCustomer2() {
		return customer;
	}

	public void setCustomer2(StringProperty customer) {
		this.customer = customer;
	}

	public StringProperty getVersionNumber2() {
		return versionNumber;
	}

	public void setVersionNumber2(StringProperty versionNumber) {
		this.versionNumber = versionNumber;
	}

	/**
	* Returns the title metadata information about the matrix
	* @return  title data assigned to the matrix
	*/
	public final String getTitle() {
		return title.getValue();
	}

	/**
	* Sets the title metadata information about the matrix. Puts changes on the stack, but does not set a checkpoint.
	* @param title  the new title data for the matrix
	*/
	public final void setTitle(String title, AbstractDSMData abstractDSMData) {
		String currentTitle = this.title.getValue();
		abstractDSMData.addChangeToStack(
				new MatrixChange(() -> this.title.set(title), () -> this.title.set(currentTitle), false));
	}

	/**
	* Returns the project name metadata information about the matrix
	* @return  project name data assigned to the matrix
	*/
	public final String getProjectName() {
		return projectName.getValue();
	}

	/**
	* Sets the project name metadata information about the matrix. Puts changes on the stack, but does not set a checkpoint.
	* @param projectName  the new project name data for the matrix
	*/
	public final void setProjectName(String projectName, AbstractDSMData abstractDSMData) {
		String currentName = this.projectName.getValue();
		abstractDSMData.addChangeToStack(new MatrixChange(() -> this.projectName.set(projectName),
				() -> this.projectName.set(currentName), false));
	}

	/**
	* Returns the customer metadata information about the matrix
	* @return  customer data assigned to the matrix
	*/
	public final String getCustomer() {
		return customer.getValue();
	}

	/**
	* Sets the customer metadata information about the matrix. Puts changes on the stack, but does not set a checkpoint.
	* @param customer  the new customer data for the matrix
	*/
	public final void setCustomer(String customer, AbstractDSMData abstractDSMData) {
		String currentCustomer = this.customer.getValue();
		abstractDSMData.addChangeToStack(
				new MatrixChange(() -> this.customer.set(customer), () -> this.customer.set(currentCustomer), false));
	}

	/**
	* Returns the version number metadata information about the matrix
	* @return  version number property assigned to the matrix
	*/
	public final String getVersionNumber() {
		return versionNumber.getValue();
	}

	/**
	* Sets the version number metadata information about the matrix. Puts changes on the stack, but does not set a checkpoint.
	* @param versionNumber  the new version number data for the matrix
	*/
	public final void setVersionNumber(String versionNumber, AbstractDSMData abstractDSMData) {
		String currentVersionNumber = this.versionNumber.getValue();
		abstractDSMData.addChangeToStack(new MatrixChange(() -> this.versionNumber.set(versionNumber),
				() -> this.versionNumber.set(currentVersionNumber), false));
	}
}