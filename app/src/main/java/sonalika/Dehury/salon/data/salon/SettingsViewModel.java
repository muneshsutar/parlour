package sonalika.Dehury.salon.data.salon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import sonalika.Dehury.salon.common.data.MessageResponse;
import sonalika.Dehury.salon.common.data.SessionManager;
import sonalika.Dehury.salon.data.account.UserModel;
import sonalika.Dehury.salon.data.account.UserRepository;

public class SettingsViewModel extends AndroidViewModel {

    private SessionManager sessionManager;
    private UserRepository userRepository;
    private MutableLiveData<SessionManager> sessionLiveData = new MutableLiveData<>();

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();
    }

    public MutableLiveData<SessionManager> getSessionLiveData() {
        sessionLiveData = userRepository.getUpdatedSessionLiveData();
        return sessionLiveData;
    }

    public LiveData<MessageResponse> updateProfile(UserModel userModel) {
        MutableLiveData<MessageResponse> responseMutableLiveData = userRepository.updateProfile(userModel);
        userRepository.getUpdatedSessionLiveData();
        return responseMutableLiveData;
    }


    public void customerLogout(){
        userRepository.logoutUser();
    }
}