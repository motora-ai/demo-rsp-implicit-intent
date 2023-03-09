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
                        // "rsp://motora.ai/start-travel?driver_cpf=CPF_DO_MOTORISTA&car_plate=PLACA_DO_CARRO&service_code=CODIGO_DO_SERVICO&service_type=TIPO_DO_SERVICO&service_line=LINHA_DO_SERVICO&scheduled_begin_time=HORARIO_DE_INICIO_PLANEJADO"
                        //
                        // Um exemplo pode ser visto abaixo.

                        // Informações obrigatórias
                        val driverCpf: String = "92835029060" // CPF do motorista (com ou sem '.' e '-')
                        val carPlate: String = "ABC0001" // Placa do veículo (com ou sem '-')

                        // Informações opcionais
                        val serviceCode: String? = null // Código de serviço.
                                                        // ex: "987435", "TUR 165980_1_3 (01)"  ou null

                        val serviceType: String? = null // Tipo de serviço.
                                                        // ex: "Linha", "Turismo", "Squad" ou null

                        val serviceLine: String? = null // Linha de serviço.
                                                        // ex: "120 - VITORIA X SAO PAULO" ou null

                        val scheduledBeginTime: String? = null  // Data de ínicio previsto.
                                                                // ex: Data e hora em formato ISO-8601 "2023-01-01T01:01:01-03:00" ou null

                        val uri = Uri.Builder()
                            .scheme("rsp")
                            .authority("motora.ai")
                            .path("start-travel")
                            .appendQueryParameter("driver_cpf", driverCpf)
                            .appendQueryParameter("car_plate", carPlate)
                            .appendQueryParameter("service_code", serviceCode)
                            .appendQueryParameter("service_type", serviceType)
                            .appendQueryParameter("service_line", serviceLine)
                            .appendQueryParameter("scheduled_begin_time", scheduledBeginTime)
                            .build()

                        val startTravelIntent = Intent(Intent.ACTION_VIEW, uri)

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
                                Uri.Builder()
                                    .scheme("rsp")
                                    .authority("motora.ai")
                                    .path("end-travel")
                                    .build()
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