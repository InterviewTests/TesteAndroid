# Aplicativo TesteAndroid para a vaga de desenvolvedor Android

### Instruções como instalar o aplicativo:

Copiar o arquivo app-release.apk para o dispositivo Android com versão minima 4.4 ou superior.
O arquivo esta disponivel na pasta release na raiz do projeto.

**Habilitar fontes desconhecidas no disposito para conseguir instalar o app**

## Bibliotecas

As bibliotecas e ferramentas utilizadas no projeto:

- Support library
- RecyclerViews 
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) 
- [Fast-Android-Networking](https://github.com/amitshekhariitbhu/Fast-Android-Networking/)
- [Dagger 2](http://google.github.io/dagger/)
- [Calligraphy](https://github.com/chrisjenx/Calligraphy)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Junit](https://junit.org/junit4/)
- [Espresso](https://google.github.io/android-testing-support-library/)
- [Mockito](http://mockito.org/)

## Requisitos

- [Android SDK](http://developer.android.com/sdk/index.html).
- Android [8.0 (API 26) ](http://developer.android.com/tools/revisions/platforms.html#8.0).
- Android SDK Tools
- Android SDK Build tools 27.0.3
- Android Support Repository

### Testes

Para executar os testes **unitarios** em sua máquina execute o comando:
``` 
./gradlew test
``` 

Para executar os testes **funcionais** em dispositivos conectados execute o comando:
```
./gradlew connectedAndroidTest
``` 