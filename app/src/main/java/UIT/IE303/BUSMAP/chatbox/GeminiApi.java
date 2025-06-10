package UIT.IE303.BUSMAP.chatbox;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GeminiApi {
    @Headers("Content-Type: application/json")
    @POST("models/gemini-2.0-flash:generateContent")
    Call<ResponseBody> sendMessage(@Query("key") String apiKey, @Body ChatRequest request);
}
