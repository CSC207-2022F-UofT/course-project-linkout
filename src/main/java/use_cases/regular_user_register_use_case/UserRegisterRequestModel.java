package use_cases.regular_user_register_use_case;

import entities.Profile;

public class UserRegisterRequestModel {

    private String AccountName;
    private String password;
    private String repeatPassword;

    private String location;

    private String gender;

    private String age;

    private String sexuality;

    private String hobbies;

    private String height;

    private String weight;

    private String contactInformation;

    private String selfDescription;

    /**
     * @param AccountName the accountname of user
     * @param password the password of user
     * @param repeatPassword the repeatPassword of user
     * @param location the location of user
     * @param gender the gender of user
     * @param age the age of user
     * @param sexuality the sexuality of user
     * @param hobbies the hobbies of user
     * @param height the height of user
     * @param weight the weight of user
     * @param contactInformation the contactInformation of user
     * @param selfDescription the selfDescription of user
     */
    public UserRegisterRequestModel(String AccountName, String password, String repeatPassword, String location,
                                    String gender, String age, String sexuality,
                                    String hobbies, String height, String weight, String contactInformation,
                                    String selfDescription) {
        this.AccountName = AccountName;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.location = location;
        this.gender = gender;
        this.age = age;
        this.sexuality = sexuality;
        this.hobbies = hobbies;
        this.height = height;
        this.weight = weight;
        this.contactInformation = contactInformation;
        this.selfDescription = selfDescription;
    }

    String getAccountName() {
        return AccountName;
    }

    void setName(String name) {
        this.AccountName = name;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public String getWeight() {
        return weight;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public String getHobbies() {
        return hobbies;
    }

    public String getGender() {
        return gender;
    }

    public String getLocation() {
        return location;
    }

    public String getSexuality() {
        return sexuality;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Profile getProfile(){return new Profile(location, gender, age, sexuality,
            hobbies, height, weight, contactInformation,
        selfDescription);}
}
