package receiver.caelum.com.br.cadastrocaelum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import cadastro.caelum.com.br.cadastrocaelum.AlunoDAO;
import cadastro.caelum.com.br.cadastrocaelum.DBHelper;

/**
 * Created by IT-CPS on 24/06/2015.
 */
public class SMSReceiver extends BroadcastReceiver{
    private AlunoDAO alunoDAO;
    private DBHelper dbHelper;
    @Override
    public void onReceive(Context context, Intent intent) {
        dbHelper = new DBHelper(context);
        alunoDAO = new AlunoDAO(dbHelper);

        Bundle extras = intent.getExtras();
        Object[] messages = (Object[]) extras.get("pdus");
        byte[] message = (byte[]) messages[0];

        SmsMessage sms = SmsMessage.createFromPdu(message);

        if(alunoDAO.isAluno(sms.getOriginatingAddress())){
            Toast.makeText(context, "Chegou um SMS de " + sms.getOriginatingAddress(), Toast.LENGTH_LONG).show();
            //MediaPlayer mp = MediaPlayer.create(context, R.raw.msg);
            //mp.start();
        }
    }
}
