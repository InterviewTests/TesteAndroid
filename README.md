## Desafio Santander
* Candidato: pauloage (paulo.age@outlook.com)
* LinkeDin: https://www.linkedin.com/in/paulo-silva-184771155/
* Empresa: Avanade

## Notas gerais
* Android Studio: 3.2.1
* Procedimento de build: procedimento padrão (sync do gradle e run)
* A implementação foi realizada utilizando **SOLID (with love)**, MVP, esforço para desacoplar entidades, elementos de arquitetura orientada a eventos (Hey Martin Fowler! \o_) e lei de Demeter (do not break the law!!).

## Formulário de contato e `FormComponentView.java`
* A subclass de View (`FormComponentView.java`) foi criada para encapsular a criação e setup das views contituintes do form de contato. Neste caso reduzindo a responsabilidade do presenter e (tecnicamente) elevando a resuabilidade das componentes do form =)

## Uso das libs:
* **EventBus:** descoplar entidades. Neste caso, o clique (botão enviar) e título da actionbar foram implementados com orientação a eventos.
* **AndroidAnnotations**: redução de linhas de código e facilidade de recuperação de views/activities/fragments.
* **RetroFit2**: consumo de serviços.
* **adapter-rxjava2**: adapter do RetroFit para retorno de *Observables*
* **rxjava2:rxandroid**: Utilização de Threads e MainThreads (JUnit4)
* **rxjava2:rxjava:2.2.4**: *Observables*

## Testes
* A *suite* de testes encontra-se em **InvestmentsTestSuite.java**
* Sem testes instrumentados (Espresso) - não foi solicitado.

