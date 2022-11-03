package entities;

public class Profile {

    private String location;
    private String gender;
    private String age;
    private String sexuality;
    private String hobbies;
    private int height;
    private int weight;
    private int contactInformation;
    private String selfDescription;

    public Profile(String location, String gender, String age, String sexuality,
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

    public String getAge() {
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

    public void setAge(String age) {
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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSelfDescription(String selfDescription) {
        this.selfDescription = selfDescription;
    }
}
