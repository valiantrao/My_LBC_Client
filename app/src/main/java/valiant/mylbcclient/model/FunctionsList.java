package valiant.mylbcclient.model;


public class FunctionsList {

    private String function_name, ad_id, ad_ids_to_compare, amount, new_trade_first_msg;
    private int isAbove;

    public FunctionsList(String function_name, String ad_id, String ad_ids_to_compare, String amount, String new_trade_first_msg, int isAbove) {
        this.function_name = function_name;
        this.ad_id = ad_id;
        this.ad_ids_to_compare = ad_ids_to_compare;
        this.amount = amount;
        this.new_trade_first_msg = new_trade_first_msg;
        this.isAbove = isAbove;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_ids_to_compare() {
        return ad_ids_to_compare;
    }

    public void setAd_ids_to_compare(String ad_ids_to_compare) {
        this.ad_ids_to_compare = ad_ids_to_compare;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNew_trade_first_msg() {
        return new_trade_first_msg;
    }

    public void setNew_trade_first_msg(String new_trade_first_msg) {
        this.new_trade_first_msg = new_trade_first_msg;
    }

    public int isAbove() {
        return isAbove;
    }

    public void setAbove(int above) {
        isAbove = above;
    }
}
