package sonalika.Dehury.salon.data.account;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import sonalika.Dehury.salon.common.data.SessionManager;

public class AccountViewModel extends AndroidViewModel {

    private SessionManager sessionManager;

    public AccountViewModel(Application application) {
        super(application);
        sessionManager = new SessionManager(application);
    }

}