# Projeto `Pandemia Simulator`

# Descrição Resumida do Projeto/Jogo

> O Pandemia Simulator consiste em um mapa quadriculado de tamanho variável que representa
uma cidade em um momento de pandemia. O agente principal é o prefeito, que caminha pela cidade a fim
de gerir os recursos necessários para a imunização da população, que é o objetivo principal do jogo. Para
isso, o jogador deve lidar com eventos como o investimento em vacinas, a aglomeração nas casas, a
paralisação das empresas/indústrias e, consequentemente, manejar os recursos monetários necessários
para conter o vírus de maneira que este não contamine a população inteira, que a cidade não colapse
economicamente ou que este não seja destituído por uma aprovação baixa.

# Equipe
* `<Leonardo de Queiroz Borges>` - `<177829>`
* `<Lucas Eduardo Ramos De Oliveira>` - `<182333>`

# Vídeos do Projeto

## Vídeo da Prévia
> [Link para o vídeo](https://drive.google.com/file/d/1Jy8ckKvVrpeEhBOrAJIAfLVy8HdCP8GC/view?usp=sharing)

# Slides do Projeto

## Slides da Prévia
> [Link para os slides](https://drive.google.com/file/d/1ruOlYLg5hwbdd_i3FqVQaLBlXTQcqdt0/view?usp=sharing)

# Documentação dos Componentes

# Diagramas

## Diagrama Geral do Projeto

![Diagrama Componentes](assets/arquitetura.png)

> Para a estruturação e o desenvolvimento do jogo, decidiu-se usar uma modificação do estilo arquitetural Model View Controller, em que temos uma componente que controla toda a parte de visualização do projeto, para que seja facilmente adaptada depois, e outra componente que controla os parâmetros e ações dos atores.

## Diagrama Geral de Componentes

Este é o diagrama compondo os componentes do jogo:

![Diagrama Componentes](assets/diagrama-componentes.png)

## Componente `GameBuilder`

> Esse componente é responsável por gerar os objetos que compõem o fluxo do jogo, solicitar a criação das instituições necessárias na componente City
> de acordo com um arquivo .csv e conectar as interfaces.

![Componente](assets/componente-gamebuilder.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Pacote | `com.gameBuilder`
Autores | `Lucas e Leonardo`
Interfaces | `IRConnectComponents` <br> `IBuildGame` <br> `IBuilder`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](assets/interfaces-gamebuilder.png)

Interface agregadora do componente em Java:

~~~java
public interface IBuilder extends IBuildGame, IRConnectComponents{
}
~~~

## Detalhamento das Interfaces


### Interface `IBuildGame`

Interface que permite acesso ao método buildGame para montagem do jogo.

~~~java
public interface IBuildGame {
    void buildGame(String directory);
}
~~~

Método | Objetivo
-------| --------
`buildGame` | Permite a montagem do jogo de acordo com o diretório dado.


### Interface `IRConnectComponents`

Interface que conecta a interface IConnectComponents à classe Builder.

~~~java
import com.gameControl.IConnectComponents;

public interface IRConnectComponents {
    void connect(IConnectComponents com);
}
~~~

Método | Objetivo
-------| --------
`connect` | Conecta o parâmetro "com" da classe Builder à interface IConnectComponents.


## Componente `GameControl`

> <Esse componente é responsável por controlar as ações e o fluxo do jogo, contém o Timer e o KeyboardListener.>

![Componente](assets/componente-gamecontrol.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `com.gameControl`
Autores | `Lucas e Leonardo`
Interfaces | `IConnectComponents` <br> `IPauseTimer` <br> `IRunGame` <br> `IGameApp` <br> `IGame`

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](assets/interfaces-gamecontrol.png)

Interface agregadora do componente em Java:

~~~java
public interface IGame extends IRunGame, IConnectComponents, IPauseTimer, IGameApp{
}
~~~

## Detalhamento das Interfaces


### Interface `IPauseTimer`

Interface que contém o método que é responsável por pausar a execução do Timer.

~~~java
public interface IPauseTimer {
    void pauseTimer();
    void resumeTimer();
}
~~~

Método | Objetivo
-------| --------
`pauseTimer` | pausa a execução do timer.
`resumeTimer` | volta a execução do timer.


### Interface `IConnectComponents`

Interface que provém o método necessário para ligar os componentes criados pelo Builder às variáveis que estão em Game.

~~~java
public interface IConnectComponents {
    void connectComponents(City city, View view);
}
~~~

Método | Objetivo
-------| --------
`connectComponents` | conecta os ponteiros recebidos pelos parâmetros às variáveis de Game.


### Interface `IRunGame`

Interface que contém o método que é responsável por executar o jogo.

~~~java
public interface IRunGame {
    void runGame();
}
~~~

Método | Objetivo
-------| --------
`runGame` | executa o jogo.


### Interface `IGameApp`

Interface agregadora que contém os métodos necessários pela Main para execução e conexão do jogo.

~~~java
public interface IGameApp extends IRunGame, IConnectComponents{
}
~~~

## Componente `City`

> <Esse componente é responsavel por guarda o tabuleiro do jogo, contém os controllers de cada instituição.>

![Componente](assets/componente-city.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `com.city`
Autores | `Lucas e Leonardo`
Interfaces | `IUpdateParameters` <br> `IRUpdateParameters` <br> `IRUpdateBar` <br> `IRUpdateView` <br> `IActionPanel` 

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](assets/interfaces-city.png)

Interface agregadora do componente em Java:

~~~java
public interface ICity extends IRUpdateBar, IUpdateParameters {
}
~~~

## Detalhamento das Interfaces


### Interface `IUpdateParameters`

Contém os métodos de atualização dos parâmetros de City.

~~~java
public interface IUpdateParameters {
    void updateMoney(int quantityDelta);
    void updateApproval(int quantityDelta);
    void updateInfected(int quantityDelta);
    void updateImmunized(int quantityDelta);
    void updateDeaths(int quantityDelta);
}
~~~

Método | Objetivo
-------| --------
`updateMoney` | Método que permite a atualização do parâmetro Money de City pelo InstitutionView.
`updateApproval` | Método que permite a atualização do parâmetro Approval de City pelo InstitutionView.
`updateInfected` | Método que permite a atualização do parâmetro Infected de City pelo InstitutionView.
`updateImmunized` | Método que permite a atualização do parâmetro Immunized de City pelo InstitutionView.
`updateDeaths` | Método que permite a atualização do parâmetro Deaths de City pelo InstitutionView.


### Interface `IRUpdateBar`

Interface que conecta a classe City à interface IUpdateBar para que City consiga atualizar as barras de progresso de View.

~~~java
public interface IRUpdateBar {
    void connect(IUpdateBar barControl);
}
~~~

Método | Objetivo
-------| --------
`connect` | Conecta a interface IUpdateBar à variavel barControl de City.


### Interface `IActionPanel`

Contém ações que o usuário pode fazer através do painel de cada instituição, quando este abrí-lo.

~~~java
public interface IActionPanel {
    void invest(int quantity);
    void limitOccupation(int percentage);
    void stopAgglomeration();
    int getParameter(char type);
}
~~~

Método | Objetivo
-------| --------
`invest` | Investe uma quantidade de dinheiro em uma determinada instituição.
`limitOccupation` | Limita a ocupação de uma determinada instituição.
`stopAgglomeration` | Intervém em alguma aglomeração que pode estar ocorrendo no local, para não aumentar a taxa de infecção.
`getParameter` | Obtém os dados de um certo parâmetro contido em alguma instituição.


### Interface `IRUpdateView`

Interface que conecta a interface IUpdateView à classe InstitutionView, para permitir o acesso aos métodos.

~~~java
public interface IRUpdateView {
    void connect(IUpdateView viewUpdate);
}
~~~

Método | Objetivo
-------| --------
`connect` | Método que conecta a interface IUpdateView à variável viewUpdate de InstitutionView.


### Interface `IRUpdateParameters`

Interface que conecta a interface IUpdateParameters à classe InstitutionView, para permitir o acesso aos métodos de atualização de parâmetros da City.

~~~java
public interface IRUpdateParameters {
    void connect(IUpdateParameters cityParameters);
}
~~~

Método | Objetivo
-------| --------
`connect` | Método que conecta a interface IUpdateParameters à variável cityParameters de InstitutionView.

### Interface `IInstitutionControl`

Interface agregadora de Institution Control, que agrega as interfaces necessárias.

~~~java
public interface IInstitutionControl extends IRUpdateParameters, IRUpdateView, IActionPanel{
}
~~~


## Componente `View`

> <Esse componente é responsavel por toda parte gráfica do código, possui um tabuleiro de views especificos, além de um BarView e Prefeito com.view.>

![Componente](assets/componente-view.png)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `com.view`
Autores | `Lucas e Leonardo`
Interfaces | `IUpdateBar` <br> `IUpdateView` <br> `IRPauseTimer` <br> `IRActionPanel` <br> `IKeyboard` 

### Interfaces

Interfaces associadas a esse componente:

![Diagrama Interfaces](assets/interfaces-view.png)

Interface agregadora do componente em Java:

~~~java
public interface IView extends IKeyboard, IUpdateBar{
}
~~~

## Detalhamento das Interfaces


### Interface `IUpdateBar`

Interface que disponibiliza os métodos de atualização dos parâmetros de View para que as barras de progresso se atualizem.

~~~java
public interface IUpdateBar {
    void setMoney(int quantity);
    void setApproval(int percentage);
    void setDeaths(int quantity);
    void setInfected(int quantity);
    void setImmunized(int quantity);
    void setPopulation(int quantity);
}
~~~

Método | Objetivo
-------| --------
`setMoney` | Configura a variável "money" de View com o valor passado por parâmetro, para atualização.
`setApproval` | Configura a variável "approval" de View com o valor passado por parâmetro, para atualização.
`setDeaths` | Configura a variável "deaths" de View com o valor passado por parâmetro, para atualização.
`setInfected` | Configura a variável "infected" de View com o valor passado por parâmetro, para atualização.
`setImmunized` | Configura a variável "immunized" de View com o valor passado por parâmetro, para atualização.
`setPopulation` | Configura a variável "population" de View com o valor passado por parâmetro, para atualização.


### Interface `IKeyboard`

Interface que disponibiliza as ações que o Mayor pode fazer no View.

~~~java
public interface IKeyboard {
    void keyPress(int key);
}
~~~

Método | Objetivo
-------| --------
`keyPress` | Executa as ações correspondentes à tecla pressionada.


### Interface `IRPauseTimer`

Interface que conecta a classe City à interface IPauseTimer para que City consiga pausar a execução do jogo.

~~~java
public interface IRPauseTimer {
    void connect(IPauseTimer timerControl);
}
~~~

Método | Objetivo
-------| --------
`connect` | Conecta a interface IPauseTimer à variavel timerControl de cada painel.


### Interface `IRActionPanel`

Interface que conecta a interface ActionPanel à classe InstitutionPanel respectiva.

~~~java
public interface IRActionPanel {
    void connect(IActionPanel actionPanel);
}
~~~

Método | Objetivo
-------| --------
`connect` | Conecta a interface IActionPanel, implementada pela classe InstitutionController, à classe InstitutionPanel, para que o painel consiga acessar métodos do controle.


### Interface `IPanel`

Interface agregadora que junta as interfaces implementadas por cada painel de instituição.

~~~java
public interface IPanel extends IRActionPanel, IRPauseTimer, WindowListener {
}
~~~


### Interface `IUpdateView`

Interface que disponibiliza os métodos de atualização dos parâmetros de cada InstitutionView para que os detalhes se atualizem.

~~~java
public interface IUpdateView {
    void setAgglomeration(boolean isAgglomerating);
}
~~~

Método | Objetivo
-------| --------
`setAgglomeration` | Comunica se a instituição está aglomerando, para que o View mude o ícone mostrado na tela.


# Plano de Exceções

## Diagrama da hierarquia de exceções
`O diagrama abaixo mostra a hierarquia do plano de exceções montado para o projeto.`

![Hierarquia Exceções](assets/diagrama-excecoes.png)

## Descrição das classes de exceção

Classe | Descrição
----- | -----
FileRead | Engloba todas as exceções de leitura não aceitas.
FileNotFound | Indica que o arquivo nao foi achado.
InvalidFormat | Indica que o arquivo esta no formato inválido.
IconRead | Engloba todas as exceções de leitura de imagens.
NameRead | Engloba todas as exceções de nomes nao aceitas.
InvalidCharacter | Indica um caracter inválido.
NameTooLong | Indica um nome muito grande.
NullName | Indica um nome vazio.
GameBuilder | Engloba todas as exceções de construção.
InvalidPosition | Indica uma posição inválida.
TwoOnSamePosition | Indica que há a tentativa dois objetos numa posição.
InvalidInstitution | Indica uma instituição inexistente.
InvalidInstitutionQuantity | Indica uma quantidade de instituição inválida.
