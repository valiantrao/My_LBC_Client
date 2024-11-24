package valiant.mylbcclient.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import org.apache.commons.codec.binary.Hex;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Config {
    public static final String LBC_API = "lbc_api";
    public static final String LBC_KEY = "";
    public static final String LBC_SECRET = "";

    public static final String data = "data";
    public static final String ad_list = "ad_list";
    public static final String profile = "profile";
    public static final String username = "username";
    public static final String url = "url";
    public static final String feedback_score = "feedback_score";
    public static final String feedback_count = "feedback_count";
    public static final String feedbacks_unconfirmed_count = "feedbacks_unconfirmed_count";
    public static final String trading_partners_count = "trading_partners_count";
    public static final String trusted_count = "trusted_count";
    public static final String blocked_count = "blocked_count";
    public static final String trade_volume_text = "trade_volume_text";
    public static final String confirmed_trade_count_text = "confirmed_trade_count_text";
    public static final String age_text = "age_text";
    public static final String created_at = "created_at";
    public static final String identity_verified_at = "identity_verified_at";
    public static final String real_name_verifications_trusted = "real_name_verifications_trusted";
    public static final String real_name_verifications_untrusted = "real_name_verifications_untrusted";
    public static final String real_name_verifications_rejected = "real_name_verifications_rejected";
    public static final String trade_count = "trade_count";
    public static final String name = "name";
    public static final String last_online = "last_online";
    public static final String visible = "visible";
    public static final String location_string = "location_string";
    public static final String countrycode = "countrycode";
    public static final String city = "city";
    public static final String trade_type = "trade_type";
    public static final String online_provider = "online_provider";
    public static final String first_time_limit_btc = "first_time_limit_btc";
    public static final String volume_coefficient_btc = "volume_coefficient_btc";
    public static final String sms_verification_required = "sms_verification_required";
    public static final String currency = "currency";
    public static final String min_amount = "min_amount";
    public static final String max_amount = "max_amount";
    public static final String max_amount_available = "max_amount_available";
    public static final String ad_id = "ad_id";
    public static final String temp_price_usd = "temp_price_usd";
    public static final String temp_price = "temp_price";
    public static final String require_feedback_score = "require_feedback_score";
    public static final String require_trade_volume = "require_trade_volume";
    public static final String msg = "msg";
    public static final String bank_name = "bank_name";
    public static final String atm_model = "atm_model";
    public static final String require_trusted_by_advertiser = "require_trusted_by_advertiser";
    public static final String require_identification = "require_identification";
    public static final String payment_window_minutes = "payment_window_minutes";
    public static final String limit_to_fiat_amounts = "limit_to_fiat_amounts";
    public static final String actions = "actions";
    public static final String public_view = "public_view";







    public static String getCurrentTimeStamp(){
        try {

            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss+00:00");

            return dateFormat.format(new Date());
        } catch (Exception e) {
            Log.e("Time error::", String.valueOf(e));
            return null;
        }
    }
    public static String getTime(String time){
        try {

            DateTime last_time = new DateTime(time);
            LocalDateTime localDateTime = last_time.toLocalDateTime();
            LocalDateTime current_time = LocalDateTime.now();



            return last_time.toString();
           /* String timeZone = time.substring(time.indexOf("+") + 1);

            DateTime dateTime = new DateTime(time.replaceAll(timeZone,"").replaceAll("\\+", ""));
            DateTime current_dateTime = DateTime.now(DateTimeZone.forID("+" + timeZone));

            int left_year = current_dateTime.getYear() - dateTime.getYear();
            int left_month = current_dateTime.getMonthOfYear() - dateTime.getMonthOfYear();

            DateTime left_date = current_dateTime.minus(dateTime.getMillis());
            long left_milli = current_dateTime.getMillis() - dateTime.getMillis();
            int sec = (int) (left_milli/1000);
            int min = sec/60;

            return "long " + String.valueOf(left_milli) + " sec " + sec;//DateTiem.now(DateTimeZone.forID("+00:00")).toString();*/
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


}
