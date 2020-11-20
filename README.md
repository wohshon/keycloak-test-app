# Test services for RHSSO / keycloak

Test services
- app1, springboot mvc: open and protected web endpoints; protected end point calls an internal service which invokes service1
- service1, springboot rest service, protected via a different client in keycloak
- service2, not used for now
