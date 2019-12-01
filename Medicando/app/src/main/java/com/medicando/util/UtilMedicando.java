package com.medicando.util;

import android.content.Context;
import android.widget.Toast;

import com.medicando.design.ToastCustom;

public class UtilMedicando {

    /*Variaveis para conexão localhost
    public static final String URL_WEB_SERVICE = "http://192.168.0.24/ws_medicando/";
    public static final String URL_WEB_SERVICE = "http://192.168.56.1/ws_medicando/";*/
    public static final String URL_WEB_SERVICE = "http://10.14.98.203/medicando-web/public/api/";

    /*Produção*/
//    public static final String URL_WEB_SERVICE = "http://medicando.com/webservice/";

    public static final int CONNECTION_TIMEOUT = 7000;
    public static final int READ_TIMEOUT = 12000;
    private static String URL_WEB_SERVICE_VIACEP;

    public static String getUrlWebServiceViacep() {
        return URL_WEB_SERVICE_VIACEP;
    }

    public static void setUrlWebServiceViacep(String cep) {
        URL_WEB_SERVICE_VIACEP = "https://viacep.com.br/ws/" + cep + "/json/";
    }


    /*Apresenta os Toasts*/
    public static void showToastShort(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToastSucess(Context context, String msg){
        ToastCustom.makeText(context, msg, ToastCustom.LENGTH_SHORT, ToastCustom.SUCCESS).show();
    }

    public static void showToastSucessLong(Context context, String msg){
        ToastCustom.makeText(context, msg, ToastCustom.LENGTH_LONG, ToastCustom.SUCCESS).show();
    }

    public static void showToastError(Context context, String msg){
        ToastCustom.makeText(context, msg, ToastCustom.LENGTH_SHORT, ToastCustom.ERROR).show();
    }

    public static void showToastInfo(Context context, String msg){
        ToastCustom.makeText(context, msg, ToastCustom.LENGTH_SHORT, ToastCustom.INFO).show();
    }

}
