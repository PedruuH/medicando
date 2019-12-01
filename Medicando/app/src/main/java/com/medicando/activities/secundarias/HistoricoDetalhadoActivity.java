package com.medicando.activities.secundarias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.medicando.R;
import com.medicando.adapter.AdapterDetalhado;
import com.medicando.adapter.AdapterHistorico;
import com.medicando.model.Pedidos;
import com.medicando.model.Status;

import java.util.ArrayList;
import java.util.List;

public class HistoricoDetalhadoActivity extends AppCompatActivity {

    private TextView textPedido, textLocal, textPagamento, textTotal;

    private RecyclerView recyclerStatus;
    private AdapterDetalhado adapterDetalhado;

    private List<Status> listaStatus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_detalhado);

        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbarPadrao);
        toolbar.setTitle("Pedido: " + "34941215");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        recyclerStatus = findViewById(R.id.recyclerStatusEntrega);

        textPedido = findViewById(R.id.detailNumeroPedido);
        textLocal = findViewById(R.id.detailEntregaPedido);
        textPagamento = findViewById(R.id.detailFormaPagamentoPedido);
        textTotal = findViewById(R.id.detailTotalPedido);

        /*Atualizo os valores*/
        textPedido.setText("Nº Pedido: 34941215");
        textLocal.setText("Endereço: Rua Teste, 100 | Teste");
        textPagamento.setText("Cartão: Terminado em 4163");
        textTotal.setText("Total: R$ 49,90");

        this.montaStatus();

        adapterDetalhado = new AdapterDetalhado(listaStatus);

        /*Configura o RecyclerView*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HistoricoDetalhadoActivity.this);
        recyclerStatus.setLayoutManager(layoutManager);
        recyclerStatus.setHasFixedSize(true);
        recyclerStatus.setAdapter(adapterDetalhado);
    }

    public void montaStatus() {

        Status statusAtual = new Status();
        statusAtual.setStatusFase("Pedido Efetuado (30/11/2019 - 12:15:06)");
        statusAtual.setStatusID(R.drawable.icon_detail_complet);
        listaStatus.add(statusAtual);

        statusAtual = new Status();
        statusAtual.setStatusFase("Autorizado (30/11/2019 - 12:22:58)");
        statusAtual.setStatusID(R.drawable.icon_detail_complet);
        listaStatus.add(statusAtual);

        statusAtual = new Status();
        statusAtual.setStatusFase("Em Transporte (30/11/2019 - 12:48:41)");
        statusAtual.setStatusID(R.drawable.icon_detail_complet);
        listaStatus.add(statusAtual);

        statusAtual = new Status();
        statusAtual.setStatusFase("Entregue");
        statusAtual.setStatusID(R.drawable.icon_detail_outline);
        listaStatus.add(statusAtual);


    }

    //Volta para a activity anterior
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
