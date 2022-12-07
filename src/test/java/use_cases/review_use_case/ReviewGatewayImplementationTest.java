package use_cases.review_use_case;

import entities.Review;
import entities.User;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import use_cases.recommend_use_case.RecommendGateway;
import use_cases.regular_user_register_use_case.UserGateway;

import javax.management.InvalidAttributeValueException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReviewGatewayImplementationTest {

    public void initializeDataset() throws IOException, InterruptedException {
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        // Reinitiate dataset
        String[] command1 = {"rm", String.format("%s/src/main/data/likes.xls", db.getWorkingDir())};
        String[] command2 = {"cp", String.format("%s/src/main/data/data_storage/likes.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        String[] command3 = {"rm", String.format("%s/src/main/data/reviews.xls", db.getWorkingDir())};
        String[] command4 = {"cp", String.format("%s/src/main/data/data_storage/reviews.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command1);
        runtime.exec(command2);
        runtime.exec(command3);
        runtime.exec(command4).waitFor();

    }

    @Test
    void findReview() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();

        ReviewGatewayImplementation reviewGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        Review reviewFound = reviewGateway.findReview(10);
        assertEquals(reviewFound.getId(), 10);

    }

    @Test
    void getReviews() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();

        ReviewGatewayImplementation reviewGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        Hashtable<Integer, List<Object>> allReviews = reviewGateway.getReviews("acc1");
        assertNotNull(allReviews);
        assertFalse(allReviews.isEmpty());

    }

    @Test
    void saveReview() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();

        ReviewGatewayImplementation reviewGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        Review toSave = new Review(3, "Test", "acc0", "acc1", 99999);
        reviewGateway.saveReview(toSave);

        FileInputStream fisreviews=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/reviews.xls"));
        HSSFWorkbook wbreviews=new HSSFWorkbook(fisreviews);
        HSSFSheet sheetreviews = wbreviews.getSheetAt(0);

        boolean foundreview = false;
        for (int i = 1; i < sheetreviews.getPhysicalNumberOfRows(); i++) {
            if (sheetreviews.getRow(i) == null){
                continue;
            }

            if (Double.valueOf(sheetreviews.getRow(i).getCell(0).toString()).intValue() == 99999) {
                foundreview = true;
                break;
            }
        }

        assertFalse(foundreview);


        toSave = new Review(3, "Test", "acc1", "acc1732", 99999);
        reviewGateway.saveReview(toSave);

        fisreviews=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/reviews.xls"));
        wbreviews=new HSSFWorkbook(fisreviews);
        sheetreviews = wbreviews.getSheetAt(0);

        foundreview = false;
        for (int i = 1; i < sheetreviews.getPhysicalNumberOfRows(); i++) {
            if (sheetreviews.getRow(i) == null){
                continue;
            }

            if (Double.valueOf(sheetreviews.getRow(i).getCell(0).toString()).intValue() == 99999) {
                foundreview = true;
                break;
            }
        }

        assertTrue(foundreview);

        FileInputStream fislikes=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/likes.xls"));
        HSSFWorkbook wblikes=new HSSFWorkbook(fislikes);
        HSSFSheet sheetlikes = wblikes.getSheetAt(0);
        boolean foundId = false;
        for (int i = 1; i < sheetlikes.getPhysicalNumberOfRows(); i++) {
            if (sheetlikes.getRow(i) == null){
                continue;
            }

            if (sheetlikes.getRow(i).getPhysicalNumberOfCells() != 4){
                continue;
            }

            if (sheetlikes.getRow(i).getCell(3).toString().isEmpty()){
                continue;
            }

            if (Double.valueOf(sheetlikes.getRow(i).getCell(3).toString()).intValue() == 3728) {
                foundId = true;
                break;
            }
        }
        assertTrue(foundId);

        initializeDataset();

        reviewGateway.removeReview(3728, "acc1");
        reviewGateway.saveReview(toSave);


        fisreviews=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/reviews.xls"));
        wbreviews=new HSSFWorkbook(fisreviews);
        sheetreviews = wbreviews.getSheetAt(0);

        foundreview = false;
        for (int i = 1; i < sheetreviews.getPhysicalNumberOfRows(); i++) {
            if (sheetreviews.getRow(i) == null){
                continue;
            }

            if (Double.valueOf(sheetreviews.getRow(i).getCell(0).toString()).intValue() == 99999) {
                foundreview = true;
                break;
            }
        }

        assertTrue(foundreview);

        initializeDataset();

    }

    @Test
    void removeReview() throws IOException, InvalidAttributeValueException, InterruptedException {

        initializeDataset();

        FileInputStream fisreviews=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/reviews.xls"));
        HSSFWorkbook wbreviews=new HSSFWorkbook(fisreviews);
        HSSFSheet sheetreviews = wbreviews.getSheetAt(0);

        ReviewGatewayImplementation reviewGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        reviewGateway.removeReview(3728, "acc1");
        boolean foundreview = false;
        for (int i = 1; i < sheetreviews.getPhysicalNumberOfRows(); i++) {
            if (sheetreviews.getRow(i) == null){
                continue;
            }

            if (sheetreviews.getRow(i).getCell(0).toString().equals("3728")) {
                foundreview = true;
                break;
            }
        }
        assertFalse(foundreview);

        FileInputStream fislikes=new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/data/likes.xls"));
        HSSFWorkbook wblikes=new HSSFWorkbook(fislikes);
        HSSFSheet sheetlikes = wblikes.getSheetAt(0);
        boolean foundId = false;
        for (int i = 1; i < sheetlikes.getPhysicalNumberOfRows(); i++) {
            if (sheetlikes.getRow(i) == null){
                continue;
            }

            if (sheetlikes.getRow(i).getPhysicalNumberOfCells() != 4){
                continue;
            }

            if (sheetlikes.getRow(i).getCell(3).toString().isEmpty()){
                continue;
            }

            if (Double.valueOf(sheetlikes.getRow(i).getCell(3).toString()).intValue() == 3728) {
                foundId = true;
                break;
            }
        }
        assertFalse(foundId);

        initializeDataset();

    }

    @Test
    void getLargestReviewId() throws IOException, InterruptedException {

        initializeDataset();

        ReviewGatewayImplementation reviewGateway = new ReviewGatewayImplementation(System.getProperty("user.dir"));
        assertEquals(4999, reviewGateway.getLargestReviewId());

    }
}