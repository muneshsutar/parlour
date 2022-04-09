package sonalika.Dehury.salon.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import sonalika.Dehury.salon.R;
import sonalika.Dehury.salon.ui.CommonPagerAdapter;

import static androidx.fragment.app.FragmentPagerAdapter.*;

public class AccountActivity extends AppCompatActivity {

    TextView loginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ViewPager viewPager = findViewById(R.id.accountVpPager);
        CommonPagerAdapter commonPagerAdapter = new CommonPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        commonPagerAdapter.addFragment(new LoginFragment());
        commonPagerAdapter.addFragment(new RegisterFragment());
        viewPager.setAdapter(commonPagerAdapter);

    }
}
