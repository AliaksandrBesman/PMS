package by.bstu.besman.lw6;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Arrays;

public class UserDetailsFragment extends Fragment {

    interface OnFragmentCallIntentAboutContactInfo {
        void callEmail(User user);
        void callPhone(User user);
        void callBrowser(User user);
    }
    String [] educations = { "начальное", "среднее", "высшее"};
    private OnFragmentCallIntentAboutContactInfo mainActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mainActivity = (OnFragmentCallIntentAboutContactInfo) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " должен реализовывать интерфейс OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false);
    }

    @Override
    public void onViewCreated( View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void displaySelectedItem(User user) {
        TableLayout user_info = (TableLayout) getView().findViewById(R.id.user_info);
        int row_count = user_info.getChildCount();
        if (user == null) return;
        if ( Arrays.asList(educations).indexOf(user.getEducation()) == 2)
        {
            user_info.getChildAt(row_count-3 - 1).setVisibility(View.VISIBLE);
        }
        else
        {
            user_info.getChildAt(row_count-3 - 1).setVisibility(View.GONE);
        }
        setUserInfo(user);
    }

    protected void setUserInfo(User user){
        TextView userNameText = getView().findViewById(R.id.name);
        TextView userSurnameText = getView().findViewById(R.id.surname);
        TextView userAgeText = getView().findViewById(R.id.age);

        TextView userCountryText = getView().findViewById(R.id.country);
        TextView userCityText = getView().findViewById(R.id.city);

        TextView userEducationText = getView().findViewById(R.id.education);
        TextView userEd_DegreeText = getView().findViewById(R.id.ed_degree);

        TextView userPhoneNumberext = getView().findViewById(R.id.phoneNumber);
        TextView userEmailText = getView().findViewById(R.id.email);
        TextView userWebSiteText = getView().findViewById(R.id.webSite);
        TextView userWebSiteLabelText = getView().findViewById(R.id.webSiteLabel);



        userNameText.setText(user.getName());
        userSurnameText.setText(user.getSurname());
        userAgeText.setText(String.valueOf(user.getAge()));

        userCountryText.setText(user.getCountry());
        userCityText.setText(user.getCity());

        userEducationText.setText(user.getEducation());
        userEd_DegreeText.setText(user.getEd_degree());

        userPhoneNumberext.setText(user.getPhoneNumber());
        userEmailText.setText(user.getEmail());
        userWebSiteText.setText(user.getWebSite());

        userPhoneNumberext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.callPhone(user);
            }
        });

        userEmailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.callEmail(user);
            }
        });

        userWebSiteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.callBrowser(user);
            }
        });

        ImageView user_image = (ImageView) getView().findViewById(R.id.user_image);
        if (user.getUri() == null)
        {
            user_image.setImageResource(R.mipmap.avatar);
        }
        else
        {
            File auxFile = new File(user.getUri());
            user_image.setImageURI(Uri.parse(auxFile.getAbsolutePath()));
        }


    }
}