# Android App Teste 
> Esta versao possui tela que lista fundos, detalha e tela de contato 
> Telas de alertas personalizadas para todo o aplicativo.

[![ Versao][version-image]][version-image]
[![ Linguagem][lang-image]][lang-image]
[![ Grandle][grandle-image]][grandle-image]

- Usa uma LIB  GmailSender para enviar e-mails.
- Possui fontes personalizadas de acordo com o Design da empresa.
- Arquivos XML string com os textos usados por padrao no applicativo.
- carrega dados externos em json ou das preferencias.

## Configuracoes

- O Aplicativo foi desenovolvido na liguagem Java Nativo.
- O Padrao de arquitetura MVC Clean Arquitecture.
- Possui os gerenciadores de layout Constraint Layout e Recyclerview 
- o Nome do package do Aplicativo deve ser:

```sh

	package 'br.banco.servces'

```


## Grandle

```sh

	grandle 'com.android.support:appcompat-v7:28.0.0'
	grandle 'com.android.support:recyclerview-v7:28.0.0'
	grandle 'com.android.support.constraint:constraint-layout:1.1.3'
	grandle 'com.android.support.constraint:constraint-layout:1.0.3'
	grandle 'com.google.code.gson:gson:2.7'

```

## Testes
- Apenas os testes no formulario de email foram aplicados

```sh

	grandle 'com.android.support.test:runner:1.0.2'
	grandle 'com.android.support.test.espresso:espresso-core:3.0.2'
	grandle 'junit:junit:4.12'
	grandle 'org.mockito:mockito-core:2.27.0'

```

## Libs
- Usa uma LIB GmailSender para enviar e-mails.
- Libs: activation.jar, aditional.jar, mail.jar
 
```sh
 
  grandle fileTree(dir: 'libs/', include: ['*.jar'])

```

1. Instalar a Library dentro da pasta lib
2. configurar o arquivo GmailSender.java da forma abaixo:
3. importear o pacote  GMailSender;
4. Caso use o Gmail para receber os emails ser pedido uma solicitacao de 'Aplicativos de Terceiros'

Abaixo veja o config do GMailSender:

```sh
    public String Mailhost = "smtp.gmail.com";
    public String User ="seu-email@gmail.com";
    public String Password ="sua-senha"; 

```

## Fonts 
- Foram utilizadas fontes que seguem a identidade visual da empresa: DINPro. 
- As fontes foram copiadas para a pasta fonts dentro do projeto.
- Abaixo veja os nomes das Fontes:

```sh
   	DINPro-Black
	DINPro-Bold
	DINPro-Medium
	DINPro-Regular
	DINPro-Light

```


## Telas 
- O Fluxo de navegacao fica na sequencia da imagem abaixo: 

![Telas do Aplicativo](https://raw.githubusercontent.com/busqe/TesteAndroid/master/telas.png)


## Fluxo Codigo 
* O Fluxo de navegacao contato por Email:

1. SOLICITACAO: View > Presenter > Model > Interactor > Validator > Rule .  
2. Rule devolve ao Validator...  que faz o caminho de volta.

```sh
	- Validator ou Rule: classe responsavel por validar os dados Recebidos/Enviados antes de fazer a SOLICITACAO.
	- Exemplo de Validator: classe FromScreen (configura a classe ScreenFundTemplate) responsavel por permitir que a tela de Fundos seja exibida.

```


- TELA FLUXO: Fundos de Investimentos -> Telad e detalhe.
![Telas do Aplicativo](https://github.com/busqe/TesteAndroid/blob/master/images/tela-flow-fund.png)

- TELA FLUXO: Criacao da Tela de Formulario de contato

![Telas do Aplicativo](https://github.com/busqe/TesteAndroid/blob/master/images/tela-flow-email.png)


## Historico de Versões

* 0.0.1
    * ALTERACAO: Envia Emails e carrega Preferencias 

## Meta

Adriano Souza – [@adrianosouzai](https://twitter.com/adrianosouzai) – adrianosouza@gmail.com


Ainda esta em fase de testes. Nao possui ``LICENSE`` .

URL do Projeto [https://github.com/busqe/TesteAndroid](https://github.com/busqe/testeandroid)

## Contribuição

- Este projeto faz parte do pacote Android Teste

## Imagens utilizadas
[version-image]: https://github.com/busqe/TesteAndroid/blob/master/images/ico-version.svg
[version-url]: https://github.com/busqe/TesteAndroid/tree/master/images

[lang-image]: https://github.com/busqe/TesteAndroid/blob/master/images/ico-lang.svg
[grandle-image]: https://github.com/busqe/TesteAndroid/blob/master/images/ico-grandle.svg

-- Muito obrigado!

\o/


