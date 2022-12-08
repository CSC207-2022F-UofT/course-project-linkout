package use_cases.user_action_use_case;

import entities.User;
import entities.VipUser;
import org.junit.jupiter.api.Test;
import use_cases.recommend_use_case.RecommendGateway;


import javax.management.InvalidAttributeValueException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LikesGatewayTest {

    public void initializeDataset() throws IOException, InterruptedException {
        RecommendGateway db = new RecommendGateway(System.getProperty("user.dir"));
        // Re-initiate dataset
        String[] command1 = {"rm", String.format("%s/src/main/data/likes.xls", db.getWorkingDir())};
        String[] command2 = {"cp", String.format("%s/src/main/data/data_storage/likes.xls", db.getWorkingDir()),
                String.format("%s/src/main/data", db.getWorkingDir())};
        Runtime runtime = Runtime.getRuntime();
        runtime.exec(command1);
        runtime.exec(command2).waitFor();

    }

    @Test
    void findLiked() throws IOException, InterruptedException {

        initializeDataset();

        LikesGateway likesGateway = new LikesGateway(System.getProperty("user.dir"));
        List<String> likedList = likesGateway.findLiked("acc1");

        assertFalse(likedList.isEmpty());

    }

    @Test
    void findLikedMe() throws IOException, InterruptedException {

        initializeDataset();

        LikesGateway likesGateway = new LikesGateway(System.getProperty("user.dir"));
        List<String> likedList = likesGateway.findLikedMe("acc1");

        assertFalse(likedList.isEmpty());

    }

    @Test
    void isSeen() throws IOException, InterruptedException {

        initializeDataset();

        LikesGateway likesGateway = new LikesGateway(System.getProperty("user.dir"));
        assertFalse(likesGateway.isSeen("acc1", "acc0"));
        assertTrue(likesGateway.isSeen("acc1", "acc1"));

    }

    @Test
    void isLiked() throws IOException, InterruptedException {

        initializeDataset();

        LikesGateway likesGateway = new LikesGateway(System.getProperty("user.dir"));
        assertFalse(likesGateway.isLiked("acc1", "acc0"));
        assertFalse(likesGateway.isLiked("acc1", "acc3"));
        assertTrue(likesGateway.isLiked("acc1", "acc47"));
    }

    @Test
    void findUser() throws IOException, InterruptedException, InvalidAttributeValueException {

        initializeDataset();
        LikesGateway likesGateway = new LikesGateway(System.getProperty("user.dir"));
        User userFound = likesGateway.findUser("acc0");
        assertNotNull(userFound);
        assertEquals(userFound.getAccountName(), "acc0");
        assertEquals(userFound.displayProfile().getLocation(), "south san francisco, california");

        User userFound2 = likesGateway.findUser("acc1");
        assertNotNull(userFound2);
        assertEquals(userFound2.getAccountName(), "acc1");
        assertTrue(userFound2 instanceof VipUser);
    }

    @Test
    void setLike() throws IOException, InterruptedException, InvalidAttributeValueException {

        initializeDataset();
        LikesGateway likesGateway = new LikesGateway(System.getProperty("user.dir"));
        likesGateway.setLike("acc1", "acc1");
        assertTrue(likesGateway.isLiked("acc1", "acc1"));

        likesGateway.setLike("acc0", "acc1");
        assertFalse(likesGateway.isLiked("acc0", "acc1"));

        initializeDataset();

    }
}