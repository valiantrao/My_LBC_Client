package valiant.mylbcclient.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import org.apache.commons.codec.binary.Hex;

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

    public static final String LBC_KEY = "112171de0d8348edce118a65b449cc2b";
    public static final String LBC_SECRET = "5b003b95c73f6374672ff129c792f0a7d7e512b3e75d4e9af549c1457d22757a";

    public static final String USERNAME = "username";
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




    public static String getCurrentTimeStamp(){
        try {

            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

            return dateFormat.format(new Date());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String encode(String msg, String key) {
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            Log.e("Config::::getinstance ", e.getMessage());
        }
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        try {
            sha256_HMAC.init(secret_key);
        } catch (InvalidKeyException e) {
            Log.e(":COnfig init ", e.getMessage());
        }

        return Hex.encodeHexString(sha256_HMAC.doFinal(msg.getBytes(StandardCharsets.UTF_8)));
    }


}
