package com.auth0.client.mgmt;

import com.auth0.json.mgmt.tickets.EmailVerificationTicket;
import com.auth0.json.mgmt.tickets.PasswordChangeTicket;
import com.auth0.net.CustomRequest;
import com.auth0.net.Request;
import com.auth0.utils.Asserts;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Class that provides an implementation of the Tickets methods of the Management API as defined in https://auth0.com/docs/api/management/v2#!/Tickets
 * <p>
 * This class is not thread-safe.
 *
 * @see ManagementAPI
 */
@SuppressWarnings("WeakerAccess")
public class TicketsEntity extends BaseManagementEntity {

    public TicketsEntity(OkHttpClient client, HttpUrl baseUrl, String apiToken) {
        super(client, baseUrl, apiToken);
    }

    /**
     * Create an Email Verification Ticket. A token with scope create:user_tickets is needed.
     * See https://auth0.com/docs/api/management/v2#!/Tickets/post_email_verification
     *
     * @param emailVerificationTicket the email verification ticket data to set.
     * @return a Request to execute.
     */
    public Request<EmailVerificationTicket> requestEmailVerification(EmailVerificationTicket emailVerificationTicket) {
        Asserts.assertNotNull(emailVerificationTicket, "email verification ticket");

        String url = baseUrl
                .newBuilder()
                .addPathSegments("api/v2/tickets/email-verification")
                .build()
                .toString();

        CustomRequest<EmailVerificationTicket> request = new CustomRequest<>(client, url, "POST", new TypeReference<EmailVerificationTicket>() {
        });
        request.addHeader("Authorization", "Bearer " + apiToken);
        request.setBody(emailVerificationTicket);
        return request;
    }

    /**
     * Create a Password Change Ticket. A token with scope create:user_tickets is needed.
     * See https://auth0.com/docs/api/management/v2#!/Tickets/post_password_change
     *
     * @param passwordChangeTicket the password change ticket data to set.
     * @return a Request to execute.
     */
    public Request<PasswordChangeTicket> requestPasswordChange(PasswordChangeTicket passwordChangeTicket) {
        Asserts.assertNotNull(passwordChangeTicket, "password change ticket");

        String url = baseUrl
                .newBuilder()
                .addPathSegments("api/v2/tickets/password-change")
                .build()
                .toString();

        CustomRequest<PasswordChangeTicket> request = new CustomRequest<>(client, url, "POST", new TypeReference<PasswordChangeTicket>() {
        });
        request.addHeader("Authorization", "Bearer " + apiToken);
        request.setBody(passwordChangeTicket);
        return request;
    }
}
