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

- Iniciar evento com nome, cidade, período (range de datas) e categoria dentre:
  - Full paper
  - Short paper
  - Demo
  - data limite para submissão
  - ao iniciar um novo evento, os dados do evento anterior são desconsiderados
  - pesquisadores continuam cadastrados, mas revisores/artigos/avaliações/áreas pertencem ao evento atual
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
- Notificar autores no final do ciclo de revisões (acho que é ao revisar todos os artigos ou na data determinada)
  - o envio deve ser real, não apenas uma mensagem no console
    - autores de artigos aceitos e rejeitados recebem email
    - o email deve informar o resultado final do artigo
    - o email deve incluir os pareceres dos revisores
    - autores só recebem os pareceres após o artigo estar aceito ou rejeitado

## Fluxo do pesquisador/autor

- Cadastrar usuários (pesquisador) com email, senha e instituição
- Submeter artigo com nome, resumo, coautores e áreas temáticas apenas se estiver no período de submissão, caso contrário mostrar mensagem
- Visualizar artigos submetidos pelo autor logado no momento com status:
  - pendente (deriva dos mais específicos)
    - submetido
    - revisão
    - em consenso
  - concluído
    - aceito
    - rejeitado

## Fluxo do revisor

- Visualizar artigos para revisão (como autor)
- Avaliar artigo escrevendo críticas (texto), contribuições (outro texto) e dando um veredito final dentre:
  - recusado
  - fracamente rejeitado
  - fracamente aceito
  - aceito

## Fluxo de revisão dos artigos

- Distribuir artigos para revisores seguindo algumas regras:
  - distribuir conforme áreas temáticas dos revisores, aceitando artigos sem aderência às áreas apenas em casos em que faltar revisor compatível
  - distribuir igualmente tanto quanto possível
  - não dar a um revisor um artigo do qual é autor ou co-autor
  - não mostrar ao revisor quem são os autores do artigo que vai avaliar
  - notificar revisor sobre cada artigo recebido para avaliação
  - o sistema deve priorizar revisores com maior compatibilidade com as áreas temáticas do artigo
    - um autor tem 3 áreas temáticas de compat. outro apenas duas, o com mais ganha o artigo
  - o autor sempre aceita o artigo (não há opção)
  - cada artigo deve ser distribuído para 2 revisores
  - a distribuição deve considerar apenas revisores do comitê técnico do evento atual
  - se não houver 2 revisores compatíveis, completar com revisores disponíveis mesmo sem compatibilidade
  - a notificação ao revisor deve informar o artigo recebido e o prazo máximo para concluir a revisão

- Concluir artigo após os 2 pareceres:
  - se os dois vereditos forem positivos, o artigo passa para aceito
  - se os dois vereditos forem negativos, o artigo passa para rejeitado
  - se houver conflito entre um parecer positivo e um negativo, o artigo passa para em consenso
  - em consenso, os revisores conversam e registram um veredito final consensual

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

## Ideias gerais

- Separar em camadas
  - Apresentação
  - Lógica (onde se enquadrariam os padrões, serviços, etc)
  - Banco (Repositórios)

## Ideias mirabolantes

- Acho que tem um Observer na questao do artigo esperando veredito (para envio de email)
- Command na questão de coordenador convidar pesquisador para ser revisor
  - Esse comando precisa ser adiado porque o usuário, apesar de não precisar aceitar, ainda precisa dizer quais temas sabe como revisor
