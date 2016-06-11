package net.kibotu.berlinplaces.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;

import com.common.android.utils.logging.Logger;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import net.kibotu.berlinplaces.LocalUser;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.network.RequestProvider;
import net.kibotu.berlinplaces.ui.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import rx.schedulers.Schedulers;

import static android.text.TextUtils.isEmpty;
import static net.kibotu.berlinplaces.misc.SnackbarExtensions.showDangerSnack;

/**
 * Created by Nyaruhodo on 11.06.2016.
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.username)
    TextInputEditText usernameLabel;
    @BindView(R.id.password)
    TextInputEditText passwordLabel;

    @BindView(R.id.facebook_login)
    LoginButton loginButton;

    private CallbackManager callbackManager;

    @Override
    public int getLayout() {
        return R.layout.fragment_login;
    }

    @NonNull
    @Override
    public String getTitle() {
        return "Login";
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, createRegisterCallback());
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
    }

    @NonNull
    private FacebookCallback<LoginResult> createRegisterCallback() {
        return new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Logger.v(tag(), "[facebook] login onSuccess " + loginResult);

                LocalUser.setToken(loginResult.getAccessToken());

                RequestProvider.getFacebookMe()
                        .subscribeOn(Schedulers.newThread())
                        .subscribe(loginResponse -> login(loginResponse.id));
            }

            @Override
            public void onCancel() {
                Logger.v(tag(), "[onCancel] login cancel");
            }

            @Override
            public void onError(FacebookException error) {
                error.printStackTrace();
            }
        };
    }

    @OnClick(R.id.login)
    void loginPressed() {
        final String username = usernameLabel.getText().toString().trim();
        final String password = passwordLabel.getText().toString().trim();

        boolean isValid = true;
        if (isEmpty(username)) {
            isValid = false;
            usernameLabel.setError("Not a valid email address!");
        }

        if (isEmpty(password)) {
            isValid = false;
            passwordLabel.setError("Not a valid password!");
        }

        if (!isValid)
            return;

        login(username + ":" + password);
    }

    private void login(String credentials) {
        RequestProvider.login(credentials)
                .subscribeOn(Schedulers.newThread())
                .subscribe(authenticationResponse -> {
                    Logger.v(tag(), "[login] " + authenticationResponse);

                    // set user token
                    RequestProvider.setUserToken(authenticationResponse.uuid);

                    // load user data
                    LocalUser.load();

                }, throwable -> showDangerSnack(throwable.getMessage()));
    }

    @OnClick(R.id.register)
    void register() {
        final String credentials = usernameLabel.getText().toString().trim() + ":" + passwordLabel.getText().toString().trim();

        RequestProvider.register(credentials)
                .subscribeOn(Schedulers.newThread())
                .subscribe(authenticationResponse -> {
                    Logger.v(tag(), "[register] " + authenticationResponse);

                    // set user token
                    RequestProvider.setUserToken(authenticationResponse.uuid);


                }, throwable -> showDangerSnack(throwable.getMessage()));
    }

    @OnClick(R.id.google)
    void loginWithGoogle() {
        LocalUser.save();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
