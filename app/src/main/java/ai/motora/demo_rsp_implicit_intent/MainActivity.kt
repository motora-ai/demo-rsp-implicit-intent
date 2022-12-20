package ai.motora.demo_rsp_implicit_intent

import ai.motora.demo_rsp_implicit_intent.ui.theme.DemoRSPImplicitIntentTheme
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "Tela principal criada")

        setContent {
            DemoRSPImplicitIntentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainActivityScreen()
                }
            }
        }
    }

    private val mainColor = Color(0xFF673AB7)

    @Preview
    @Composable
    private fun MainActivityScreen() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "RSP Implicit Intent Demo"
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(3.0f))

                Button(
                    onClick = {
                        // Para criar uma viagem utilize um Intent (https://developer.android.com/reference/android/content/Intent)
                        // passando uma ação de visualização (Intent.ACTION_VIEW) e URI com o seguinte formato:
                        //
                        // "rsp://motora.ai/start-travel?driver_cpf=CPF_DO_MOTORISTA&car_plate=PLACA_DO_CARRO"
                        //
                        // Um exemplo pode ser visto abaixo.

                        val startTravelIntent =
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("rsp://motora.ai/start-travel?driver_cpf=92835029060&car_plate=ABC0001")
                            )

                        // Depois de criar o Intent basta utilizar o método startActivity passando o Intent criado anteriormente.
                        //
                        // O método startActivity pode lançar uma exceção ActivityNotFoundException caso não encontre
                        // algum app para receber o intent, portanto ao chama-lo utilize um bloco try-catch.

                        try {
                            startActivity(startTravelIntent)
                        } catch (e: ActivityNotFoundException) {
                            Log.e(TAG, "Erro ao iniciar a viagem")
                            Log.e(TAG, e.message ?: "Sem mensagem disponivel")

                            Toast.makeText(
                                this@MainActivity,
                                "Certifique se de que o app RSP está disponivel e atualizado",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = mainColor),
                    modifier = Modifier.fillMaxWidth(.5f)
                ) {
                    Text(
                        text = "Iniciar Viagem",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(.5f))

                Button(
                    onClick = {
                        // Para encerrar uma viagem utilize um Intent (https://developer.android.com/reference/android/content/Intent)
                        // passando uma ação de visualização (Intent.ACTION_VIEW) e URI com o seguinte formato:
                        //
                        // "rsp://motora.ai/end-travel"
                        //
                        // Um exemplo pode ser visto abaixo.

                        val endTravelIntent =
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("rsp://motora.ai/end-travel")
                            )

                        // Depois de criar o Intent basta utilizar o método startActivity passando o Intent criado anteriormente.
                        //
                        // O método startActivity pode lançar uma exceção ActivityNotFoundException caso não encontre
                        // algum app para receber o intent, portanto ao chama-lo utilize um bloco try-catch.

                        try {
                            startActivity(endTravelIntent)
                        } catch (e: ActivityNotFoundException) {
                            Log.e(TAG, "Erro ao encerrar a viagem")
                            Log.e(TAG, e.message ?: "Sem mensagem disponivel")

                            Toast.makeText(
                                this@MainActivity,
                                "Certifique se de que o app RSP está disponivel e atualizado",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = mainColor),
                    modifier = Modifier.fillMaxWidth(.5f)
                ) {
                    Text(
                        text = "Encerrar Viagem",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.weight(3.0f))
            }
        }
    }
}