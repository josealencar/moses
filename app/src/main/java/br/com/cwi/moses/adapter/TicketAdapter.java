package br.com.cwi.moses.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.cwi.moses.R;
import br.com.cwi.moses.model.SituacaoTicket;
import br.com.cwi.moses.model.Ticket;
import br.com.cwi.moses.model.TipoTicket;

import static br.com.cwi.moses.model.TipoTicket.DUVIDA;

/**
 * Created by Murillo on 31/05/2017.
 */

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_list_txt_data;
        private TextView item_list_txt_titulo;
        private TextView item_list_txt_situacao;

        private LinearLayout item_list_border;

        public ViewHolder(View itemView) {
            super(itemView);

            this.item_list_border = (LinearLayout) itemView.findViewById(R.id.item_list_border);
            this.item_list_txt_data = (TextView) itemView.findViewById(R.id.item_list_txt_data);
            this.item_list_txt_titulo = (TextView) itemView.findViewById(R.id.item_list_txt_titulo);
            this.item_list_txt_situacao = (TextView) itemView.findViewById(R.id.item_list_txt_situacao);
        }
    }

    private List<Ticket> listTicket;

    public TicketAdapter(List<Ticket> listTicket) {
        this.listTicket = listTicket;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_list_ticket, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ticket ticket = this.listTicket.get(position);

        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM HH:mm");
        String data = sdfDate.format(ticket.getDataAberturaTicket());

        String titulo = ticket.getTitulo();
        String situacao = ticket.getSituacaoTicket().name();

        holder.item_list_txt_data.setText(data);
        holder.item_list_txt_titulo.setText(titulo);
        holder.item_list_txt_situacao.setText(situacao);

        holder.item_list_border.setBackgroundResource(this.getColorCard(ticket.getTipoTicket()));
    }

    private int getColorCard(TipoTicket tipoTicket) {
        switch (tipoTicket) {
            case DUVIDA:
                return R.color.walmart_orange;
            case SUGESTAO:
                return R.color.walmart_green;
            case PROBLEMA:
                return R.color.walmart_red;
            default:
                return R.color.walmart_orange;
        }
    }

    @Override
    public int getItemCount() {
        return this.listTicket.size();
    }
}
