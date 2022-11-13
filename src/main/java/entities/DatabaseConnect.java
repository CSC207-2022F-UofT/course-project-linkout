package entities;
import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class DatabaseConnect {

    private String likesfile;

    private String profilesfile;

    public DatabaseConnect(String likespath, String profilespath){
        this.likesfile = likespath;
        this.profilesfile = profilespath;
    }

    private HSSFWorkbook ProfilesBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(new File(this.profilesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    private HSSFWorkbook LikesSheet() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(new File(this.likesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    public Profile loadProfile(String usrname) throws IOException {
        HSSFWorkbook wb = ProfilesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        Profile profile = null;
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(usrname)) {
                String location;
                if (sheet.getRow(i).getCell(2)!= null){
                    location = sheet.getRow(i).getCell(2).toString();
                } else {
                    location = "Unknown";
                }
                String gender;
                if (sheet.getRow(i).getCell(1)!= null){
                    gender = sheet.getRow(i).getCell(1).toString();
                } else {
                    gender = "Unknown";
                }
                int age;
                if (sheet.getRow(i).getCell(0)!= null){
                    age = Double.valueOf(sheet.getRow(i).getCell(0).toString()).intValue();
                } else {
                    age = -1;
                }
                String sexuality;
                if (sheet.getRow(i).getCell(3)!= null){
                    sexuality = sheet.getRow(i).getCell(3).toString();
                } else {
                    sexuality = "Unknown";
                }
                String hobbies;
                if (sheet.getRow(i).getCell(7)!= null){
                    hobbies = sheet.getRow(i).getCell(7).toString();
                } else {
                    hobbies = "Unknown";
                }
                int height;
                if (sheet.getRow(i).getCell(4)!= null){
                    height = Double.valueOf(sheet.getRow(i).getCell(4).toString()).intValue();
                } else {
                    height = -1;
                }
                int weight;
                if (sheet.getRow(i).getCell(6)!= null){
                    weight = Double.valueOf(sheet.getRow(i).getCell(6).toString()).intValue();
                } else {
                    weight = -1;
                }
                int contactInformation;
                if (sheet.getRow(i).getCell(12)!= null){
                    contactInformation = Double.valueOf(sheet.getRow(i).getCell(12).toString()).intValue();
                } else {
                    contactInformation = -1;
                }
                String selfDescription;
                if (sheet.getRow(i).getCell(5)!= null){
                    selfDescription = sheet.getRow(i).getCell(5).toString();
                } else {
                    selfDescription = "Unknown";
                }
                profile = new Profile(
                        location, gender, age, sexuality, hobbies, height, weight, contactInformation, selfDescription
                );
                break;
            }
        }

        return profile;
    }



}
