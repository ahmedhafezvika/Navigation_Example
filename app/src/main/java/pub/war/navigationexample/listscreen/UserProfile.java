package pub.war.navigationexample.listscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import pub.war.navigationexample.R;
import static pub.war.navigationexample.listscreen.MyAdapter.USERNAME_KEY;

public class UserProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        String name = getArguments().getString(USERNAME_KEY) == null ? getArguments().getString(USERNAME_KEY) : "Ali Connors";
        ((TextView)view.findViewById(R.id.profile_user_name)).setText(name);

        return view;
    }
}
