package fr.android.androidexercises;

import android.widget.EditText;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;

@RunWith(CustomRobolectricTestRunner.class)
public class LoginActivityUnitTest {

    private LoginActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(LoginActivity.class);
    }

    @Test
    public void should_set_logged_state() throws Exception {
        // When
        activity.logged();

        // Then
        Assertions.assertThat(activity.loginLayout).isGone();
        Assertions.assertThat(activity.loggedText).isVisible();
    }

    @Test
    public void should_set_notlogged_state() throws Exception {
        // When
        activity.notLogged();

        // Then
        Assertions.assertThat(activity.loginLayout).isVisible();
        Assertions.assertThat(activity.loggedText).isGone();
    }

    @Test
    public void should_set_logged_with_click() throws Exception {
        // When
        EditText usernameEdit = (EditText) activity.findViewById(R.id.usernameEdit);
        usernameEdit.setText("gpouilloux");
        EditText passwordEdit = (EditText) activity.findViewById(R.id.passwordEdit);
        passwordEdit.setText("123456");
        activity.findViewById(R.id.loginButton).performClick();

        // Then
        Assertions.assertThat(activity.loginLayout).isGone();
        Assertions.assertThat(activity.loggedText).isVisible();
    }
}
