package regularuser_register_screen;

import user_register_use_case.UserRegisterDsGateway;
import user_register_use_case.UserRegisterDsRequestModel;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class FileUser implements UserRegisterDsGateway {

    private final File xlsFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, UserRegisterDsRequestModel> accounts = new HashMap<>();

    public FileUser(String xlsPath) throws IOException {
        xlsFile = new File(xlsPath);

        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("location", 2);
        headers.put("gender", 3);
        headers.put("age", 4);
        headers.put("sexuality", 5);
        headers.put("hobbies", 6);
        headers.put("height", 7);
        headers.put("weight", 8);
        headers.put("contactInformation", 9);
        headers.put("selfDescription", 10);
        headers.put("creation_time", 11);

        if (xlsFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(xlsFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);
                String location = String.valueOf(col[headers.get("location")]);
                String gender = String.valueOf(col[headers.get("gender")]);
                String age = String.valueOf(col[headers.get("age")]);
                String sexuality = String.valueOf(col[headers.get("sexuality")]);
                String hobbies = String.valueOf(col[headers.get("hobbies")]);
                String height = String.valueOf(col[headers.get("height")]);
                String weight = String.valueOf(col[headers.get("weight")]);
                String contactInformation = String.valueOf(col[headers.get("contactInformation")]);
                String selfDescription = String.valueOf(col[headers.get("selfDescription")]);
                String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                UserRegisterDsRequestModel user = new UserRegisterDsRequestModel(username, password,
                        location, gender, age, sexuality, hobbies, height, weight, contactInformation,
                        selfDescription, ldt);
                accounts.put(username, user);
            }

            reader.close();
        }
    }

    /**
     * Add requestModel to storage.
     * @param requestModel the user information to save.
     */
    @Override
    public void save(UserRegisterDsRequestModel requestModel) {
        accounts.put(requestModel.getName(), requestModel);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(xlsFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (UserRegisterDsRequestModel user : accounts.values()) {
                String line = user.getName() + "," + user.getPassword() + "," + user.getLocation() +
                        "," + user.getGender() + "," + user.getAge() + "," +
                        user.getSexuality() + "," + user.getHobbies() + ","
                + user.getHeight() + "," + user.getWeight() + "," + user.getContactInformation() +
                        "," + user.getSelfDescription() + "," + user.getCreationTime();
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

}
