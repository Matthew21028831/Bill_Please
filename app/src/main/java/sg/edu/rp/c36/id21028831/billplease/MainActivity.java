package sg.edu.rp.c36.id21028831.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText amt;
    EditText pax;
    EditText discount;
    TextView totalCost;
    TextView paymentMethod;
    CheckBox servicecharge;
    CheckBox GST;
    Button split;
    Button Reset;
    RadioGroup payment;
    RadioButton cash;
    RadioButton paynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amt=findViewById(R.id.amount);
        pax=findViewById(R.id.num_pax);
        discount=findViewById(R.id.discount);
        servicecharge=findViewById(R.id.seriveCharge);
        GST=findViewById(R.id.GST);
        split=findViewById(R.id.split);
        Reset=findViewById(R.id.Reset);
        payment=findViewById(R.id.payment);
        paymentMethod=findViewById(R.id.paymentMethod);
        totalCost=findViewById(R.id.totalCost);
        cash=findViewById(R.id.cash);
        paynow=findViewById(R.id.payNow);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                split.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        double enterAmount=Double.parseDouble(amt.getText().toString());
                        double numOfPax=Double.parseDouble(pax.getText().toString());
                        double enterDiscount=Double.parseDouble(discount.getText().toString());

                        double gst=1.0;
                        if (servicecharge.isChecked()){
                            gst *= 1.1;
                        }else if(GST.isChecked()){
                            gst *= 1.07;
                        }else if(servicecharge.isChecked() && GST.isChecked()){
                            gst *= 2.17;
                        }

                        double beforeSplitting=enterAmount*gst;
                        double totalBill= beforeSplitting*(1-(enterDiscount/100));

                        double splitBill=totalBill/numOfPax;

                        totalCost.setText("Total Bill: $" + totalBill);

                        int checkRadioId = payment.getCheckedRadioButtonId();
                        if (checkRadioId== R.id.cash){
                            paymentMethod.setText("Each pays: $" + splitBill + " in cash");
                        }else if (checkRadioId==R.id.payNow){
                            paymentMethod.setText("Each pays: $" + splitBill + " via paynow to 96745372");
                        }

                    }
                });

                Reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        amt.getText().clear();
                        pax.getText().clear();
                        discount.getText().clear();

                        if (servicecharge.isChecked()) {
                            servicecharge.setChecked(false);
                        }
                        if (GST.isChecked()) {
                            GST.setChecked(false);
                        }

                        totalCost.setText("");
                        paymentMethod.setText("");

                        if (cash.isChecked()) {
                            cash.setChecked(false);
                        }
                        if (paynow.isChecked()) {
                            paynow.setChecked(false);
                        }
                    }
                });

                
            }
        });

    }
}