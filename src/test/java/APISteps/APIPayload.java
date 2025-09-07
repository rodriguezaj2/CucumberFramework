package APISteps;

public class APIPayload {

    public static String createUserPayload(){
        String createdEmployee="{\n" +
                "  \"name\": \"Sora\",\n" +
                "  \"email\": \"KingdomHearts2002@gmail.com\",\n" +
                "  \"password\": \"RikuAndKairi\"\n" +
                "}";
        return createdEmployee;
    }
}
