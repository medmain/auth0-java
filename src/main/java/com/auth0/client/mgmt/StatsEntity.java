package com.auth0.client.mgmt;

import com.auth0.json.mgmt.DailyStats;
import com.auth0.net.CustomRequest;
import com.auth0.net.Request;
import com.auth0.utils.Asserts;
import com.fasterxml.jackson.core.type.TypeReference;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class that provides an implementation of the Stats methods of the Management API as defined in https://auth0.com/docs/api/management/v2#!/Stats
 * <p>
 * This class is not thread-safe.
 *
 * @see ManagementAPI
 */
@SuppressWarnings("WeakerAccess")
public class StatsEntity extends BaseManagementEntity {

    public StatsEntity(OkHttpClient client, HttpUrl baseUrl, String apiToken) {
        super(client, baseUrl, apiToken);
    }

    /**
     * Request the Active Users Count (logged in during the last 30 days). A token with scope read:stats is needed.
     * See https://auth0.com/docs/api/management/v2#!/Stats/get_active_users
     *
     * @return a Request to execute.
     */
    public Request<Integer> getActiveUsersCount() {
        String url = baseUrl
                .newBuilder()
                .addPathSegments("api/v2/stats/active-users")
                .build()
                .toString();

        CustomRequest<Integer> request = new CustomRequest<>(client, url, "GET", new TypeReference<Integer>() {
        });
        request.addHeader("Authorization", "Bearer " + apiToken);
        return request;
    }

    /**
     * Request the Daily Stats for a given period. A token with scope read:stats is needed.
     * See https://auth0.com/docs/api/management/v2#!/Stats/get_daily
     *
     * @param from the first day of the period (inclusive). Time is not taken into account.
     * @param to   the last day of the period (inclusive). Time is not taken into account.
     * @return a Request to execute.
     */
    public Request<List<DailyStats>> getDailyStats(Date from, Date to) {
        Asserts.assertNotNull(from, "date from");
        Asserts.assertNotNull(to, "date to");

        String dateFrom = formatDate(from);
        String dateTo = formatDate(to);
        String url = baseUrl
                .newBuilder()
                .addPathSegments("api/v2/stats/daily")
                .addQueryParameter("from", dateFrom)
                .addQueryParameter("to", dateTo)
                .build()
                .toString();

        CustomRequest<List<DailyStats>> request = new CustomRequest<>(client, url, "GET", new TypeReference<List<DailyStats>>() {
        });
        request.addHeader("Authorization", "Bearer " + apiToken);
        return request;
    }

    protected String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }
}
