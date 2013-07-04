curl -X DELETE http://localhost:9200/news

curl -X POST http://localhost:9200/news/news -d '
{
  "id": 1,
  "title": "Portugueses divididos sobre saída de Vítor Gaspar do Governo",
  "content:": "notícia da saída de Vítor Gaspar do Governo foi recebida com um misto de satisfação e preocupação por parte dos portugueses. Apesar de fazerem um balanço pouco positivo dos dois anos de governação do ministro, as pessoas que a SIC ouviu em Bragança não acreditam que a mudança traga novidades.",
  "source": "http://sicnoticias.sapo.pt/pais/2013/07/02/portugueses-divididos-sobre-saida-de-vitor-gaspar-do-governo",
  "tags": ["politics"]
}'

curl -X POST http://localhost:9200/news/news -d '
{
  "id": 2,
  "title": "Paulo Portas apresenta demissão a Passos Coelho",
  "content:": "O ministro de Estado e dos Negócios Estrangeiros apresentou a meio da tarde desta terça-feira a sua demissão ao primeiro-ministro, Pedro Passos Coelho. Paulo Portas, ministro de Estado e dos Negócios Estrangeiros, abandona o Governo no dia em que é dada posse a Maria Luís Albuquerque, ministra das Finanças indigitada após a demissão de Vítor Gaspar. É precisamente a escolha da nova ministra que está na base da saída de Paulo Portas.",
  "source": "http://www.rtp.pt/noticias/index.php?article=663594&tm=9&layout=121&visual=49",
  "tags": ["politics"]
}'

curl -X POST http://localhost:9200/news/news -d '
{
  "id": 3,
  "title": "A secretária de Estado contestada toma posse como ministra das Finanças",
  "content:": "Maria Luís Albuquerque é a nova ministra das Finançaas, ocupando o lugar de Vítor Gaspar, que apresentou a sua demissão nesta segunda-feira. A até aqui secretária de Estado do Tesouro toma posse nesta terçaa-feira, às 17h, confirmou a Presidência da República, no seu site oficial, onde também se dá conta da demissão de Vítor Gaspar.",
  "source": "http://www.publico.pt/economia/noticia/maria-luis-albuquerque-e-a-nova-ministra-das-financas-1598904",
  "tags": ["politics"]
}'

curl -X POST http://localhost:9200/news/news -d '
{
  "id": 4,
  "title": "Capel e Rubio condicionados",
  "content:": "O extremo espanhol Diego Capel e o avançado chileno Diego Rubio treinaram, esta quarta-feira, à parte do restante plante, isto por estarem condicionados. ",
  "source": "http://www.abola.pt/nnh/ver.aspx?id=412659",
  "tags": ["sports", "sporting"]
}'

curl -X POST http://localhost:9200/news/news -d '
{
  "id": 5,
  "title": "Jesus considera que Cardozo faltou ao respeito ao clube",
  "content:": "Em entrevista conduzida por José Eduardo Moniz, vice-presidente do Benfica e administrador da SAD, e que será emitida quinta-feira no canal televisivo dos encarnados, Jorge Jesus enfrentou o tema quente do incidente com Óscar Cardozo, na final da Taça de Portugal, argumentando que o avançado paraguaio faltou ao respeito ao treinador, aos adeptos e ao clube.",
  "source": "http://www.abola.pt/nnh/ver.aspx?id=412612",
  "tags": ["sports", "benfica"]
}'