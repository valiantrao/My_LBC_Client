package valiant.mylbcclient.model;


public class Function {

    private String function_name, ad_id, ad_ids_to_compare, amount, new_trade_first_msg;
    private boolean isAbove;

    public Function(String function_name, String ad_id, String ad_ids_to_compare, String amount, String new_trade_first_msg, boolean isAbove) {
        this.function_name = function_name;
        this.ad_id = ad_id;
        this.ad_ids_to_compare = ad_ids_to_compare;
        this.amount = amount;
        this.new_trade_first_msg = new_trade_first_msg;
        this.isAbove = isAbove;
    }
}
