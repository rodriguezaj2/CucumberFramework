package APISteps;

public class APIPayload {

    public static String createUserPayload(){
        String createdUser="{\n" +
                "  \"name\": \"Sora\",\n" +
                "  \"email\": \"KingdomHearts2002@gmail.com\",\n" +
                "  \"password\": \"RikuAndKairi\"\n" +
                "}";
        return createdUser;
    }

    public static String createEmployeePayload(){
        String createdEmployee="{\n" +
                "  \"emp_firstname\": \"Sora\",\n" +
                "  \"emp_lastname\": \"K\",\n" +
                "  \"emp_middle_name\": \"Blade\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2002-12-21\",\n" +
                "  \"emp_status\": \"Employed\",\n" +
                "  \"emp_job_title\": \"Keyblade Wielder\"\n" +
                "}";
        return createdEmployee;
    }
}
