package com.zwartzon.infrastructure;

import com.zwartzon.application.GrpcApiServiceImpl;
import com.zwartzon.application.UserDtoConverter;
import com.zwartzon.domain.user.application.App;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GrpcApiConfiguration {
  @Bean
  GrpcApiServiceImpl grpcApiService(App userApp, UserDtoConverter converter) {
    return new GrpcApiServiceImpl(userApp, converter);
  }

  @Bean
  UserDtoConverter userDtoConverter() {
    return new UserDtoConverter();
  }

  @Bean
  Server grpcApiGatewayServer(GrpcApiServiceImpl grpcApiService) {
    return ServerBuilder.forPort(8080)
                        .addService(grpcApiService)
                        .build();
  }
}
