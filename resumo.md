# Visão geral

## Atores

- Usuário
  - Pesquisador
    - Revisor (vira revisor através de convite do coordenador)
  - Coordenador (tem apenas 1)

## Fluxo geral

- Autenticar com email e senha
  - Usuário coordenador deve ser pré-configurado

## Fluxo do coordenador

- Iniciar evento com nome, data limite para submissão, cidade, período (range de datas) e categoria dentre:
  - Full paper
  - Short paper
  - Demo
  - ao iniciar um novo evento, os dados do evento anterior são desconsiderados
  - pesquisadores e coordenador continuam cadastrados, mas revisores/artigos/avaliações/áreas pertencem ao evento atual
- Cadastrar áreas temáticas
  - áreas são cadastradas pelo coordenador
  - autores usam essas áreas para classificar artigos
  - revisores usam essas áreas para declarar expertise
  - as áreas são usadas para calcular compatibilidade entre artigos e revisores
- Convidar pesquisadores para serem revisores
  - o pesquisador convidado seleciona suas áreas temáticas ao entrar com sua conta e abrir o sistema
  - essas áreas serão usadas na distribuição automática dos artigos
- Visualizar dados do evento como coordenador:
  - número de
    - artigos submetidos
    - revisores
    - artigos avaliados
      - artigos aceitos e rejeitados contam como avaliados
    - artigos pendentes
      - artigos em revisão e em consenso contam como pendentes
  - relação de artigos pendentes e avaliador responsável

## Fluxo do pesquisador/autor

- Cadastrar usuários  (pesquisador) com email, senha e instituição
- Submeter artigo com nome, resumo, coautores e áreas temáticas apenas se estiver no período de submissão, caso contrário mostrar mensagem
- Visualizar artigos submetidos pelo autor logado no momento com status:
  - pendente (deriva dos mais específicos)
    - submetido
    - revisão
    - ~em consenso~ (revisão)
  - concluído
    - aceito
    - rejeitado

## Fluxo do revisor

- Visualizar artigos para revisão (como autor)
- Avaliar artigo escrevendo críticas (texto), contribuições (outro texto) e dando um veredito final dentre:
  - rejeitado
  - fracamente rejeitado
  - fracamente aceito
  - aceito

## Fluxo de revisão dos artigos

- Distribuir artigos para revisores seguindo algumas regras:
  - distribuir conforme áreas temáticas dos revisores, aceitando artigos sem aderência às áreas apenas em casos em que faltar revisor compatível
  - distribuir igualmente tanto quanto possível (no caso de ter mais artigos do que revisores alguém vai ficar com mais)
  - não dar a um revisor um artigo do qual é autor ou co-autor (máxima)
  - não mostrar ao revisor quem são os autores do artigo que vai avaliar (máxima)
  - notificar por email revisor sobre cada artigo recebido para avaliação
  - o sistema deve priorizar revisores com maior compatibilidade com as áreas temáticas do artigo
    - um autor tem 3 áreas temáticas de compat. outro apenas duas, o com mais ganha o artigo
  - o autor sempre aceita o artigo (não há opção)
  - cada artigo deve ser distribuído para 2 revisores
  - a distribuição deve considerar apenas revisores do comitê técnico do evento atual
  - se não houver 2 revisores compatíveis, completar com revisores disponíveis mesmo sem compatibilidade
  - a notificação ao revisor deve informar o artigo recebido e o prazo máximo para concluir a revisão (fim do evento como um todo)

- Notificar autores no final da revisão
  - o envio deve ser real, não apenas uma mensagem no console
    - autores de artigos aceitos e rejeitados recebem email
    - o email deve informar o resultado final do artigo
    - o email deve incluir os pareceres dos revisores

- Concluir artigo após os 2 pareceres:
  - Casos aceitos:
    - aceito + aceito
    - aceito + fracamente recusado
  - Casos recusados:
    - recusado + recusado
    - recusado + fracamente aceito
  - Casos de atenção (necessário notificar revisores sobre a necessidade de trocar o veredito de um deles para conclusão real da revisão):
    - aceito + recusado
    - fracamente aceito + fracamente recusado

- Notificar autores no final da revisão
  - o envio deve ser real, não apenas uma mensagem no console
    - autores de artigos aceitos e rejeitados recebem email
    - o email deve informar o resultado final do artigo
    - o email deve incluir os pareceres dos revisores

## RF10 / Extra

- Extra ???

## Padrões que provavelmente serão usados em algum lugar

- Chain (Autorização)
- Proxy (talvez melhor para autorização do que Chain, considerando o escopo e facilidade de implementar)
- Facade (Um service por exemplo)
- Observer (envio de artigo p/ revisão)
- Singleton (p/ repositories, algumas classes de apresentacao e services)
- Strategy
- Command (para ações como convidar revisor, submeter artigo, etc)
- state (talvez para o estado do artigo, mas não sei se compensa a complexidade)

### Um pouco mais de detalhes sobre os padrões

- Proxy
  - pode ser usado para verificar se o usuário tem permissão para realizar uma ação antes de delegar a chamada para o objeto real que executa a ação (facade)

- Facade
  - por ator, englobando as ações que o ator tem em seu fluxo, se necessário criar adicionais
    - reviewer
    - researcher
    - coordinator

- Observer
  - notificar revisores sobre recebimento de artigos para revisão
  - notificar autores sobre conclusão da revisão

- Command
  - ao "convidar" um pesquisador para ser revisor, este deve receber inserir as áreas temáticas que tem proficiência para revisar, de maneira que isso precisa acontecer de maneira adiada
  - provavelmente também na camada de apresentação

- State para o estado do artigo
  - cada estado sabe o que pode fazer, evitando vários ifs espalhados por aí

- Adapter
  - na questão de email evitando acoplamento com classe de email concreta

## Ideias gerais

- Separar em camadas
  - Apresentação
  - Lógica (onde se enquadrariam os padrões, serviços, etc)
  - Banco (Repositórios)

- As informações serão guardadas em memória, não vai haver banco de fato
  - na verdade nem mesmo o processo de seed vai ser a partir de um csv ou coisa assim, vamos utilizar um seed a partir de uma classe que vai rodar:
    - ou a partir de uma  escolha do usuário  de popular a partir do menu, onde ele criaria apenas o evento na mão
    - ou colocar quando inicializar o app

- Usar interfaces de repositórios evitando acoplamento

- Os repositórios concretos devem armazenar em atributos as informações
    - os usuários cadastrados ficam como uma lista em UserRepo etc

- Usar a lógica de que o único evento é o atual, ou seja, não tem pra que guardar referência a evento nas classes que tem relacionamento com ele, existe apenas um evento

- Quando novo evento for startado, dados do antigo devem ser descartador, isso deve estar centralizado no service de evento, se for existir
