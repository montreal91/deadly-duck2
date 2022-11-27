package com.zwartzon.infrastructure;

import com.zwartzon.domain.user.application.App;
import com.zwartzon.domain.user.core.ports.incoming.GetAllUsersPort;
import com.zwartzon.domain.user.core.ports.incoming.GetUserByHandlePort;
import com.zwartzon.domain.user.core.ports.outgoing.UserRepository;
import com.zwartzon.domain.user.core.UserAdapter;
import com.zwartzon.domain.user.infrastructure.InMemoryUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfiguration {
  @Bean
  public UserRepository userRepository() {
    return new InMemoryUserRepository();
  }

  @Bean
  public UserAdapter userService(UserRepository userRepository) {
    return new UserAdapter(userRepository);
  }

  @Bean
  public App userApp(
      GetAllUsersPort getAllUsersPort, GetUserByHandlePort getUserByHandlePort
  ) {
    return new App(getAllUsersPort, getUserByHandlePort);
  }
}
