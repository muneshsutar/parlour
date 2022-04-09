package sonalika.Dehury.salon.ui.account;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import sonalika.Dehury.salon.MainActivity;
import sonalika.Dehury.salon.R;
import sonalika.Dehury.salon.data.account.LoginFormRequest;
import sonalika.Dehury.salon.data.account.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private  TextInputEditText inputEmail,inputPassword;
    private TextView userLogin;
    private UserViewModel userViewModel;
    private RelativeLayout llProgressBar;

    FirebaseAuth firebaseAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        inputEmail = view.findViewById(R.id.inputEmail);
        inputPassword = view.findViewById(R.id.inputPassword);
        userLogin = view.findViewById(R.id.userLogin);
        userLogin.setOnClickListener(v -> customerLogin());

        llProgressBar = view.findViewById(R.id.llProgressBar);
        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    private void customerLogin() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (!email.isEmpty() && !password.isEmpty()){
            llProgressBar.setVisibility(View.VISIBLE);
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Intent i = new Intent(getActivity(),MainActivity.class);
                            startActivity(i);
                            llProgressBar.setVisibility(View.GONE);

                        }
                    });


        }else {
            Toast.makeText(getActivity(), "Empty Field Are Not Allowed", Toast.LENGTH_SHORT).show();
        }
    }

}
