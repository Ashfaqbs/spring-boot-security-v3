## **1. OAuth & OAuth2**
### **What is OAuth?**
- **OAuth** stands for **Open Authorization** and is a protocol for **authorization**. It allows third-party services to access resources on behalf of a user without sharing their credentials (like a password).
  
### **OAuth vs. OAuth2:**
- **OAuth2** is the newer version of OAuth that simplifies the process by:
  - Eliminating complex cryptography and signature requirements.
  - Using **tokens** to authorize third-party apps to access user resources.
  - **OAuth** focuses solely on **authorization**, not on user **authentication**.

### **Key Concepts in OAuth2:**
- **Access Token**: A token that grants limited access to the resource server.
- **Authorization Code**: An intermediate code exchanged for an access token.
- **Resource Owner**: The user who owns the data.
  
### **Use Case**:
- Applications can allow users to log in via OAuth2 providers like Google or Facebook, allowing limited access to their profile without exposing passwords.

---

## **2. OpenID Connect (OIDC)**
### **What is OIDC?**
- **OIDC** is an identity layer on top of OAuth2. It adds **authentication** to the authorization capabilities of OAuth2.
- **OAuth2** allows apps to access resources; **OIDC** allows apps to know **who the user is**.

### **How It Works:**
- After a successful OAuth2 process, OIDC adds an **ID token** that contains details about the user (like name, email, etc.).

### **Use Case**:
- When users log in via Google or Facebook, OIDC ensures that not only is the resource access granted, but the identity of the user is confirmed.

---

## **3. SSO (Single Sign-On)**
### **What is SSO?**
- **SSO** allows users to authenticate **once** and access multiple applications without logging in again.
- **How it works**: Once you log in through an SSO provider (e.g., Google), any other app linked to that provider will trust the existing session and log you in automatically.

### **Use Case**:
- After logging into Gmail, you also gain access to Google Drive, Google Docs, etc., without re-entering our credentials.

  With Single Sign-On (SSO), when we log in to one application, we'll be automatically logged in to all other applications that share the same SSO domain.

Scenario:

Let's say we have 3 applications:

1. App A 
2. App B 
3. App C 

All 3 apps are configured with SSO using the same Identity Provider (IdP).

Login Flow:

1. You navigate to App A  and click login.
2. You're redirected to the IdP's login page.
3. You enter your credentials and authenticate with the IdP.
4. IdP issues an SSO token (or session cookie).
5. You're redirected back to App A, now logged in.

Automatic Login to Other Apps:

1. You navigate to App B .
2. App B checks for the SSO token (or session cookie).
3. Since the token is valid, App B logs you in automatically.
4. Same happens for App C .

Benefits:

1. Seamless user experience across multiple apps.
2. Reduced password fatigue.
3. Improved security.

SSO Types:

1. Horizontal SSO: Multiple apps share the same SSO domain.
2. Vertical SSO: Multiple apps within a single organization.

Common SSO Implementations:

1. OAuth 2.0
2. OpenID Connect (OIDC)
3. SAML (Security Assertion Markup Language)
4. LDAP (Lightweight Directory Access Protocol)

Real-world Examples:

1. Google Apps (Gmail, Drive, Docs)
2. Microsoft 365 (Office, Outlook, Teams)
3. Amazon Web Services (AWS) SSO


---

## **4. Multi-Factor Authentication (MFA)**
### **What is MFA?**
- **MFA** is a security system that requires more than one method of authentication to verify the user’s identity. It uses a combination of factors:
  - **Something you know** (password, PIN)
  - **Something you have** (smartphone, hardware token)
  - **Something you are** (biometric data like fingerprints)

### **Types of MFA:**
1. **Knowledge Factor**: Password, PIN.
2. **Possession Factor**: OTP via SMS, authenticator app, security tokens (e.g., YubiKey).
3. **Inherence Factor**: Biometrics (fingerprint, face recognition).
4. **Location-Based Factor**: Verifies based on user’s geographic location.
5. **Time-Based Factor**: Restricts access based on time (e.g., business hours).

### **2FA (Two-Factor Authentication)**:
- **2FA** is a specific type of **MFA**, where **exactly two** factors are required for login (commonly a password and OTP).

### **Use Case**:
- Gmail login with password (something you know) and OTP sent to your phone (something you have).

---

## **5. Okta**
### **What is Okta?**
- **Okta** is a cloud-based identity and access management service provider. It facilitates **SSO**, **OAuth2**, **OIDC**, and **MFA** to manage and secure user access to applications.
- **What it provides**:
  - User authentication and authorization.
  - SSO across multiple applications.
  - Integration with MFA methods (like 2FA).
  
### **Use Case**:
- Companies use Okta for secure employee logins. Employees authenticate once and can access multiple internal applications seamlessly with SSO.

---

## **6. Keycloak**
### **What is Keycloak?**
- **Keycloak** is an open-source alternative to Okta. It provides **SSO**, **OAuth2**, **OIDC**, and **MFA** but can be self-hosted and managed by organizations.
- **Keycloak’s features**:
  - User authentication.
  - SSO and identity brokering (using third-party identity providers like Google).
  - Fine-grained user access control.
  - Integration with multiple protocols (OAuth2, OIDC, SAML).

### **Use Case**:
- A company using Keycloak can host it internally to manage employee login systems, offering both SSO and multi-factor authentication.

---

## **7. Common Authentication Scenarios**
### **OAuth2 with OIDC (Login via Google)**
- An app allows users to sign in using Google. The app requests authorization (OAuth2) and receives both an access token and an ID token (OIDC), confirming who the user is.

### **MFA with OTP (Banking)**
- A banking app requires you to enter a password (knowledge factor), followed by an OTP sent to your phone (possession factor) to verify identity before accessing the account.

---

## **Key Differences Summarized**:
| Concept       | Purpose                                   | Key Feature                                                                 |
|---------------|-------------------------------------------|-----------------------------------------------------------------------------|
| **OAuth2**    | Authorization                             | Grants access to resources via access tokens, without exposing passwords.   |
| **OIDC**      | Authentication + Authorization            | Adds identity verification to OAuth2 via ID tokens.                         |
| **SSO**       | Seamless login across multiple apps       | Users log in once and access multiple apps linked to the same SSO system.   |
| **MFA**       | Enhanced security through multiple factors| Verifies identity using 2+ factors (password, OTP, biometrics).             |
| **2FA**       | Two-factor authentication (a subset of MFA)| Uses exactly two factors to authenticate (e.g., password + OTP).            |
| **Okta**      | Cloud-based Identity management           | Provides SSO, OAuth2, OIDC, and MFA for enterprises.                        |
| **Keycloak**  | Open-source identity management           | Self-hosted alternative to Okta with SSO, OAuth2, OIDC, and MFA.            |

---

