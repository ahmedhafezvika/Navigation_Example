package pub.war.navigationexample;


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
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import pub.war.navigationexample.models.Money;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpecifyAmountFragment extends Fragment implements View.OnClickListener {

    private TextInputEditText input_amount;

    private NavController navController;
    private String recipient = "";

    public SpecifyAmountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipient = Objects.requireNonNull(getArguments()).getString("recipient");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolbar(view);

        input_amount = view.findViewById(R.id.input_amount);
        ((TextView)view.findViewById(R.id.recipient)).setText("Sending money to " + recipient);
        view.findViewById(R.id.cancel_btn).setOnClickListener(this);
        view.findViewById(R.id.send_btn).setOnClickListener(this);

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
            case R.id.send_btn:
                if (!TextUtils.isEmpty(Objects.requireNonNull(input_amount.getText()).toString())) {
                    Money money = new Money(Double.parseDouble(input_amount.getText().toString()));
                    Bundle bundle = new Bundle();
                    bundle.putString("recipient", recipient);
                    bundle.putParcelable("amount", money);
                    navController.navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle);
                }
                break;
        }
    }
}
