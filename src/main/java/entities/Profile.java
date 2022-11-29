package entities;

public class Profile {
    private String location;
    private String gender;
    private int age;
    private String sexuality;
    private String hobbies;
    private int height;
    private int weight;
    private int contactInformation;
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

    public Profile(String location, String gender, int age, String sexuality,
                   String hobbies, int height, int weight, int contactInformation,
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

    public int getContactInformation() {
        return contactInformation;
    }
    public int getWeight() {
        return weight;
    }
    public int getAge() {
        return age;
    }
    public int getHeight() {
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

    public void setAge(int age) {
        this.age = age;
    }
    public void setContactInformation(int contactInformation) {
        this.contactInformation = contactInformation;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setHeight(int height) {
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
    public void setWeight(int weight) {
        this.weight = weight;
    }
}