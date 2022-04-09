package sonalika.Dehury.salon.common.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import sonalika.Dehury.salon.common.data.MessageResponse;
import sonalika.Dehury.salon.common.data.NearbySalonsResponse;
import sonalika.Dehury.salon.common.data.SalonSearchModel;
import sonalika.Dehury.salon.data.booking.BookingForm;
import sonalika.Dehury.salon.data.booking.BookingModel;
import sonalika.Dehury.salon.data.account.LoginFormRequest;
import sonalika.Dehury.salon.data.account.RegisterFormRequest;
import sonalika.Dehury.salon.data.account.UserModel;
import sonalika.Dehury.salon.data.booking.SalonRatingForm;
import sonalika.Dehury.salon.ui.home.ModuleResponse;
import sonalika.Dehury.salon.data.salon.SalonReviewsModel;
import sonalika.Dehury.salon.data.salon.SalonServiceModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface RetrofitApiService {

    @POST("customer/login")
    Observable<LoginFormRequest.LoginResponse> logIn(@Body LoginFormRequest loginFormRequest);

    @POST("customer/update")
    Observable<LoginFormRequest.LoginResponse> updateProfile(@Body UserModel userModel);

    @POST("customer/create")
    Observable<MessageResponse> registerUser(@Body RegisterFormRequest registerFormRequest);


    @POST("salon/review")
    Observable<MessageResponse> rateSalon(@Body SalonRatingForm ratingForm);

    @GET("module/index")
    Observable<ModuleResponse> getHomeModules();

    @POST("salon/nearby")
    Observable<NearbySalonsResponse> nearBySalons(@Body SalonSearchModel searchModel);

    @GET("salon/services")
    Observable<List<SalonServiceModel>> salonServices(@Query("salonId") int salonId);

    @GET("booking/customer")
    Observable<List<BookingModel>> customerUpcomingBookings(@Query("customer_id") int customerId);

    @GET("booking/history")
    Observable<List<BookingModel>> customerBookingHistory(@Query("customer_id") int customerId);

    @GET("salon/reviews")
    Observable<List<SalonReviewsModel>> salonReviews(@Query("salonId") int salonId);

    @Multipart
    @POST("salon/create")
    Observable<MessageResponse> postSalon(@PartMap() Map<String, RequestBody> partMap,
                                            @Part MultipartBody.Part logoImage);

    @POST("square/nonce")
    Call<Void> charge(@Body ChargeRequest request);

    class ChargeErrorResponse {
        String errorMessage;
    }

    class ChargeRequest {
        final String nonce;
        final BookingForm bookingForm;

        public ChargeRequest(String nonce, BookingForm bookingForm) {
            this.nonce = nonce;
            this.bookingForm = bookingForm;
        }
    }
}
