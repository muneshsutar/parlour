package sonalika.Dehury.salon.ui.account;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import sonalika.Dehury.salon.MainActivity;
import sonalika.Dehury.salon.R;
import sonalika.Dehury.salon.data.account.RegisterFormRequest;
import sonalika.Dehury.salon.data.account.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {
    private EditText inputEmail,inputPassword,inputUsername,inputPhone,inputPasswordConfirm;
    private TextView registerCustomer;
    private UserViewModel userViewModel;
    private RelativeLayout llProgressBar;
    FirebaseAuth firebaseAuth;



    public RegisterFragment() {
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
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        inputEmail = view.findViewById(R.id.inputEmail);
        inputPassword = view.findViewById(R.id.inputPassword);
        inputUsername = view.findViewById(R.id.inputUsername);
        inputPhone = view.findViewById(R.id.inputPhone);
        inputPasswordConfirm = view.findViewById(R.id.inputPasswordConfirm);

        registerCustomer = view.findViewById(R.id.registerCustomer);
        registerCustomer.setOnClickListener(v -> customerRegister());

        llProgressBar = view.findViewById(R.id.llProgressBar);

        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    private void customerRegister() {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String confirmPassword = inputPasswordConfirm.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();
        String username = inputUsername.getText().toString().trim();


        if (!email.isEmpty() && password.isEmpty()){
            llProgressBar.setVisibility(View.VISIBLE);
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Intent i = new Intent(getActivity(),MainActivity.class);
                    startActivity(i);
                    llProgressBar.setVisibility(View.GONE);

                }
            });

        }else {
            Toast.makeText(getActivity(), "Empty Field Are not Allowed", Toast.LENGTH_SHORT).show();
        }
    }

}
