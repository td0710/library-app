export const oktaConfig = {
  clientId: "0oam43pps9cjbFaDz5d7",
  issuer: "https://dev-90848118.okta.com/oauth2/default",
  redirectUri: "https://localhost:3000/login/callback",
  scopes: ["openid", "profile", "email"],
  pkce: true,
  disableHttpsCheck: true,
};
