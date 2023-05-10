package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Employee;
import model.Model;
import model.UserProfile;

public class LoginViewModel implements  ViewModel{

    private StringProperty passwordProperty;
    private StringProperty workingNumberProperty;
    private StringProperty errorProperty;
    private Model model;

    public LoginViewModel(Model model) {
        this.passwordProperty = new SimpleStringProperty("");
        this.workingNumberProperty = new SimpleStringProperty("");
        this.errorProperty = new SimpleStringProperty("");
        this.model = model;
    }

    public boolean login(){
        Employee employee;
        UserProfile userProfile;
        try {
            userProfile = new UserProfile(Integer.parseInt(workingNumberProperty.get()), passwordProperty.get());
            errorProperty.set("");
        }
        catch (Exception e){
            errorProperty.set("Invalid input");
            return false;
        }


        try {
            employee = model.login(userProfile);
            errorProperty.set("");
        }
        catch (Exception e){
            e.printStackTrace();
            errorProperty.set("Server is not working");
            return false;
        }

if(employee == null){
            errorProperty.set("Wrong working number or password");
            return false;
        }
        else{
            errorProperty.set("");
            model.setUser(employee);
            return true;
        }

    }


    public String getPasswordProperty() {
        return passwordProperty.get();
    }

    public StringProperty passwordPropertyProperty() {
        return passwordProperty;
    }

    public String getWorkingNumberProperty() {
        return workingNumberProperty.get();
    }

    public StringProperty workingNumberPropertyProperty() {
        return workingNumberProperty;
    }

    public String getErrorProperty() {
        return errorProperty.get();
    }

    public StringProperty errorPropertyProperty() {
        return errorProperty;
    }


}