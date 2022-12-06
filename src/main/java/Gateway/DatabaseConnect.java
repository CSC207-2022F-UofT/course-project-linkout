package Gateway;
import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import entities.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.hamcrest.core.IsInstanceOf;

import javax.management.InvalidAttributeValueException;

public class DatabaseConnect implements UserUpgrade {

    private String likesfile;

    private String profilesfile;

    private String reviewsfile;

    private String popularfile;

    private String recommendfile;

    private String recommendbasefile;

    private String similarfile;

    public DatabaseConnect(String workingdir) {
        this.likesfile = workingdir + "/src/main/data/likes.xls";
        this.profilesfile = workingdir + "/src/main/data/profiles.xls";
        this.reviewsfile = workingdir + "/src/main/data/reviews.xls";
        this.popularfile = workingdir + "/src/main/data/popular.xls";
        this.recommendfile = workingdir + "/src/main/data/recommend.xls";
        this.recommendbasefile = workingdir + "/src/main/data/recommend_base.xls";
        this.similarfile = workingdir + "/src/main/data/similar.xls";
    }

    private HSSFWorkbook ProfilesStyleBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File(this.profilesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb = new HSSFWorkbook(fis);


        return wb;
    }

    private HSSFWorkbook ProfileStyleBook(String type) throws IOException, InvalidAttributeValueException {
        //obtaining input bytes from a file
        FileInputStream fis;
        if (type.equals("popular")) {
            fis = new FileInputStream(new File(this.popularfile));
        } else if (type.equals("recommend")) {
            fis = new FileInputStream(new File(this.recommendfile));
        } else if (type.equals("recommendbase")) {
            fis = new FileInputStream(new File(this.recommendbasefile));
        } else if (type.equals("similar")) {
            fis = new FileInputStream(new File(this.similarfile));
        } else if (type.equals("profiles")) {
            fis = new FileInputStream(new File(this.profilesfile));
        } else {
            throw new InvalidAttributeValueException("ProfileStyleBook only accept [popular, recommend, recommendbase, similar, profiles]");
        }
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb = new HSSFWorkbook(fis);

        return wb;
    }

    private HSSFWorkbook LikesBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File(this.likesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb = new HSSFWorkbook(fis);

        return wb;
    }

    private HSSFWorkbook ReviewsBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File(this.reviewsfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb = new HSSFWorkbook(fis);

        return wb;
    }

    private String loadStringCell(Cell cell) {
        String toreturn;
        if ((cell != null) & (cell.toString() != "")) {
            toreturn = cell.toString();
        } else {
            toreturn = "Unknown";
        }
        return toreturn;
    }

    private int loadIntCell(Cell cell) {
        int toreturn;
        if ((cell != null) & (!Objects.equals(cell.toString(), ""))) {
            toreturn = Double.valueOf(cell.toString()).intValue();
        } else {
            toreturn = -1;
        }
        return toreturn;
    }


    private Profile CreateProfile(Row row) {
        String location = loadStringCell(row.getCell(2));
        String gender = loadStringCell(row.getCell(1));
        int age = loadIntCell(row.getCell(0));
        String sexuality = loadStringCell(row.getCell(3));
        String hobbies = loadStringCell(row.getCell(7));
        int height = loadIntCell(row.getCell(4));
        int weight = loadIntCell(row.getCell(6));
        int contactInformation = loadIntCell(row.getCell(11));
        String selfDescription = loadStringCell(row.getCell(5));
        Profile profile = new Profile(
                location, gender, String.valueOf(age), sexuality, hobbies, String.valueOf(height), String.valueOf(weight),
                String.valueOf(contactInformation), selfDescription
        );
        return profile;
    }

    private Hashtable<String, String> getReviewUsername(int reviewId) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        int currid;
        Hashtable<String, String> userinfo = new Hashtable<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (sheet.getRow(i).getPhysicalNumberOfCells() == 4) {
                currid = loadIntCell(sheet.getRow(i).getCell(3));
            } else {
                continue;
            }
            if (currid == reviewId) {
                Row row = sheet.getRow(i);
                String username = loadStringCell(row.getCell(0));
                String userviewed = loadStringCell(row.getCell(1));
                userinfo.put("username", username);
                userinfo.put("userviewed", userviewed);
                break;
            }
        }
        return userinfo;
    }

    public Review findReview(int reviewId) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ReviewsBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        String stringid = Integer.toString(reviewId);
        Review review = null;
        int currid;
        int rating = -1;
        String comment = "";
        boolean found = false;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currid = loadIntCell(sheet.getRow(i).getCell(0));
            if (currid == reviewId) {
                Row row = sheet.getRow(i);
                rating = loadIntCell(row.getCell(1));
                comment = loadStringCell(row.getCell(2));
                found = true;
                break;
            }
        }
        if (found) {
            Hashtable<String, String> userinfo = getReviewUsername(reviewId);
            review = new Review(rating, comment, userinfo.get("username"), userinfo.get("userviewed"), reviewId);
        } else {
            return review;
        }
        return review;
    }

    public void deleteReview(Integer id_rev) throws IOException {
        HSSFWorkbook wb = ReviewsBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        int currid;

        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currid = loadIntCell(sheet.getRow(i).getCell(0));
            if (currid == id_rev) {
                Row row = sheet.getRow(i);
                row.getCell(1).setCellValue(0);
                row.getCell(2).setCellValue("Hidden");
                wb.write(new File(this.reviewsfile));
                wb.close();
            }
        }
    }
    public Hashtable<Integer, List<Object>> loadAllReviewsGot(String usrname) throws IOException, InvalidAttributeValueException {
        Hashtable<Integer, List<Object>> allreviews = new Hashtable<>();
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(0).toString();
            if ((currname.equals(usrname)) & (sheet.getRow(i).getPhysicalNumberOfCells() == 4) & (sheet.getRow(i).getCell(3).getNumericCellValue() != 0.0)) {
                Row row = sheet.getRow(i);
                int reviewId = loadIntCell(row.getCell(3));
                Review review = findReview(reviewId);
                List<Object> revBody = new ArrayList<>();
                revBody.add(review.getRating());
                revBody.add(review.getComment());
                allreviews.put(reviewId, revBody);
            }
        }
        return allreviews;
    }

    public Profile findProfile(String usrname) throws IOException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
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

    public List<String> findLiked(String usrname) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        List<String> liked = new ArrayList<String>();
        String currname;
        String likeuser;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(0).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                likeuser = loadStringCell(row.getCell(1));
                liked.add(likeuser);
            }
        }

        return liked;
    }

    public List<String> findLikedMe(String usrname) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        List<String> liked = new ArrayList<String>();
        String currname;
        String likeuser;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(1).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                likeuser = loadStringCell(row.getCell(0));
                liked.add(likeuser);
            }
        }

        return liked;
    }

    public User findUser(String usrname) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        User user = null;
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(usrname)) {
                Row row = sheet.getRow(i);
                Profile profile = CreateProfile(row);
                String password = loadStringCell(row.getCell(9));
                String isVip = loadStringCell(row.getCell(10));
                List<String> liked = findLiked(usrname);
                List<String> likedme = findLikedMe(usrname);
                Hashtable<Integer, List<Object>> reviews = loadAllReviewsGot(usrname);
                //String restrictedTime = loadStringCell(row.getCell(12));
                if (isVip.equals("TRUE")) {
                    user = new VipUser(password, usrname, profile, true, liked, likedme, reviews);
                    //user.setRestrictedTime(Float.parseFloat(restrictedTime));
                } else {
                    user = new RegularUser(password, usrname, profile, liked, likedme, reviews);
                    //user.setRestrictedTime(Float.parseFloat(restrictedTime));
                }
                break;
            }
        }
        return user;
    }


    public List<User> LoadAllUser(String type) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfileStyleBook(type);
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        List<User> users = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            Profile profile = CreateProfile(row);
            String usrname = row.getCell(8).toString();
            String password = row.getCell(9).toString();
            String isVip = row.getCell(10).toString();
            List<String> liked = findLiked(usrname);
            List<String> likedme = findLikedMe(usrname);
            Hashtable<Integer, List<Object>> reviews = loadAllReviewsGot(usrname);
            User user;
            if (isVip.equals("TRUE")) {
                user = new VipUser(password, usrname, profile, true, liked, likedme, reviews);
            } else {
                user = new RegularUser(password, usrname, profile, liked, likedme, reviews);
            }
            users.add(user);
        }
        return users;
    }

    public boolean upgrade(String accName, boolean status) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ProfilesStyleBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet = wb.getSheetAt(0);
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            currname = sheet.getRow(i).getCell(8).toString();
            if (currname.equals(accName)) {
                sheet.getRow(i).getCell(10).setCellValue(status);
                wb.write(new File(this.profilesfile));
                wb.close();
                return true;
            }
        }

        return false;

    }

}