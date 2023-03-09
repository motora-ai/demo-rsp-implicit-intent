## Demo RSP Implicit Intent

Para interagir com o RSP via outros app's é necessário utilizar o mecanismo Android de [Implicit Intents](https://developer.android.com/guide/components/intents-filters#Types), para declarar ao sistema uma ação que deve ser executada por algum app (no caso o RSP).

### Iniciando uma viagem

Para criar uma viagem utilize um [Intent](https://developer.android.com/reference/android/content/Intent) passando uma ação de visualização ([Intent.ACTION_VIEW](https://developer.android.com/reference/android/content/Intent#ACTION_VIEW)) e URI com o seguinte formato:

```rsp://motora.ai/start-travel?driver_cpf=CPF_DO_MOTORISTA&car_plate=PLACA_DO_CARRO&service_code=CODIGO_DO_SERVICO&service_type=TIPO_DO_SERVICO&service_line=LINHA_DO_SERVICO&scheduled_begin_time=HORARIO_DE_INICIO_PLANEJADO```

Um exemplo pode ser visto abaixo.

```kotlin
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
```

Agora basta utilizar o método [startActivity](https://developer.android.com/reference/android/app/Activity#startActivity(android.content.Intent)) passando o intent criado anteriormente.

O método startActivity pode lançar uma exceção do tipo ActivityNotFoundException caso não encontre algum app para receber o intent emitido, portanto ao chama-lo utilize um bloco try-catch.

```kotlin
try {
    startActivity(startTravelIntent)
} catch (e: ActivityNotFoundException) {
    Log.e(TAG, "Erro ao iniciar a viagem")
    Log.e(TAG, e.message ?: "Sem mensagem disponível")
    
    Toast.makeText(
        this@MainActivity,
        "Certifique se de que o app RSP está disponível e atualizado",
        Toast.LENGTH_LONG
    ).show()
}
```

### Encerrando uma viagem

Para encerrar uma viagem utilize um [Intent](https://developer.android.com/reference/android/content/Intent) passando uma ação de visualização ([Intent.ACTION_VIEW](https://developer.android.com/reference/android/content/Intent#ACTION_VIEW)) e URI com o seguinte formato:

```rsp://motora.ai/end-travel```

Um exemplo pode ser visto abaixo.

```kotlin
val uri = Uri.Builder()
    .scheme("rsp")
    .authority("motora.ai")
    .path("end-travel")
    .build()

val endTravelIntent = Intent(Intent.ACTION_VIEW, uri)
```

Agora basta utilizar o método [startActivity](https://developer.android.com/reference/android/app/Activity#startActivity(android.content.Intent)) passando o intent criado anteriormente.

O método startActivity pode lançar uma exceção do tipo ActivityNotFoundException caso não encontre algum app para receber o intent emitido, portanto ao chama-lo utilize um bloco try-catch.

```kotlin
try {
    startActivity(endTravelIntent)
} catch (e: ActivityNotFoundException) {
    Log.e(TAG, "Erro ao encerrar a viagem")
    Log.e(TAG, e.message ?: "Sem mensagem disponível")

    Toast.makeText(
        this@MainActivity,
        "Certifique se de que o app RSP está disponível e atualizado",
        Toast.LENGTH_LONG
    ).show()
}
```

### Demo

Neste repositório se encontra um app usado como demonstração que pode ser executado para testar a comunicação com o RSP. Para executar o projeto basta abri-lo no [Android Studio](https://developer.android.com/studio) e executar o projeto em um dispositivo real ou emulador que contenha a versão mais recente do app RSP. 