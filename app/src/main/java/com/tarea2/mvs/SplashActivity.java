package com.tarea2.mvs;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

/**
 * SplashActivity muestra una pantalla de bienvenida por 3 segundos al iniciar la aplicación.
 * Después de ese tiempo, redirige automáticamente a la actividad principal
 */
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Configura la vista de la pantalla splash
     *
     * @param savedInstanceState Si la actividad se reinicia, este Bundle contiene los datos más recientes.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable() {
            /**
             * El método run() es ejecutado después del retraso especificado.
             * Inicia la actividad MainActivity y finaliza la actividad SplashActivity.
             */
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
