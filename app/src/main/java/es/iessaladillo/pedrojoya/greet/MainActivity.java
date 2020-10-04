package es.iessaladillo.pedrojoya.greet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity{

    TextView greet,contad;
    EditText txtName,txtSurname;
    String name,Surname;
    Button boton;
    RadioButton mr,ms,mrs;
    CheckBox politely;
    ProgressBar barra;
    ImageView face;
    Switch premium;
    int contador=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setviews();

    }


    private void setviews(){



        // Recogemos la información del botón por ID

        boton = ActivityCompat.requireViewById(this,R.id.btnGreet);
        contad= ActivityCompat.requireViewById(this,R.id.contador);

        //Indicamos al botón que acción debe hacer al ser pulsado

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Cuando el botón es pulsado se llama a la función que hace todas las acciones
                getandsetgreet();

                contador++;
                contad.setText( contador + "/10");
                barra.setProgress(contador);
                if (contador>=10 ){

                    aviso();

                    contad.setVisibility(4);

                }

                if(premium.isChecked() == true){


                    getandsetgreet();

                }

            }
        });


        premium = ActivityCompat.requireViewById(this,R.id.lblSwitch);
        barra = ActivityCompat.requireViewById(this, R.id.progress_bar);


        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (premium.isChecked() == true){

                    barra.setVisibility(4);
                    contad.setVisibility(4);
                }
                else{
                    contador = 0;
                    contad.setText( contador + "/10");

                    barra.setVisibility(0);
                    contad.setVisibility(0);
                }
            }
        });

        emoticono();


    }


    private void emoticono(){

        // Recogemos información de los Radio Button

        mr = ActivityCompat.requireViewById(this,R.id.rdbMr);
        ms = ActivityCompat.requireViewById(this,R.id.rdbMs);
        mrs = ActivityCompat.requireViewById(this,R.id.rdbMrs);

        face = ActivityCompat.requireViewById(this,R.id.imgFace);

        // Se cambia la imagen en función del RadioCheck que esté pulsado

        mr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                face.setImageResource(R.drawable.ic_mr);
            }
        });

        ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                face.setImageResource(R.drawable.ic_mrs);
            }
        });

        mrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                face.setImageResource(R.drawable.ic_ms);
            }
        });


    }





    private void getandsetgreet(){

        //Obtenemos el nombre y apellid introducido por el usuario por ID

        txtName = ActivityCompat.requireViewById(this,R.id.editNombre);
        txtSurname = ActivityCompat.requireViewById(this,R.id.editApellido);


        // Transformamos el EditText en String para poder traabjar con el
        name = txtName.getText().toString();
        Surname = txtSurname.getText().toString();


        if(name.length()==0 || Surname.length()==0) {

            error();

        }

        else {

            // Recogemos la información del CheckBox por ID
            politely = ActivityCompat.requireViewById(this, R.id.chkPolitely);


            // Comprobamos si el boton de saludo educado está marcado o no

            if (politely.isChecked() == true) {

                polite(name, Surname);

            } else {

                nonpolite(name, Surname);

            }
        }
    }



    private void error(){

        greet = findViewById(R.id.main_saludo);

        greet.setText("Tienes que introducir tu nombre y apellido!");


    }


    private void aviso(){

        greet = findViewById(R.id.main_saludo);
        greet.setText("Compra la versión premium para mas saludos.");

    }

    private void polite(String name ,String Surname){

        greet = findViewById(R.id.main_saludo);

        if(mr.isChecked() == true){

            greet.setText("Good morning Mr. " + name + " " + Surname +"\n" + "Please to meet you");

        }

        if(ms.isChecked() == true){

            greet.setText("Good morning Mrs. " + name + " " + Surname +"\n" + "Please to meet you");

        }

        if(mrs.isChecked() == true){

            greet.setText("Good morning Ms. " + name + " " + Surname +"\n" + "Please to meet you");

        }

    }




    private void nonpolite(String name, String Surname){

        greet = findViewById(R.id.main_saludo);

        if(mr.isChecked() == true){

            greet.setText("Hello Mr. " + name + " " + Surname +". Whats up?");

        }

        if(ms.isChecked() == true){

            greet.setText("Hello Mrs." + name + " " + Surname +". Whats up?");

        }

        if(mrs.isChecked() == true){

            greet.setText("Hello Ms." + name + " " + Surname +". Whats up?");

        }
    }
}




