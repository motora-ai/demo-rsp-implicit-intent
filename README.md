## Demo RSP Implicit Intent

Para interagir com o RSP via outros app's é necessário utilizar o mecanismo Android de [Implicit Intents](https://developer.android.com/guide/components/intents-filters#Types), para declarar ao sistema uma ação que deve ser executada por algum app (no caso o RSP).

### Iniciando uma viagem

Para criar uma viagem utilize um [Intent](https://developer.android.com/reference/android/content/Intent) passando uma ação de visualização ([Intent.ACTION_VIEW](https://developer.android.com/reference/android/content/Intent#ACTION_VIEW)) e URI com o seguinte formato:

```rsp://motora.ai/start-travel?driver_cpf=CPF_DO_MOTORISTA&car_plate=PLACA_DO_CARRO```

Um exemplo pode ser visto abaixo.

```kotlin
val uri = Uri.parse("rsp://motora.ai/start-travel?driver_cpf=92835029060&car_plate=ABC0001")

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
val uri = Uri.parse("rsp://motora.ai/end-travel")

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