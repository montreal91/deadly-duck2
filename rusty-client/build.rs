
fn main() {
    protoc_rust_grpc::Codegen::new()
        .out_dir("src/protogen")
        .input("proto/InputGatewayApi.proto")
        .rust_protobuf(true)
        .run()
        .expect("Error Compilig protocol buffer.");
}
