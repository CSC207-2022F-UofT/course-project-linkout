package use_cases.review_use_case;

import Gateway.DatabaseGateway;
import entities.Review;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ReviewGatewayImplementation extends DatabaseGateway implements use_cases.review_use_case.ReviewGateway {


    public ReviewGatewayImplementation(String workingdir) {
        super(workingdir);
    }

    private Hashtable<String, String> getReviewUsername(int reviewId) throws IOException {
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        int currid;
        Hashtable<String, String> userinfo = new Hashtable<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (sheet.getRow(i) == null){
                continue;
            }
            if (sheet.getRow(i).getPhysicalNumberOfCells() == 4){
                currid = loadIntCell(sheet.getRow(i).getCell(3));
            }else{
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

    @Override
    public Review findReview(int reviewId) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wb = ReviewsBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        Review review = null;
        int currid;
        int rating = -1;
        String comment = "";
        boolean found = false;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (sheet.getRow(i) == null){
                continue;
            }
            currid = loadIntCell(sheet.getRow(i).getCell(0));
            if (currid == reviewId) {
                Row row = sheet.getRow(i);
                rating = loadIntCell(row.getCell(1));
                comment = loadStringCell(row.getCell(2));
                found = true;
                break;
            }
        }
        if (found){
            Hashtable<String, String> userinfo = getReviewUsername(reviewId);
            review = new Review(rating, comment, userinfo.get("username"), userinfo.get("userviewed"), reviewId);
        }else{
            return review;
        }
        return review;
    }

    public Hashtable<Integer, List<Object>> getReviews(String usrname) throws IOException, InvalidAttributeValueException {
        Hashtable<Integer, List<Object>> allreviews = new Hashtable<>();
        HSSFWorkbook wb = LikesBook();
        //creating a Sheet object to retrieve the object
        HSSFSheet sheet=wb.getSheetAt(0);
        String currname;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            if (sheet.getRow(i) == null){
                continue;
            }
            currname = sheet.getRow(i).getCell(1).toString();
            if ((currname.equals(usrname)) & (sheet.getRow(i).getPhysicalNumberOfCells() == 4)) {
                 if (sheet.getRow(i).getCell(3).getNumericCellValue() == 0.0){
                     continue;
                 }
                Row row = sheet.getRow(i);
                int reviewId = loadIntCell(row.getCell(3));
                Review review = findReview(reviewId);
                if (review == null){
                    continue;
                }
                List<Object> revBody = new ArrayList<>();
                revBody.add(review.getRating());
                revBody.add(review.getComment());
                allreviews.put(reviewId, revBody);
            }
        }
        return allreviews;
    }

    @Override
    public void saveReview(Review review) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wblikes = LikesBook();
        HSSFSheet sheetlikes=wblikes.getSheetAt(0);
        HSSFWorkbook wbreviews = ReviewsBook();
        HSSFSheet sheetreviews=wbreviews.getSheetAt(0);
        String username;
        String userviewed;
        boolean liked = false;
        Row rowReview;
        String currusername;
        String curruserviewed;
        int currliked;
        boolean added = false;

        if (findReview(review.getId()) != null){
            return;
        }
        username = review.getWriter();
        userviewed = review.getReceiver();

        for (int i = 1; i < sheetlikes.getPhysicalNumberOfRows(); i++) {
            currusername = loadStringCell(sheetlikes.getRow(i).getCell(0));
            curruserviewed = loadStringCell(sheetlikes.getRow(i).getCell(1));
            currliked = loadIntCell(sheetlikes.getRow(i).getCell(2));
            if ((currusername.equals(username)) & (curruserviewed.equals(userviewed)) & (currliked == 1)) {
                sheetlikes.getRow(i).createCell(3).setCellValue(review.getId());
                liked = true;
                break;
            }
        }
        if (!liked){
            return;
        }

        for (int i = 1; i < sheetreviews.getPhysicalNumberOfRows(); i++) {
            if (sheetreviews.getRow(i) == null) {
                rowReview = sheetreviews.createRow(i);
                rowReview.createCell(0).setCellValue(review.getId());
                rowReview.createCell(1).setCellValue(review.getRating());
                rowReview.createCell(2).setCellValue(review.getComment());
                added = true;
                break;
            }
        }

        if (!added){
            rowReview = sheetreviews.createRow(sheetreviews.getPhysicalNumberOfRows());
            rowReview.createCell(0).setCellValue(review.getId());
            rowReview.createCell(1).setCellValue(review.getRating());
            rowReview.createCell(2).setCellValue(review.getComment());
        }


        SaveWorkbook(wblikes, "likes");
        SaveWorkbook(wbreviews, "reviews");
    }

    @Override
    public void removeReview(int id, String username) throws IOException, InvalidAttributeValueException {
        HSSFWorkbook wblikes = LikesBook();
        HSSFSheet sheetlikes=wblikes.getSheetAt(0);
        HSSFWorkbook wbreviews = ReviewsBook();
        HSSFSheet sheetreviews=wbreviews.getSheetAt(0);
        String currusername;
        int currid;
        boolean found = false;
        //Delete from likes.xls
        for (int i = 1; i < sheetlikes.getPhysicalNumberOfRows(); i++) {
            if (sheetlikes.getRow(i).getPhysicalNumberOfCells() != 4){
                continue;
            }
            currusername = loadStringCell(sheetlikes.getRow(i).getCell(1));
            currid = loadIntCell(sheetlikes.getRow(i).getCell(3));
            if ((currusername.equals(username)) & (currid == id)) {
                sheetlikes.getRow(i).removeCell(sheetlikes.getRow(i).getCell(3));
                found = true;
                break;
            }
        }

        if (!found){
            return;
        }

        //Delete from reviews.xls
        for (int i = 1; i < sheetlikes.getPhysicalNumberOfRows(); i++) {
            currid = loadIntCell(sheetreviews.getRow(i).getCell(0));
            if (currid == id) {
                sheetreviews.removeRow(sheetreviews.getRow(i));
                break;
            }
        }

        SaveWorkbook(wblikes, "likes");
        SaveWorkbook(wbreviews, "reviews");
    }

    @Override
    public int getLargestReviewId() throws IOException {
        HSSFWorkbook wbreviews = ReviewsBook();
        HSSFSheet sheetreviews=wbreviews.getSheetAt(0);
        int currid;
        int maxid = 0;
        boolean found = false;
        //Delete from likes.xls
        for (int i = 1; i < sheetreviews.getPhysicalNumberOfRows(); i++) {
            if (sheetreviews.getRow(i) == null){
                continue;
            }
            currid = loadIntCell(sheetreviews.getRow(i).getCell(0));
            if (currid > maxid) {
                maxid = currid;
            }
        }
        return maxid;
    }

}
