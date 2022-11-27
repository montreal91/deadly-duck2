
pub mod grpc {
    tonic::include_proto!("com.zwartzon.api.grpc");
}

use grpc::gateway_api_service_client::GatewayApiServiceClient;
use grpc::Empty;


#[tokio::main]
async fn main() -> Result<(), Box<dyn std::error::Error>> {
    println!("Hello, world!");

    let request = tonic::Request::new(Empty {});
    let mut client = GatewayApiServiceClient::connect("http://localhost:8080").await?;


    let response = client.get_all_users(request).await;

    match response {
        Ok(response) => {
            println!("Ok response.");
            let mut users_stream = response.into_inner();

            while let Some(response) = users_stream.message().await.expect("Can't get a user") {
                for user in response.items {
                    println!("User handle: {}", user.handle);
                }
            }
        }
        Err(e) => {
            println!("There was an error while handling users: {}", e);
        }
    };

    Ok(())
}
