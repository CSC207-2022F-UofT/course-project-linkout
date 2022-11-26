package screen;

import entities.Review;
import entities.User;
import org.apache.poi.ss.usermodel.*;
import useCases.review_use_case.ReviewGateway;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class FileReview implements ReviewGateway {
    private String excelFilePath;

    public FileReview(String excelFilePath) {
        this.excelFilePath = excelFilePath;
    }

    @Override
    public void saveReview(Review review) {
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getLastRowNum();
            Object[] reviewObject = {review.getId(), review.getRating(), review.getComment()};
            Row row = sheet.createRow(++rowCount);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount);
            cell.setCellValue(rowCount);
            for (Object field : reviewObject) {
                cell = row.createCell(++columnCount);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveReview(int id, User user) {
        try {
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            sheet.removeRow(sheet.getRow(id));
            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void softdeleteReview(int id, User user) {
        //TODO: Implement the method

    }

//    @Override
//    public List<Review> getReviews() {
//        List<Review> reviews = new ArrayList<>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(filename));
//            String line;
//            Review review;
//            while((line = reader.readLine()) != null){
//                String[] data = line.split(",");
//                User user = new RegularUser(null, null, null);
//                //TODO: initialize the user
//                review = new Review(Integer.parseInt(data[1]), data[2], user);
//                review.setId(Integer.parseInt(data[3]));
//                reviews.add(review);
//            }
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return reviews;
//
//    }
}
