# deadlock-detection
Projeto II - Cadeira de Sistemas Operacionais, Engenharia da computação - 2018.1 | Instituto Federal do Ceará

## Descrição
O presente projeto é o segundo de dois projetos previstos para serem feitos na disciplina de Sistemas Operacionais com o objetivo de fixar os conteúdos ensinados em sala, referentes à gerencia de recursos de máquina e deadlocks.

O projeto consiste de duas classes de threads: processos e sistema operacional. A classe processos poderá ter várias instâncias, que devem simular os processos solicitando, utilizando e liberando recursos do sistema. A classe sistema operacional terá apenas uma instância e ficará responsável por detectar possíveis deadlocks.

### Pré-requisitos

* Java Runtime Environment (JRE) 8 ou mais recente.

 Link: [JRE 8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jre8-downloads-2133155.html)

### Executando

- Baixe o arquivo *DeadlockDetection.jar* que se encontra no diretório *dist/DeadlockDetection.jar*
- Com o JRE já instalada na máquina execute o *DeadlockDetection.jar*

## Testes
### Entradas
Antes de iniciar a simulação, o usuário deverá informar todos os recursos existentes no sistema, e para cada recurso informados o usuário deverá configurar os seguintes parâmetros:
```
- Nome do Recurso (Ex: Impressora)
- Identificador do Recurso (Ex: 1)
```
Obs.: A quantidade máxima de recursos é 10.

* Thread “Sistema Operacional”: Esta thread tem a função verificar periodicamente (a cada intervalo Δt) se existe algum deadlock no sistema. Se houver, ela deve informar ao usuário quais processos estão em deadlock e quais estão impedidos de rodar. Ao iniciar a execução da simulação, o programa deverá solicitar ao usuário o intervalo Δt (em segundos), e em seguida instanciar a thread sistema operacional.

* Threads “Processo”: Estas threads deverão solicitar, utilizar e liberar recursos existentes no sistema. Podem existir até 10 processos rodando “simultaneamente”.

* Criar thread processo: Durante a criação de cada processo devem ser definidos os seguintes parâmetros:
```
- Id = identificador do processo.
- ΔTs = intervalo de tempo de solicitação (em segundos).
- ΔTu = intervalo de tempo de utilização (em segundos).
```
A cada ΔTs o processo solicita a utilização de um recurso escolhido aleatoriamente dentre os recursos existentes no sistema que ainda não foram concedidos ao respectivo processo. Se o recurso estiver disponível, o processo receberá o recurso, irá utilizá-lo e ao final deverá liberá-lo. Caso o recurso solicitado não esteja disponível, o processo deverá dormir e só deverá ser acordado quando o recurso que ele solicitou for liberado por outro processo.

Após o processo ter tomado posse de um recurso, ele deverá utilizá-lo durante o intervalo de tempo ΔTu e em seguida liberá-lo.

* Eliminar thread processo: Esta opção permite eliminar um processo a partir de um id. Deve ser permitido eliminar tanto os processos que estejam “rodando” quanto os que estejam “bloqueados”,

### Saídas
A interface deve exibir o grafo de detecção de deadlocks.
A interface deve mostrar, a cada instante, o status de cada processo (rodando ou bloqueado), os recursos existentes, os recursos disponíveis, quais recursos estão sendo utilizados por cada um dos processos, quais recursos estão sendo aguardados pelos processos bloqueados e um “log” na tela mostrando todas as operações efetuadas por todos os processos. Este “log” deverá mostrar mensagens do tipo “o processo X solicitou/está utilizando/liberou o recurso Y”.
A interface também deverá informar se existe algum deadlock e quais processos estão em deadlock ou impedidos de rodar.

### Imagens
![Primeira tela](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb1.png)

![Menu](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb2.png)

![Seleção de recursos](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb3.png)

![Sistema operacional](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb4.png)

![Tela de grafos](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb5.png)

![Executando](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb6.png)

![Adicionando processo](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb7.png)

![Informação do processo](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb8.png)

![Removendo processo](https://github.com/lucasmag/deadlock-detection/blob/master/imagens/thumb9.png)

## Feito com

* [JavaFX Scene Builder + Netbeans](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk-netbeans-jsp-3413153-ptb.html) 

## Autor

* **Lucas Magalhães** - [LucasMag](https://github.com/lucasmag)

