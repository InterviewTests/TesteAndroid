package resource.com.br.santanderapp.service;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
