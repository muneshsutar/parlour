package sonalika.Dehury.salon.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import sonalika.Dehury.salon.common.data.LocationLiveData;
import sonalika.Dehury.salon.common.data.LocationModel;
import sonalika.Dehury.salon.common.data.SessionManager;
import sonalika.Dehury.salon.data.salon.SalonRepository;
import sonalika.Dehury.salon.data.account.UserRepository;

public class HomeViewModel extends AndroidViewModel {
    private LiveData<LocationModel> locationModelLiveData;
    private LiveData<Boolean> gpsStatus;
    private SalonRepository salonRepository;
    private UserRepository userRepository;

    private MutableLiveData<ModuleResponse> moduleResponse;
    private MutableLiveData<SessionManager> sessionLiveData = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        locationModelLiveData = new LocationLiveData(application);
        salonRepository = SalonRepository.getInstance();
        userRepository = UserRepository.getInstance();
    }

    public LiveData<LocationModel> getLocation() {
        return locationModelLiveData;
    }

    public MutableLiveData<ModuleResponse> getModuleResponse() {
        moduleResponse = salonRepository.getHomeModules();
        return moduleResponse;
    }

    public MutableLiveData<SessionManager> getSessionLiveData() {
        sessionLiveData = userRepository.getUpdatedSessionLiveData();
        return sessionLiveData;
    }
}