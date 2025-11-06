// We need these imports
import { KeycloakService } from 'keycloak-angular';

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        // --- YOUR KEYCLOAK DETAILS ---
        url: 'http://localhost:8080', // URL of your Keycloak server
        realm: 'bdcc-real-enset',      // Name of your Realm
        clientId: 'app-test'   // Name of the Client you created
        // --- END YOUR DETAILS ---
      },
      initOptions: {
        onLoad: 'check-sso', // Tries to log in silently on page load
        silentCheckSsoRedirectUri:
          window.location.origin + '/assets/silent-check-sso.html'
      },
      // This is the new, cleaner way to add the token to requests.
      // It will automatically work with your `provideHttpClient()`.
      enableBearerInterceptor: true,

      // Tell the interceptor to ONLY add the token when calling your Gateway
      bearerExcludedUrls: [
        '/assets'
        // Add any other non-api routes here
      ]
    });
}
