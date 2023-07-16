
mod protogen;

use futures::executor;
use grpc::ClientStubExt;
use grpc::StreamingResponse;

use protogen::InputGatewayApi::{Empty, UserListView, GetUsersResponse};
use protogen::InputGatewayApi_grpc::GatewayApiServiceClient;


const GRPC_HOST: &str = "localhost";
const GRPC_PORT: u16 = 8080;


fn collect_user_views(response: StreamingResponse<GetUsersResponse>) -> Vec<UserListView> {
    let response_result = executor::block_on(response.collect());

    let mut result = Vec::new();

    match response_result {
        Ok(users) => {
            let users_vector = users.1.get(0).unwrap();

            for user in users_vector.items.iter() {
                result.push(user.clone());
            }
        }

        Err(e) => {
            panic!("There was an error while handling users: {}", e);
        }
    }
    result
}

fn main() {
    let client = GatewayApiServiceClient::new_plain(
        GRPC_HOST,
        GRPC_PORT,
        Default::default()
    ).unwrap();

    let user_views = collect_user_views(client.get_all_users(
        grpc::RequestOptions::new(), Empty::new()
    ));

    for user in user_views {
        println!("User handle: {}", user.get_handle());
    }
}
