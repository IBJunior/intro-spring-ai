# Concepts fondamentaux de Spring AI

Ce repository contient des exemples illustrant l'utilisation des concepts fondamentaux de Spring AI avec comme
fournisseur
de modèle [OpenAI](https://platform.openai.com/).

## Prérequis

Avant de démarrer le projet, il est impératif d'avoir une clé API d'OpenAI. Pour obtenir cette clé, vous pouvez
consulter la [documentation des clés API d'OpenAI](https://platform.openai.com/api-keys). Une fois obtenue, vous pouvez
soit l'exporter dans une variable d'environnement, soit la renseigner dans le fichier **application.properties**.

````
#Exporter la clé dans la variable d'environnement OPENAI_API_KEY
spring.ai.openai.api-key=${OPENAI_API_KEY:'ou renseignez la ici'}
````

**Note :** Ce projet utilise la version 1.0.0-SNAPSHOT de Spring AI, ce qui pourrait entraîner des différences au
niveau des exemples présentés ici si vous utilisez
une version ultérieure. En cas de divergences, vous avez deux options :

- Vous pouvez vous assurer de travailler avec la même version que moi en consultant le fichier pom.xml du projet.
- Si cette version snapshot n'est plus disponible, vous pouvez simplement consulter la documentation de Spring AI de la
  version que vous utilisez, qui
  est très détaillée.

## Structure du projet

Pour simplifier le test des différents concepts, j'ai utilisé spring-boot-starter-web. Chaque concept est détaillé dans
un contrôleur dont le nom est préfixé par le concept étudié.

### ChatClientController

Il contient des exemples d'utilisation de ChatClient.
Dans certains exemples le **ChatClient** est créé directement à partir d'un Builder, dans d'autre cas, on injecte le
Bean
chatClient déjà configuré dans **ChatClientConfiguration.**

### PromptsController

Il présente les différentes manières de créer des prompts.

### ImageModelController

Il contient des exemples concernant la génération des images.

### SpeechModelController

Il contient des exemples de génération de l'audio à partir du texte.

### MultiModalController

Il montre comment invoquer un LLM avec différentes modalités (texte, image, etc.).

## Pour aller plus loin

Pour aller plus loin je vous recommande la documentation de Spring AI qui est très détaillée et complète. Vous pouvez aussi
explorer Spring AI avec d'autres modèles comme mistral ou llama par exemple.

- [Spring AI](https://docs.spring.io/spring-ai/reference/)
- [OpenAI](https://platform.openai.com/docs/overview)