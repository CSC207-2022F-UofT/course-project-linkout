package use_cases.regular_user_register_use_case;

import java.time.LocalDateTime;

public class UserRegisterDsRequestModel {

    private final String name;
    private String password;

    private String location;

    private String gender;

    private String age;

    private String sexuality;

    private String hobbies;

    private String height;

    private String weight;

    private String contactInformation;

    private String selfDescription;
    private final LocalDateTime creationTime;
    private int restrictionDuration;
    private float restrictionStartTime; // May be long

    public UserRegisterDsRequestModel(String name, String password, String location,
                                      String gender, String age, String sexuality, String hobbies,
                                      String height, String weight, String contactInformation,
                                      String selfDescription, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.contactInformation = contactInformation;
        this.location = location;
        this.height = height;
        this.sexuality = sexuality;
        this.hobbies = hobbies;
        this.weight = weight;
        this.selfDescription = selfDescription;
        this.creationTime = creationTime;
        this.restrictionDuration = 0;
        this.restrictionStartTime = 0;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }

    public String getLocation() {
        return location;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSexuality() {
        return sexuality;
    }

    public String getGender() {
        return gender;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getSelfDescription() {
        return selfDescription;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public int getRestrictionDuration() { return restrictionDuration; }

    public void setRestrictionDuration(int restrictionDuration) { this.restrictionDuration = restrictionDuration; }

    public float getRestrictionStartTime() { return restrictionStartTime; }

    public void setRestrictionStartTime(float restrictionStartTime) { this.restrictionStartTime = restrictionStartTime; }
}