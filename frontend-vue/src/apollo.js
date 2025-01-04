import { ApolloClient, InMemoryCache } from '@apollo/client/core'
import { createApolloProvider } from '@vue/apollo-option'
import { HttpLink, split } from "@apollo/client/core"
import { GraphQLWsLink } from "@apollo/client/link/subscriptions"
import { getMainDefinition } from "@apollo/client/utilities"
import { createClient } from "graphql-ws"

const cache = new InMemoryCache()

const httpLink = new HttpLink({
    uri: "http://localhost:8080/graphql"
})

const wsLink = new GraphQLWsLink(
    createClient({
        url: "ws://localhost:8080/graphql",
    })
);

const link = split(
    // split based on operation type
    ({ query }) => {
        const definition = getMainDefinition(query)
        return (
            definition.kind === "OperationDefinition" &&
            definition.operation === "subscription"
        )
    },
    wsLink,
    httpLink
)

const apolloClient = new ApolloClient({
    link,
    cache
})

export default createApolloProvider({
    defaultClient: apolloClient,
})

