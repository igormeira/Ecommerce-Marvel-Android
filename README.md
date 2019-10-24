# Comics

Desenvolvido para a plataforma Android. O aplicativo tem o objetivo de servir como uma loja de quadrinhos da Marvel. Nele você é capaz de visualizar uma lista de HQs, visualizar informações específicas sobre um determinada HQ e realizar a compra das HQs que você desejar.

## Api utilizada:

- ([https://developer.marvel.com](https://developer.marvel.com))

## Dependências:

#### Activities
- 'androidx.appcompat:appcompat:1.1.0'
- 'androidx.annotation:annotation:1.1.0'
- 'androidx.lifecycle:lifecycle-extensions:2.1.0'
- 'androidx.recyclerview:recyclerview:1.0.0'

#### Layout
- 'androidx.cardview:cardview:1.0.0'
- 'com.google.android.material:material:1.0.0'
- 'com.google.android.material:material:1.0.0'
- 'androidx.constraintlayout:constraintlayout:1.1.3'

#### Acessar Api
- 'com.squareup.retrofit2:retrofit:2.5.0'
- 'com.squareup.retrofit2:converter-gson:2.5.0'
- 'com.squareup.okhttp3:logging-interceptor:3.13.1'
- 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'

#### Testes
- 'junit:junit:4.12'
- 'androidx.test:core:1.2.0'
- 'com.android.support.test:rules:1.0.2'
- 'com.android.support.test.espresso:espresso-core:3.0.2'
- 'androidx.test.espresso:espresso-intents:3.2.0'
- 'com.android.support.test.espresso:espresso-contrib:3.0.2'

## Permissões

- "android.permission.INTERNET"
- "android.permission.ACCESS_NETWORK_STATE"

## Estrutura

### asynctask

- BaseAsyncTask: Classe responsável por executar atividades de maneira assincrona.

### model

- Classes modelo (User, Address, Comic, ShopCar): Essas calsses guardam todo o modelo de como cria-las, modifica-las, recuperar informações e compara-las.
- Classes de desconto: Como as classes implementam sempre os mesmos métodos, foi usado o padrão de projeto Abstract Factory. Foi criada uma interface e 3 classes que implementam os métodos declarados na interface.

### retrofit

- Interface service: Essa interface é responsável por guardar a requisição (GET) que é feita à Api.
- MarvelRetrofit: Classe responsável estabelecer a conexão com a Api e fazer uso da interface servive, para realizar a requisição.

### ui

No pacote ui estão as classses responsáveis por fazer a ligação entre as telas e as classes de lógica.

- Adapters: Foi utulizado o padrão de projeto Adapter, no caso das classes ComicsActivity e ShopActivity, para realizar a adaptação para itens que são exibidos. Para listar os itens encontrados nessas classes, foi feito o uso de RecyclerView ([link](https://developer.android.com/guide/topics/ui/layout/recyclerview)). Com o RecyclerView nós temos uma melhora de performace e não usamos tanta memória quando o ListView, já que que existe uma reciclagem dos itens mostrados.
- ComicDetailActivity: Mostra as informações da HQ selecionada e cuida de adicionar a HQ no carrinho.
- ComicsActivity: Lista todas as HQs recuperadas da Api.
- LoginActivity: Cuida da parte de login do usuário.
- PayActivity: Realiza o pagamento do usuário. Responsável por mostrar informação da compra e receber os dados do cartão de créito.
- ShippingActivity: Tela apresentada caso o pagamento seja aceito. Exibe uma mensagem de agredicimento para o cliente.
- ShopActivity: Lista os intens presentes no carrinho do cliente.
- SplashScreenActivity: Exibe a tela de apresentação do app.
- UserActivity: Exibe as informações do usuário logado.

### util

Esse pacote guarda todas as classes que auxiliam as activities em suas funções.

- Currency: Possui os métodos para colocar a moeda no formato adequado.
- Dialogs: Possui métodos para invocar dialogs.
- SharedPreference: Possui métodos para guardar, recuperar e remover os itens do carrionho do usuário.
- Text: Possui método para ajustar os textos exibidos.

## O que pode ser adicionado?

- Refatoramento do código
- Retirar chamada da Api de dentro da ComicsActivity
- Validação de login
- Edição de usuário
- Criação de usuário
- Validação do cartão de crédito
- Outras formas de pagamento
- Tela para acompanhar pedido
- Incrementar testes instrumentados
- Incrementar testes de unidade
