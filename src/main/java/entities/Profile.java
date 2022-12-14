package entities;

public class Profile {
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
     * Create a new Profile with location, gender, age, sexuality, hobbies, height, weight,
     * contactInformation, and selfDescription.
     *
     * @param location the location of this single Profile.
     * @param gender the gender of this single Profile.
     * @param age the age of this single Profile.
     * @param sexuality the sexuality of this single Profile.
     * @param hobbies the hobbies of this single Profile.
     * @param height the height of this single Profile.
     * @param weight the weight of this single Profile.
     * @param contactInformation the contactInformation of this single Profile.
     * @param selfDescription the selfDescription of this single Profile.
     */

    public Profile(String location, String gender, String age, String sexuality,
                   String hobbies, String height, String weight, String contactInformation,
                   String selfDescription) {
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


    // This empty constructor is for testing purpose.
    public Profile(){
        this.location = "";
        this.gender = "";
        this.age = "";
        this.sexuality = "";
        this.hobbies = "";
        this.height = "";
        this.weight = "";
        this.contactInformation = "";
        this.selfDescription = "";
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
}