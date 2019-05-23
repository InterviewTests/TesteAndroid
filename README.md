# Android App Teste 
> Esta versao possui tela que lista fundos, detalha e tela de contato 
> Telas de alertas personalizadas para todo o aplicativo.

[![ Version][version-image]][version-image]

- Usa uma LIB  GmailSender para enviar e-mails.
- Possui fontes personalizadas de acordo com o Design da empresa.
- Arquivos XML string com os textos usados por padrao no applicativo.
- carrega dados externos em json ou das preferencias.

## Configuracoes

- O Aplicativo foi desenovolvido na liguagem Java Nativo.
- Possui os gerenciadores de layout Constraint Layout e Recicledview 

## Grandle

- com.android.support:appcompat-v7:28.0.0.

## Testes
- Apenas os testes no formulario de email foram aplicados


```sh

grandle com.android.support:appcompat-v7:28.0.0
grandle com.android.support:recyclerview-v7:28.0.0
grandle com.android.support.test:runner:1.0.2
grandle com.android.support.test.espresso:espresso-core:3.0.2
grandle junit:junit:4.12
grandle org.mockito:mockito-core:2.27.0

```

## Telas 

![Telas do Aplicativo](https://raw.githubusercontent.com/busqe/TesteAndroid/master/telas.png)


## Release History

* 0.0.1
    * CHANGE: Envia Emails e Carrega Preferencias 

## Meta

Adriano Souza – [@adrianosouzai](https://twitter.com/adrianosouzai) – adrianosouza@gmail.com


Ainda esta em fase de testes. Nao possui ``LICENSE`` .

URL do Projeto [https://github.com/busqe/TesteAndroid](https://github.com/busqe/testeandroid)

## Contributing

- Este projeto faz parte do pacote Android Teste

## Imagens utilizadas
[version-image]: https://raw.githubusercontent.com/busqe/TesteAndroid/master/images/phone.svg
[version-url]: https://github.com/busqe/TesteAndroid/tree/master/images


\o\