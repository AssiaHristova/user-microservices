# user-microservices consist of 3 services that communicate between each other. The userService is an Aggregator, that receives the client request and sends request to the AddressService and TransactionService. The transactionService itself communicates with an 3rd party API. The communication is happening in the Adapters, which send requests to the other services. which 
