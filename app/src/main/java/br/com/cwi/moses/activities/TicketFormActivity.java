package br.com.cwi.moses.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.com.cwi.moses.R;
import br.com.cwi.moses.models.TipoTicket;
import br.com.cwi.moses.services.FormValidatorService;
import br.com.cwi.moses.services.TicketService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TicketFormActivity extends AppCompatActivity {

    private FormValidatorService formValidatorService = new FormValidatorService();
    private TicketService ticketService = TicketService.getInstance();

    private TipoTicket tipoTicket;

    @BindView(R.id.ticket_form_txt_titulo)
    EditText ticket_form_txt_titulo;

    @BindView(R.id.ticket_form_txt_descricao)
    EditText ticket_form_txt_descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_form);

        ButterKnife.bind(this);

        this.initComponents();
        setTitle(this.buildPageTitle());
    }

    private void initComponents() {
        this.tipoTicket = (TipoTicket) getIntent().getSerializableExtra("TICKET_TIPO");
    }

    public void salvarTicket(View view) {
        if (this.isTituloOk() && this.isDescricaoOk()) {
            String titulo = ticket_form_txt_titulo.getText().toString();
            String descricao = ticket_form_txt_descricao.getText().toString();

            this.ticketService.addTicketFromForm(titulo, descricao, this.tipoTicket);
            this.goToListTickets();
        }
    }

    private void goToListTickets() {
        Intent intent = new Intent(this, TicketListActivity.class);
        startActivity(intent);
    }

    private boolean isTituloOk() {
        formValidatorService.cleanFieldErrors(ticket_form_txt_titulo);
        return formValidatorService.isntFieldEmpty(ticket_form_txt_titulo);
    }

    private boolean isDescricaoOk() {
        formValidatorService.cleanFieldErrors(ticket_form_txt_descricao);
        return formValidatorService.isntFieldEmpty(ticket_form_txt_descricao);
    }

    private String buildPageTitle() {
        return "Novo Ticket - " + this.tipoTicket.toString();
    }
}
