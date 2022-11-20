package entities;
import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.management.InvalidAttributeValueException;

public class DatabaseConnect {

    private String likesfile;

    private String profilesfile;

    private String reviewsfile;

    private String popularfile;

    private String recommendfile;

    private String recommendbasefile;

    private String similarfile;

    public DatabaseConnect(String workingdir){
        this.likesfile = workingdir+"/src/main/data/likes.xls";
        this.profilesfile = workingdir+"/src/main/data/profiles.xls";
        this.reviewsfile = workingdir+"/src/main/data/reviews.xls";
        this.popularfile = workingdir+"/src/main/data/popular.xls";
        this.recommendfile = workingdir+"/src/main/data/recommend.xls";
        this.recommendbasefile = workingdir+"/src/main/data/recommend_base.xls";
        this.similarfile = workingdir+"/src/main/data/similar.xls";
    }

    private HSSFWorkbook ProfilesStyleBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File(this.profilesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    private HSSFWorkbook ProfileStyleBook(String type) throws IOException, InvalidAttributeValueException {
        //obtaining input bytes from a file
        FileInputStream fis;
        if (type.equals("popular")) {
            fis=new FileInputStream(new File(this.popularfile));
        } else if (type.equals("recommend")) {
            fis=new FileInputStream(new File(this.recommendfile));
        } else if (type.equals("recommendbase")) {
            fis=new FileInputStream(new File(this.recommendbasefile));
        } else if (type.equals("similar")) {
            fis=new FileInputStream(new File(this.similarfile));
        } else if (type.equals("profiles")) {
            fis=new FileInputStream(new File(this.profilesfile));
        } else {
            throw new InvalidAttributeValueException("ProfileStyleBook only accept [popular, recommend, recommendbase, similar, profiles]");
        }
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    private HSSFWorkbook LikesBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(new File(this.likesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    private HSSFWorkbook ReviewsBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(new File(this.reviewsfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    private Profile CreateProfile(Row row) {
        String location;
        if ((row.getCell(2) != null) & (row.getCell(2).toString() != "")) {
            location = row.getCell(2).toString();
        } else {
            location = "Unknown";
        }
        String gender;
        if ((row.getCell(1) != null) & (row.getCell(1).toString() != "")) {
            gender = row.getCell(1).toString();
        } else {
            gender = "Unknown";
        }
        int age;
        if ((row.getCell(0) != null) & (row.getCell(0).toString() != "")) {
            age = Double.valueOf(row.getCell(0).toString()).intValue();
        } else {
            age = -1;
        }
        String sexuality;
        if ((row.getCell(3) != null) & (row.getCell(3).toString() != "")) {
            sexuality = row.getCell(3).toString();
        } else {
            sexuality = "Unknown";
        }
        String hobbies;
        if ((row.getCell(7) != null) & (row.getCell(7).toString() != "")) {
            hobbies = row.getCell(7).toString();
        } else {
            hobbies = "Unknown";
        }
        int height;
        if ((row.getCell(4) != null) & (row.getCell(4).toString() != "")) {
            height = Double.valueOf(row.getCell(4).toString()).intValue();
        } else {
            height = -1;
        }
        int weight;
        if ((row.getCell(6) != null) & (row.getCell(6).toString() != "")) {
            weight = Double.valueOf(row.getCell(6).toString()).intValue();
        } else {
            weight = -1;
        }
        int contactInformation;
        if ((row.getCell(11) != null) & (row.getCell(11).toString() != "")) {
            contactInformation = Double.valueOf(row.getCell(11).toString()).intValue();
        } else {
            contactInformation = -1;
        }
        String selfDescription;
        if ((row.getCell(5) != null) & (row.getCell(5).toString() != "")) {
            selfDescription = row.getCell(5).toString();
        } else {
            selfDescription = "Unknown";
        }
        Profile profile = new Profile(
                location, gender, age, sexuality, hobbies, height, weight, contactInformation, selfDescription
        );
        return profile;
    }

    public Profile findProfile(String usrname) throws IOException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        Profile profile = null;
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                profile = CreateProfile(row);
                break;
            }
        }

        return profile;
    }

    public User findUser(String usrname) throws IOException, InvalidAttributeValueException{
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        User user = null;
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                Profile profile = CreateProfile(row);
                String password = row.getCell(9).toString();
                String isVip = row.getCell(10).toString();
                if (isVip.equals("TRUE")){
                    user = new VipUser(password, usrname, profile, true);
                } else {
                    user = new RegularUser(password, usrname, profile);
                }
            }
        }
        return user;
    }

    public ArrayList<User> LoadAllUser(String type) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfileStyleBook(type);
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        ArrayList<User> users = new ArrayList<>();
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Profile profile = CreateProfile(row);
            String usrname = row.getCell(8).toString();
            String password = row.getCell(9).toString();
            String isVip = row.getCell(10).toString();
            User user;
            if (isVip.equals("TRUE")){
                user = new VipUser(password, usrname, profile, true);
            } else {
                user = new RegularUser(password, usrname, profile);
            }
            users.add(user);
        }
        return users;
    }

}
