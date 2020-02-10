package pub.war.navigationexample;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseRecipientFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText input_recipient;

    private NavController navController;

    public ChooseRecipientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view);

        input_recipient = view.findViewById(R.id.input_recipient);
        view.findViewById(R.id.cancel_btn).setOnClickListener(this);
        view.findViewById(R.id.next_btn).setOnClickListener(this);

        navController = Navigation.findNavController(view);
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity()))
                .getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_btn:
                Objects.requireNonNull(getActivity()).onBackPressed();
                break;
            case R.id.next_btn:
                if (!TextUtils.isEmpty(Objects.requireNonNull(input_recipient.getText()).toString())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("recipient", input_recipient.getText().toString());
                    navController.navigate(R.id.action_chooseRecipientFragment_to_specifyAmountFragment, bundle);
                }
                break;
        }
    }
}
