import { createAuthGuard } from 'keycloak-angular';

export const MyAuthGuard = createAuthGuard({

  // This function is called by the router to check access
  canActivate: (instance, route, state) => {

    const requiredRoles = route.data['roles'];

    // If no roles are required, just check if authenticated
    if (!requiredRoles || requiredRoles.length === 0) {
      return instance.isAuthenticated();
    }

    // Check if user has the required roles
    return requiredRoles.some((role: string) => instance.hasRealmRole(role));
  },

  // This function is called if canActivate returns false
  onAuthRequired: (instance, state) => {
    // Redirect to login, passing the URL they tried to access
    instance.login({
      redirectUri: window.location.origin + state.url
    });
  }
});
