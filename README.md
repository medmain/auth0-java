# Medmain's Auth0 Java SDK Fork

There are some limitations with the Auth0 SDK as it currently exists that were causing us problems with our usage of the `ManagementAPI`:

## Lack of ability to handle token refreshes, and [no intention to add it](https://github.com/auth0/auth0-java/issues/212).

Each `Entity` object keeps its own access token. This makes it completely impractical to handle token refreshes, because every existing entity would have to be monitored and updated or re-created when a token expires.

Ideally, the `ManagementAPI` would implement an interface, and we could implement a different version that does handle refreshing. Unfortunately this is not the case.

Our solution is to expose the underlying `OkHttpClient` in this fork. In our application, we wrap up the `ManagementAPI` and do the following two things:

1. Add interceptors to the now exposed `OkHttpClient` to handle fetching a new token if it has expired, and also to inject the access token into the HTTP headers of the request.
2. Pass a dummy token into `ManagementAPI`'s `Entity` classes, which later get replaced by the above filter.

Using the above strategy, all the entities share the same access token, which is automatically refreshed when it becomes invalid.

## No support for rate limiting

This was previously unavailable so we were adding our own interceptor, but support for this was added in [1.34.0](https://github.com/auth0/auth0-java/releases/tag/1.34.0).

# What's in this fork

This fork only exposes the underlying `OkHttpClient` - the interceptors described above are defined in our application code.

We also expose the constructors to the `Entity` classes, and the management API's `baseUrl`, to allow us to inject the dummy access token and modified http client into the `Entity` classes in our wrapped-up management API. 
