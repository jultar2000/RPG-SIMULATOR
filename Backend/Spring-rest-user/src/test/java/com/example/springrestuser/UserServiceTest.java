package com.example.springrestuser;

import com.example.springrestuser.user.action.repository.UserActionRepository;
import com.example.springrestuser.user.entity.User;
import com.example.springrestuser.user.repository.UserRepository;
import com.example.springrestuser.user.security.Sha256;
import com.example.springrestuser.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserActionRepository userActionRepository;

    @InjectMocks
    private UserService underTest;

    @Test
    void canAddUserTest() {
        User user = User.builder()
                .login("user")
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();

        underTest.add(user);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canDeleteUserTest() {
        User user = User.builder()
                .login("user")
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();

        underTest.delete(user);
        verify(userRepository).delete(user);
    }

    @Test
    void canUpdateUserTest() {
        User user = User.builder()
                .login("user")
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();

        underTest.add(user);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canFindAllUsers() {
        underTest.findAll();
        verify(userRepository).findAll();
    }

    @Test
    void canFindUserByLoginTest() {
        User user = User.builder()
                .login("user")
                .name("User")
                .surname("uuser")
                .dateOfBirth(LocalDate.of(2000, 12, 4))
                .password(Sha256.hash("useruser"))
                .email("user@gmail.com")
                .build();

        underTest.find("user");
        verify(userRepository).findById("user");
    }
}
