package Gateway;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.management.InvalidAttributeValueException;

public abstract class DatabaseGateway {

    private String likesfile;

    private String profilesfile;

    private String reviewsfile;

    private String popularfile;

    private String recommendfile;

    private String recommendbasefile;

    private String similarfile;

    public DatabaseGateway(String workingdir){
        this.likesfile = workingdir+"/src/main/data/likes.xls";
        this.profilesfile = workingdir+"/src/main/data/profiles.xls";
        this.reviewsfile = workingdir+"/src/main/data/reviews.xls";
        this.popularfile = workingdir+"/src/main/data/popular.xls";
        this.recommendfile = workingdir+"/src/main/data/recommend.xls";
        this.recommendbasefile = workingdir+"/src/main/data/recommend_base.xls";
        this.similarfile = workingdir+"/src/main/data/similar.xls";
    }

    protected HSSFWorkbook ProfilesStyleBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis = new FileInputStream(new File(this.profilesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    protected HSSFWorkbook ProfileStyleBook(String type) throws IOException, InvalidAttributeValueException {
        //obtaining input bytes from a file
        FileInputStream fis;
        switch (type) {
            case "popular":
                fis = new FileInputStream(new File(this.popularfile));
                break;
            case "recommend":
                fis = new FileInputStream(new File(this.recommendfile));
                break;
            case "recommendbase":
                fis = new FileInputStream(new File(this.recommendbasefile));
                break;
            case "similar":
                fis = new FileInputStream(new File(this.similarfile));
                break;
            case "profiles":
                fis = new FileInputStream(new File(this.profilesfile));
                break;
            default:
                throw new InvalidAttributeValueException("ProfileStyleBook only accept [popular, recommend, recommendbase, similar, profiles]");
        }
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    protected HSSFWorkbook LikesBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(new File(this.likesfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    protected HSSFWorkbook ReviewsBook() throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(new File(this.reviewsfile));
        //creating workbook instance that refers to .xls file
        HSSFWorkbook wb=new HSSFWorkbook(fis);

        return wb;
    }

    protected void SaveWorkbook(HSSFWorkbook wb, String type) throws IOException, InvalidAttributeValueException {
        OutputStream fileOut;
        switch (type) {
            case "popular":
                fileOut = new FileOutputStream(this.popularfile);
                break;
            case "recommend":
                fileOut = new FileOutputStream(this.recommendfile);
                break;
            case "recommendbase":
                fileOut = new FileOutputStream(this.recommendbasefile);
                break;
            case "similar":
                fileOut = new FileOutputStream(this.similarfile);
                break;
            case "profiles":
                fileOut = new FileOutputStream(this.profilesfile);
                break;
            case "likes":
                fileOut = new FileOutputStream(this.likesfile);
                break;
            case "reviews":
                fileOut = new FileOutputStream(this.reviewsfile);
                break;
            default:
                throw new InvalidAttributeValueException("SaveWorkbook only accept [popular, recommend, recommendbase, similar, profiles, likes, reviews]");
        }
        //creating workbook instance that refers to .xls file
        wb.write(fileOut);
        wb.close();
    }

    protected String loadStringCell(Cell cell){
        String toreturn;
        if ((cell != null) & (cell.toString() != "")) {
            toreturn = cell.toString();
        } else {
            toreturn = "Unknown";
        }
        return toreturn;
    }

    protected int loadIntCell(Cell cell){
        int toreturn;
        if ((cell != null) & (!Objects.equals(cell.toString(), ""))) {
            toreturn = Double.valueOf(cell.toString()).intValue();
        } else {
            toreturn = -1;
        }
        return toreturn;
    }


}
