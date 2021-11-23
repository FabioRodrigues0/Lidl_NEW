# Lidl

![Lidl](/media/brand__default.png)

#### Estrutura do Projeto

* src
    * main
        * java
            * com.example.Lidl
                * Classes
                * Controller
                * Database
                * Model
        * Resources
            * com.example.Lidl
                * ... `javaFX Scenes`
            * media
                * ... `imagens`

#### Classes

Contem todas as classes com os construtores e os getters and setters.

No caso das Classes da categoria, marca e taxa algem de ter os construtores, getters e setters, tem também a o pedido a
base de dados das mesmas para popular a classe

#### Controller

Contem todos os controllers das views

#### Model

Contem os varios models

#### Database

Contem o acesso a base de dados

#### Resources

Contem as varias views

## Views

<!-- Scenes -->
<details>
  <summary>Home</summary>
<div align="center">

![Home](/media/home2.png)

</div>
<ol>
    <li>
      <span>Tabela onde irão aparecer os produtos adicionados a compra</span>
    </li>
    <li>
      <span>Botões para indicar a quanditdade do produto</span>
    </li>
    <li>
        <span>Custo Total da compra</span>
    </li>
    <li>
        <span>Caixa de texto só editable pelos botões que indicam a quantidade de produtos(2)</span>
    </li>
    <li>
        <span>Caixa de Texto para ser inserido o numero de cliente</span>
    </li>
    <li>
        <span>Botão para finilizar a compra</span>
    </li>
    <li>
        <span>Botão para apagar produto da lista adicionados a compra(1)</span>    
    </li>
    <li>
        <span>Botões criados dinamicamente baseado nas categorias que existem na base de dados</span>
    </li>
    <li>
        <span>Botões criados dinamicamente baseado nos produtos que existem na base de dados.</span></br>
        <span>Só apresenda os produtos após ter sido clicado numa categoria e so aparece os produtos relacionados a essa categoria</span>
    </li>
  </ol>
</details>
<details>
  <summary>Client</summary>
<div align="center">

![Client](/media/client2.png)

</div>
<ol>
    <li>
      <span>Botão para criar novo cliente</span>
    </li>
    <li>
      <span>Caixa de texto para indicar o numero de cliente para pesquisar faturas baseados no mesmo</span>
    </li>
    <li>
        <span>Apos indicar o numero de cliente ele apresenta a quantidade de pontos acumulada</span>
    </li>
    <li>
        <span>Tabela onde irão ser apresentados todas as faturas ou as faturas do cliente caso tenha indicado em **2** </span>
    </li>
    <li>
        <span>Tabela onde irão ser apresentados os produtos da fatura selecionada</span>
    </li>
  </ol>
</details>
<details>
  <summary>Product</summary>
<div align="center">

![Product](/media/produto2.png)

</div>
<ol>
    <li>
      <span>Botão para fazer refresh a lista de produtos na base de dados</span>
    </li>
    <li>
      <span>Botão para adicionar novo produto a base de dados</span>
    </li>
    <li>
        <span>Botão para eliminar um produto da base de dados</span>
    </li>
    <li>
        <span>Botão para atualizar produto selecionado na tabela(5)</span>
    </li>
    <li>
        <span>Tabela onde serão aparesentados todos os produtos na base de dados</span>
    </li>
  </ol>
</details>