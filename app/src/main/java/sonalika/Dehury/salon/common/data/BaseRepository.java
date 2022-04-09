/*
 * Copyright (c) 2019. AMWOLLO VICTOR <https://ovicko.com>
 */

package sonalika.Dehury.salon.common.data;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import sonalika.Dehury.salon.common.Constants;
import sonalika.Dehury.salon.common.MesApp;
import sonalika.Dehury.salon.common.api.RetrofitApiService;
import sonalika.Dehury.salon.common.api.RetrofitClient;

public class BaseRepository {
    public RetrofitApiService apiService;
    public Context context;
    public SessionManager sessionManager;
    public MutableLiveData<MessageResponse> liveDataMessage = new MutableLiveData<>();
    public  MessageResponse messageResponse = new MessageResponse();

    public BaseRepository() {
        apiService = RetrofitClient.getInstance().getApiService();
        context = MesApp.getInstance().context;
        sessionManager = new SessionManager(context);
    }

    public void setSuccessMessages(MessageResponse requestResponse ){
        messageResponse.setCode(requestResponse.getCode());
        messageResponse.setMessage(requestResponse.getMessage());
        liveDataMessage.setValue(messageResponse);
    }

    public void setErrorMessages(Throwable throwable){
        messageResponse.setCode(Constants.ERROR_CODE);
        messageResponse.setMessage(throwable.getLocalizedMessage());
        liveDataMessage.setValue(messageResponse);
    }
}
