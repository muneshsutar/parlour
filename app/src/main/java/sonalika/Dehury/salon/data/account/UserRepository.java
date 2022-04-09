/*
 * Copyright (c) 2019. AMWOLLO VICTOR <https://ovicko.com>
 */

package sonalika.Dehury.salon.data.account;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import sonalika.Dehury.salon.common.data.BaseRepository;
import sonalika.Dehury.salon.common.Constants;
import sonalika.Dehury.salon.common.data.MessageResponse;
import sonalika.Dehury.salon.common.data.SessionManager;


public class UserRepository extends BaseRepository {

    private  static UserRepository userRepository;
    private  MutableLiveData<MessageResponse> liveDataMessage = new MutableLiveData<>();
    private  MutableLiveData<SessionManager> sessionLiveData = new MutableLiveData<>();
    private  MessageResponse messageResponse = new MessageResponse();


    public  static UserRepository getInstance(){
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    @SuppressLint("CheckResult")
    public MutableLiveData<MessageResponse> login(LoginFormRequest loginForm){

        apiService.logIn(loginForm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    messageResponse.setCode(loginResponse.getCode());
                    messageResponse.setMessage(loginResponse.getMessage());

                    if (loginResponse.getCode() == Constants.SUCCESS_CODE) {
                        UserModel customer = loginResponse.getUser();
                        if (customer.getAuthKey() != null) {

                            sessionManager.createSession(
                                    customer.getPhone(),
                                    customer.getUsername(),customer.getEmail(),customer.getAuthKey(),
                                    String.valueOf(customer.getId()));
                            sessionManager.setLogin(true);

                        }

                    }
                    liveDataMessage.setValue(messageResponse);
                }, this::setErrorMessages);

        return  liveDataMessage;

    }

    @SuppressLint("CheckResult")
    public MutableLiveData<MessageResponse> registerUser(RegisterFormRequest formRequest){

        apiService.registerUser(formRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setSuccessMessages, this::setErrorMessages);

        return  liveDataMessage;

    }

    @SuppressLint("CheckResult")
    public MutableLiveData<MessageResponse> updateProfile(UserModel userModel){

        apiService.updateProfile(userModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    messageResponse.setCode(loginResponse.getCode());
                    messageResponse.setMessage(loginResponse.getMessage());

                    if (loginResponse.getCode() == Constants.SUCCESS_CODE) {
                        UserModel customer = loginResponse.getUser();
                        if (customer.getAuthKey() != null) {

                            sessionManager.updateSession(customer.getPhone(),
                                    customer.getUsername(),customer.getEmail());

                            sessionLiveData.postValue(sessionManager);
                        }

                    }
                    liveDataMessage.setValue(messageResponse);
                }, this::setErrorMessages);

        return  liveDataMessage;

    }

    public MutableLiveData<SessionManager> getUpdatedSessionLiveData() {
        sessionLiveData.postValue(sessionManager);
        return sessionLiveData;
    }


    public void logoutUser(){
        sessionManager.logoutUser();
    }
}
