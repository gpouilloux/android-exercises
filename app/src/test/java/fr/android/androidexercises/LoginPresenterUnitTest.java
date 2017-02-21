package fr.android.androidexercises;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterUnitTest {

    @Mock
    LoginActivity loginActivity;

    @InjectMocks
    LoginPresenter loginPresenter;

    @Test
    public void password_should_be_valid() throws Exception {
        loginPresenter.checkCredentials("password");

        Mockito.verify(loginActivity).logged();
        Mockito.verify(loginActivity).message(R.string.text_logged);
    }

    @Test
    public void password_invalid_because_length_insufficient() throws Exception {
        loginPresenter.checkCredentials("ab");

        Mockito.verify(loginActivity).notLogged();
        Mockito.verify(loginActivity).message(R.string.notLogged);
    }

    @Test
    public void password_invalid_because_is_null() throws Exception {
        loginPresenter.checkCredentials(null);

        Mockito.verify(loginActivity).notLogged();
        Mockito.verify(loginActivity).message(R.string.notLogged);
    }
}
