
pub mod grpc {
    tonic::include_proto!("com.zwartzon.api.grpc");
}

use grpc::gateway_api_service_client::GatewayApiServiceClient;
use grpc::{
    Empty,
    UserListView
};
use tonic::Request;
use tonic::transport::Channel;

const GRPC_ADDRESS: &str = "http://localhost:8080";


async fn create_grpc_client() -> GatewayApiServiceClient<Channel> {
    let channel = Channel::from_static(GRPC_ADDRESS)
        .connect()
        .await
        .expect("Can't create a channel.");

    GatewayApiServiceClient::new(channel)
}

async fn get_all_users(grpc_client: &mut GatewayApiServiceClient<Channel>) -> Vec<UserListView> {
    let response = grpc_client.get_all_users(Request::new(Empty {})).await;
    let mut result: Vec<UserListView> = Vec::new();

    match response {
        Ok(response) => {
            println!("Ok response.");
            let mut users_stream = response.into_inner();

            while let Some(users_response) = users_stream.message().await.expect("Can't get a user") {
                for user in users_response.items {
                    result.push(user);
                }
            }
        }
        Err(e) => {
            println!("There was an error while handling users: {}", e);
        }
    };

    result
}

async fn users_future() -> Vec<UserListView> {
    let mut grpc_client = create_grpc_client().await;
    get_all_users(&mut grpc_client).await
}


#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    println!("Hello, world!");

    let users = users_future().await;

    println!("Found users: ");
    for user in users {
        println!("{}", user.handle);
    }

    Ok(())
}
