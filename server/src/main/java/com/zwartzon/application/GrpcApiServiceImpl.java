package com.zwartzon.application;

import com.zwartzon.api.grpc.Empty;
import com.zwartzon.api.grpc.GatewayApiServiceGrpc;
import com.zwartzon.api.grpc.GetUsersResponse;
import com.zwartzon.api.grpc.UserListView;
import com.zwartzon.domain.user.application.App;
import io.grpc.stub.StreamObserver;

import java.util.List;


public class GrpcApiServiceImpl
    extends GatewayApiServiceGrpc.GatewayApiServiceImplBase {

  private final App userApp;
  private final UserDtoConverter converter;

  public GrpcApiServiceImpl(App userApp, UserDtoConverter converter) {
    this.userApp = userApp;
    this.converter = converter;
  }

  @Override
  public void getAllUsers(
      Empty request, StreamObserver<GetUsersResponse> responseObserver
  ) {
    List<UserListView> responseItems = userApp.getAllUsers()
                                              .stream()
                                              .map(converter::dtoToView)
                                              .toList();
    GetUsersResponse response = GetUsersResponse.newBuilder()
                                                .addAllItems(responseItems)
                                                .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
