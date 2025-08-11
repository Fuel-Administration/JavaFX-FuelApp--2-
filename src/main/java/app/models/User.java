package app.models;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String idNumber;
    private String username;
    private String password;
    private String carMake;
    private String carModel;
    private String carReg;

    public User() {}

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getCarMake() { return carMake; }
    public void setCarMake(String carMake) { this.carMake = carMake; }
    public String getCarModel() { return carModel; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public String getCarReg() { return carReg; }
    public void setCarReg(String carReg) { this.carReg = carReg; }
}
