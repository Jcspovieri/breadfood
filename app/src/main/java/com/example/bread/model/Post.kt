package com.example.bread.model

class Post {
    var nomeProduto: String = ""
    var categoria: String = ""
    var descricao: String = ""
    var imageUrl: String = ""

    constructor(nomeProduto: String, categoria: String, descricao: String, imageUrl: String){
        this.nomeProduto = nomeProduto
        this.categoria = categoria
        this.descricao = descricao
        this.imageUrl = imageUrl
    }

    constructor(){}
}