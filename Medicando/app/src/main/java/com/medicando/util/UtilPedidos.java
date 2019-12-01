package com.medicando.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.medicando.activities.primarias.MainActivity;
import com.medicando.datamodel.LinkServerDataModel;
import com.medicando.datamodel.PedidosDataModel;
import com.medicando.model.Pedidos;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class UtilPedidos {
    @SuppressLint("StaticFieldLeak")
    public static class confirmaSolicitacaoPedidoAsyncTask extends AsyncTask<String, String, String> {
        /*Declarando as variaveis da conexao HTTP*/
//        HttpURLConnection conn;
//        URL url = null;
//        Uri.Builder builder;

        /*Declarando o Contexto*/
        Context context;

        /*Pedido que vai ser enviado*/
        Pedidos pedido;

        ProgressDialog progressDialog;



        public confirmaSolicitacaoPedidoAsyncTask(Context context, Pedidos pedido) {
//            this.builder = new Uri.Builder();
            this.context = context;
            this.pedido = pedido;
//
//            /*Montando os Parametros de Passagem
//             * TODO: Primeira linha é a verificacao, para aceitar apenas requições com esse parametr
//             * TODO: Demais linhas é os dados do usuario
//             */
//
//            builder.appendQueryParameter("verificar", "app_inserir_pedido");
//            builder.appendQueryParameter(PedidosDataModel.getId(), pedido.getPedId());
//            builder.appendQueryParameter(PedidosDataModel.getIdSituacao(), String.valueOf(pedido.getPedIdSituacao()));
//            builder.appendQueryParameter(PedidosDataModel.getIdUser(), String.valueOf(pedido.getPedIdUser()));
//            builder.appendQueryParameter(PedidosDataModel.getIdUserEndereco(), String.valueOf(pedido.getPedIdUserEndereco()));
//            builder.appendQueryParameter(PedidosDataModel.getIdUserBancarios(), String.valueOf(pedido.getPedIdUserBancarios()));
//            builder.appendQueryParameter(PedidosDataModel.getIdFarmacia(), String.valueOf(pedido.getPedIdFarmacia()));
//            builder.appendQueryParameter(PedidosDataModel.getIdMedicamento(), String.valueOf(pedido.getPedIdMedicamento()));
//            builder.appendQueryParameter(PedidosDataModel.getIdDescricao(), String.valueOf(pedido.getPedIdDesc()));
//            builder.appendQueryParameter(PedidosDataModel.getQtdadeMedicamentos(), String.valueOf(pedido.getPedQtdadeMedicamento()));
//            builder.appendQueryParameter(PedidosDataModel.getValorTotal(), String.valueOf(pedido.getPedValorTotal()));
//            builder.appendQueryParameter(PedidosDataModel.getIdFormaPagamento(), String.valueOf(pedido.getPedIdFormaPagamento()));
//            builder.appendQueryParameter(PedidosDataModel.getQtdadeParcelas(), String.valueOf(pedido.getPedQtdadeParcelas()));
//            builder.appendQueryParameter(PedidosDataModel.getLinkReceita(), pedido.getPedLinkReceita());
//            builder.appendQueryParameter(PedidosDataModel.getTaxaEntrega(), String.valueOf(pedido.getPedTaxaEntrega()));
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);

            progressDialog.setMessage("Efetuando pedido, por favor espere...");
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {
//            try {
//                //url = new URL(UtilMedicando.URL_WEB_SERVICE + "APICadastrarUsuarioTeste.php");
//                url = new URL(UtilMedicando.URL_WEB_SERVICE + LinkServerDataModel.getAPICadastrarPedido());
//            } catch (MalformedURLException e) {
//                UtilMedicando.showToastShort(context, "Houve erro! Servidor incorreto.");
//            } catch (Exception e) {
//                UtilMedicando.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
//            }
//
//            try {
//                conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(UtilMedicando.READ_TIMEOUT);
//                conn.setConnectTimeout(UtilMedicando.CONNECTION_TIMEOUT);
//                conn.setRequestMethod("POST");
//                conn.setRequestProperty("charset", "utf-8");
//
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                conn.connect();
//
//                String query = builder.build().getEncodedQuery();
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
//                writer.write(Objects.requireNonNull(query));
//                writer.flush();
//                writer.close();
//                os.close();
//                conn.connect();
//
//            } catch (IOException erro) {
//                UtilMedicando.showToastShort(context, "Houve erro! Tente novamente mais tarde.");
//            }
//
//            try {
//                int response_code = conn.getResponseCode();
//                if (response_code == HttpURLConnection.HTTP_OK) {
//                    InputStream input = conn.getInputStream();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//                    StringBuilder result = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        result.append(line);
//                    }
//                    return (result.toString());
//                } else {
//                    return ("Erro de conexão");
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                return e.toString();
//            } finally {
//                conn.disconnect();
//            }
            return("OK");
        }

        @Override
        protected void onPostExecute(String result) {
//            Log.d("Service", "onPostExecute: " + result);
//            try {
//                JSONObject obj = new JSONObject(result);
//                if(obj.getBoolean("sucesso")){
//                    if(progressDialog != null && progressDialog.isShowing()){
//                        progressDialog.dismiss();
//                        context.startActivity(new Intent(context, MainActivity.class));
//                        UtilMedicando.showToastSucessLong(context, "Pedido efetuado com sucesso! Aguarde a Entrega.");
//                    }
//                } else {
//                    progressDialog.dismiss();
//                    UtilMedicando.showToastError(context, "Estamos com instabilidade! Tente novamente em Breve.");
//                }
//            } catch (Exception e){
//                progressDialog.dismiss();
//                UtilMedicando.showToastError(context, "Estamos com instabilidade! Tente novamente em Breve.");
//            }

            if(progressDialog != null && progressDialog.isShowing()){
                progressDialog.dismiss();
                context.startActivity(new Intent(context, MainActivity.class));
                UtilMedicando.showToastSucessLong(context, "Pedido efetuado com sucesso! Aguarde a Entrega.");
            }
        }
    }
}
