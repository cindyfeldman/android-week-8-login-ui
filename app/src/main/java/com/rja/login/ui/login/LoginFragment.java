package com.rja.login.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.rja.login.Preferences;
import com.rja.login.R;
import com.rja.login.ui.main.MainActivity;

public class LoginFragment extends Fragment {

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText userNameView = view.findViewById(R.id.username_input_layout);
        TextInputEditText passwordView = view.findViewById(R.id.password_input_layout);

        MaterialButton logInButton = view.findViewById(R.id.LoginButton);

        String lastUsername = Preferences.getUsernames(requireContext());
        if (lastUsername != null) {
            userNameView.setText(lastUsername);
        }

        logInButton.setOnClickListener(v -> {
            validateUsernameAndPassword(userNameView, passwordView);
            if (validateUsernameAndPassword(userNameView, passwordView)) {
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    onLogin();
            } else {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validateUsernameAndPassword(TextInputEditText username, TextInputEditText password) {
        String usernameText = username.getText().toString() != null ? username.getText().toString() : "";
        String passwordText = password.getText().toString() != null ? username.getText().toString() : "";

        if (usernameText.length() < 3 || usernameText.length() > 25) {
            username.setError("Username must be between 3 and 25 characters");
            return false;
        }
        if (passwordText.length() < 3 || passwordText.length() > 25) {
            return false;
        }
        Preferences.setUsername(requireContext(), usernameText);
        return true;
    }

    private void onLogin() {
        startActivity(MainActivity.createIntent(getContext()));
    }

}
