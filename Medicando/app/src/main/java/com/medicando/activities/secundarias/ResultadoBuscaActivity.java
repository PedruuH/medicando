package com.medicando.activities.secundarias;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.medicando.R;
import com.medicando.activities.primarias.CestaComprasActivity;
import com.medicando.activities.terciarias.ResultadoFiltradoActivity;
import com.medicando.adapter.AdapterResultadoBusca;
import com.medicando.datamodel.BuscasDataModel;
import com.medicando.datamodel.LinkServerDataModel;
import com.medicando.datamodel.MedicamentosDataModel;
import com.medicando.datamodel.ResultadoBuscaDataModel;
import com.medicando.datamodel.VerificacoesWebService;
import com.medicando.helper.VerificaStatusInternet;
import com.medicando.model.ResultadoBusca;
import com.medicando.util.UtilMedicando;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultadoBuscaActivity extends AppCompatActivity {

    /*Declarando as Variaveis*/
    private RecyclerView recyclerFiltrarResultados;
    private Button btnFiltrarResultados;

    /*Adapter*/
    public AdapterResultadoBusca adapterResultadoBusca;

    /*Lista*/
    private List<ResultadoBusca> resultadoBuscaList = new ArrayList<>();

    private String nomeMedic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_busca);
        Toolbar toolbar = findViewById(R.id.toolbarPadrao);
        toolbar.setTitle("Resultados");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /*Recuperando as Variaveis do Layout*/
        recyclerFiltrarResultados = findViewById(R.id.recyclerResultadoBuscas);
        btnFiltrarResultados = findViewById(R.id.btnFiltrarResultados);

        /*Recupera String Passada*/
        Bundle dados = getIntent().getExtras();
        if(dados!=null){
             nomeMedic = dados.getString(BuscasDataModel.getDscBusca());
        }

        if(VerificaStatusInternet.isOnline(ResultadoBuscaActivity.this)){
            BuscarDadosResultadoBusca task =
                    new BuscarDadosResultadoBusca(ResultadoBuscaActivity.this, nomeMedic);
            task.execute();
        } else {
            UtilMedicando.showToastInfo(ResultadoBuscaActivity.this,"Não há conexão com a Internet.");
        }


    }

    public AdapterResultadoBusca.ResultadosBuscasOnClickListener resultadosBuscasOnClickListener(){
        return new AdapterResultadoBusca.ResultadosBuscasOnClickListener() {
            @Override
            public void resultadoBuscaOnClick(AdapterResultadoBusca.HolderResultado holder, int position) {
                ResultadoBusca resBusca = resultadoBuscaList.get(position);

                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(ResultadoBuscaActivity.this).toBundle();
                Intent it = new Intent(ResultadoBuscaActivity.this, ResultadoFiltradoActivity.class);
                it.putExtra("resBusca", resBusca);
                startActivity(it, bundle);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_padrao, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_padrao_cesta_compras:
                startActivity(new Intent(ResultadoBuscaActivity.this, CestaComprasActivity.class));
                break;
            default:
                break;
        }
        return true;
    }

    public class BuscarDadosResultadoBusca extends AsyncTask<String, String, String> {
        /*Declarando as variaveis da conexao HTTP*/
//        HttpURLConnection conn;
//        URL url = null;
//        Uri.Builder builder;

        /*Declarando as demais Variaveis*/
        @SuppressLint("StaticFieldLeak")
        Context context;
        String nomeMedicamento;
        @SuppressLint("StaticFieldLeak")

        private AlertDialog alerta;

        public BuscarDadosResultadoBusca(Context context, String nomeMedicamento
                                         //ProgressBar progressBar,
                                         ) {
//            this.builder = new Uri.Builder();
//            this.context = context;
//            this.nomeMedicamento = nomeMedicamento;
//            //this.progressBar = progressBar;
//
//            /*Montando os Parametros de Passagem
//             * TODO: Primeira linha é a verificacao, para aceitar apenas requições com esse parametr
//             * TODO: Demais linhas é os dados do usuario
//             */
//            builder.appendQueryParameter("verificar", VerificacoesWebService.getVerificaAppBuscarResultados());
//            builder.appendQueryParameter(MedicamentosDataModel.getNome(), nomeMedicamento);
        }

        /*Metodos*/
        @Override
        protected void onPreExecute() {
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View v = li.inflate(R.layout.layout_progress_dialog, null);
//
//            builder.setView(v);
//
//            alerta = builder.create();
//            alerta.show();
//            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//            lp.copyFrom(alerta.getWindow().getAttributes());
//            lp.width = 300;
//            lp.height = 300;
//            alerta.getWindow().setAttributes(lp);
        }

        @Override
        protected String doInBackground(String... strings) {
//            try {
//                url = new URL(UtilMedicando.URL_WEB_SERVICE + LinkServerDataModel.getAPIBuscarResultados());
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
//
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
//            //Log.d("WebService", "onPostExecute: " + result);
//            try{
//                JSONArray jsonArray = new JSONArray(result);
//                if(jsonArray.length() != 0){
//                    JSONObject object = jsonArray.getJSONObject(0);
//                    if(object.getBoolean("sucesso")){
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            object = jsonArray.getJSONObject(i);
//                            ResultadoBusca res = new ResultadoBusca();
//                            res.setMedicId(object.getInt(ResultadoBuscaDataModel.getIdMedicamento()));
//                            res.setMedicNome(object.getString(ResultadoBuscaDataModel.getNomeMedicamento()));
//
//                            res.setDescId(object.getInt(ResultadoBuscaDataModel.getIdDescricao()));
//                            res.setDescDes(object.getString(ResultadoBuscaDataModel.getDescDescricao()));
//                            res.setDescQuantidade(object.getString(ResultadoBuscaDataModel.getQuantidadeDescricao()));
//                            res.setDescReceita(object.getString(ResultadoBuscaDataModel.getReceitaDescricao()));
//
//                            res.setpPreco(object.getDouble(ResultadoBuscaDataModel.getPrecoPreco()));
//
//                            res.setFarmId(object.getInt(ResultadoBuscaDataModel.getIdFarmacia()));
//                            res.setFarmNome(object.getString(ResultadoBuscaDataModel.getNomeFarmacia()));
//
//                            resultadoBuscaList.add(res);
//                        }
//
//                        adapterResultadoBusca = new AdapterResultadoBusca(resultadoBuscaList, resultadosBuscasOnClickListener());
//
//                        /*Configura o RecyclerView*/
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                        recyclerFiltrarResultados.setLayoutManager(layoutManager);
//                        recyclerFiltrarResultados.setHasFixedSize(true);
//                        recyclerFiltrarResultados.setAdapter(adapterResultadoBusca);
//                    } else {
//                        UtilMedicando.showToastError(context,
//                                "Não houve sucesso, tente novamente!");
//                    }
//                } else {
//                    UtilMedicando.showToastError(context,
//                            "Nenhum registro encontrado no momento!");
//                }
//            } catch (JSONException e){
//                Log.d("WebService", "Erro JsonException: " + e.getMessage());
//            } finally {
//                if (alerta != null && alerta.isShowing())
//                    alerta.dismiss();
//            }

            ResultadoBusca res = new ResultadoBusca();
            res.setMedicId(1);
            res.setMedicNome("Omeprazol 20mg");
            res.setDescId(1);
            res.setDescDes("Omeprazol é um medicamento indicado para o tratamento de pacientes onde o estômago produza uma quantidade excessiva de ácido.");
            res.setDescQuantidade("50");
            res.setDescReceita("N");
            res.setpPreco(7.99);
            res.setFarmId(1);
            res.setFarmNome("Pague Menos");

            resultadoBuscaList.add(res);

            res = new ResultadoBusca();
            res.setMedicId(2);
            res.setMedicNome("Dipirona Sódica 500mg");
            res.setDescId(2);
            res.setDescDes("A Dipirona Sódica é um analgésico e antitérmico utilizado em enfermidades que tenham dor e febre como sintomas.");
            res.setDescQuantidade("75");
            res.setDescReceita("N");
            res.setpPreco(5.55);
            res.setFarmId(2);
            res.setFarmNome("Drogasil");

            resultadoBuscaList.add(res);

            res = new ResultadoBusca();
            res.setMedicId(3);
            res.setMedicNome("Analgésico Tylenol® 750mg");
            res.setDescId(3);
            res.setDescDes("O TYLENOL® 750mg é indicado para o alívio temporário da dor de leve a moderada, como aquelas associadas a gripes, resfriados, artrites e cólicas, e para a redução da febre.");
            res.setDescQuantidade("60");
            res.setDescReceita("N");
            res.setpPreco(32.00);
            res.setFarmId(3);
            res.setFarmNome("Drogalider");

            resultadoBuscaList.add(res);

            adapterResultadoBusca = new AdapterResultadoBusca(resultadoBuscaList, resultadosBuscasOnClickListener());

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerFiltrarResultados.setLayoutManager(layoutManager);
            recyclerFiltrarResultados.setHasFixedSize(true);
            recyclerFiltrarResultados.setAdapter(adapterResultadoBusca);

            if (alerta != null && alerta.isShowing())
                alerta.dismiss();
        }
    }
}
